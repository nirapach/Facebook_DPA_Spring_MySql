package com.gravity4.facebook.dpareports.api.Actions;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.Actions.ActionsAdGroupCSVWriter;
import com.gravity4.facebook.dpareports.dao.Actions.ActionTypeAdGroupLevelStatsDAO;
import com.gravity4.facebook.dpareports.model.APImodels.Actions.ActionTypeStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVModels.Actions.CSVActionsAdGroupStats;
import com.gravity4.facebook.dpareports.responseparser.responsedata.actions.AdGroupActionTypesStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Actions.AdGroupActionTypesResultData;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Actions.ActionTypesValueResultData;
import com.gravity4.facebook.dpareports.utils.OAuthExpirationTokenChecker;
import com.gravity4.facebook.dpareports.utils.StatisticsDate;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.http.HTTPException;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@SuppressWarnings("unchecked")
public class ActionTypeAdGroupStats {

    Logger logger = LoggerFactory.getLogger(ActionTypeAdGroupStats.class);

    @Autowired
    ActionTypeAdGroupLevelStatsDAO actionTypeAdGroupLevelStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Group Level
        */
    public String getActionTypeAdGroupstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token,String csv_address) throws URISyntaxException, IOException, PropertyVetoException, SQLException, HTTPException {


        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        String level="adgroup";
        String date_preset = "yesterday";
        String action_breakdowns = "['action_type']";
        String fields = "['adgroup_name','adgroup_id','actions']";
        String filtering="[{field:'action_type',operator:'IN'," +
                "value:['link_click','like','offsite_conversion','offsite_conversion.fb_pixel_purchase'," +
                "'comment','offsite_conversion.fb_pixel_add_to_cart','offsite_conversion.fb_pixel_view_content'," +
                "'post','post_like','page_engagement','post_engagement','mention','photo_view']}]";
        //url for the get request
        String Campaign_Stats_Get_URL = "graph.facebook.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //setting parameters for the get request
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(Campaign_Stats_Get_URL).setPath("/v2.3/act_" + Account_ID + "/insights")
                .setParameter("action_breakdowns", action_breakdowns)
                .setParameter("fields", fields)
                .setParameter("level", level)
                .setParameter("date_preset", date_preset)
                .setParameter("filtering", filtering)
                .setParameter("access_token", Access_Token);

        BufferedReader reader = null;
        boolean status = false;
        //getting the httpresponse
        CloseableHttpResponse httpResponse;
        //declaring the httpget request
        HttpGet httpGet = new HttpGet(builder.build());

        try {
            httpResponse = httpClient.execute(httpGet);


            System.out.println("GET Response Status: "
                    + httpResponse.getStatusLine().getStatusCode());

            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));



             /*
        To check for OAuth Token Expiration
         */

            status = oAuthExpirationTokenChecker.checkOAuthTokenException(reader, Client_ID,Account_ID_Integer);



        /*
        To write the Account Level Stats to the Database
        Only when the returned status is '1' then this data is written into the database
         */
            if (status) {

                httpResponse = httpClient.execute(httpGet);
                reader = new BufferedReader(new InputStreamReader(

                        httpResponse.getEntity().getContent()));

                Gson gson = new Gson();

                String inputLine;
                StringBuffer fbresponse = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    fbresponse.append(inputLine);
                }
                String jsonfeed = fbresponse.toString();
                AdGroupActionTypesStatsJSONResponse response = gson.fromJson(jsonfeed, AdGroupActionTypesStatsJSONResponse.class);

                List<AdGroupActionTypesResultData> results = response.data;

               ActionTypeStatsLoader adGroupStatsLoader;
                List<ActionTypeStatsLoader> adGroupStatsLoaderList = new ArrayList<ActionTypeStatsLoader>();

                for (AdGroupActionTypesResultData resultData : results) {

                    List<ActionTypesValueResultData> valueResultData =resultData.actions;

                    for (ActionTypesValueResultData actionvalues:valueResultData) {

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's date in date_preset field of the curl request*/

                        adGroupStatsLoader = new ActionTypeStatsLoader();
                        Date Stats_Date = StatisticsDate.getYesterday();

                        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String Start_Date = resultData.date_start;
                        String Stop_Date = resultData.date_stop;
                        Date Activity_Start_Date = null;
                        Date Activity_End_Date = null;
                        try {
                            Activity_Start_Date = format.parse(Start_Date);
                            Activity_End_Date = format.parse(Stop_Date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        //parsing and adding the data TO THE MODEL
                        adGroupStatsLoader.setClient_ID(Client_ID);
                        adGroupStatsLoader.setID(Long.parseLong(resultData.adgroup_id.trim()));
                        adGroupStatsLoader.setName(resultData.adgroup_name);
                        adGroupStatsLoader.setStats_Date(Stats_Date);
                        adGroupStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                        adGroupStatsLoader.setActivity_End_Date(Activity_End_Date);
                        adGroupStatsLoader.setType(actionvalues.action_type);
                        adGroupStatsLoader.setValue(actionvalues.value);


                        adGroupStatsLoaderList.add(adGroupStatsLoader);
                    }

                }
                actionTypeAdGroupLevelStatsDAO.storeactionsadgrouplevelstats(adGroupStatsLoaderList);

                //for storing into file for email
                List<CSVActionsAdGroupStats> csvActionsAdGroupStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvActionsAdGroupStatsList=actionTypeAdGroupLevelStatsDAO.fileactionsdadgrouplevelstats(Client_ID, emailstatsdate);
                //call the CSV file writer
                String stored= ActionsAdGroupCSVWriter.writecsvfile(csvActionsAdGroupStatsList, Client_ID, emailstatsdate,csv_address);

                if(stored!=null){
                    store_file_name=stored;
                }

            }
            reader.close();


        } catch (ClientProtocolException e) {
            logger.info("OverAllClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("OverAllAdGroupStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("OverAllAdGroupStats HTTP Response NullPointerException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        httpClient.close();
        return store_file_name;

    }
}
