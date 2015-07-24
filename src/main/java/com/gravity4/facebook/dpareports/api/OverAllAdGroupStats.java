package com.gravity4.facebook.dpareports.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.OverAllAdGroupCSVWriter;
import com.gravity4.facebook.dpareports.dao.OverAllAdGroupLevelStatsDAO;
import com.gravity4.facebook.dpareports.model.AdGroupStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVOverAllAdGroupStats;
import com.gravity4.facebook.dpareports.responseparser.responsedata.OverAllAdGroupStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.OverAllAdGroupResultData;
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
import org.springframework.stereotype.Component;
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
public class OverAllAdGroupStats {

    Logger logger = LoggerFactory.getLogger(OverAllAdGroupStats.class);

    @Autowired
    OverAllAdGroupLevelStatsDAO overAllAdGroupLevelStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Group Level
        */
    public String getOverAllAdGroupstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException, HTTPException {


        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "['adgroup_id','spend','age','gender','total_actions'," +
                "'reach','clicks','impressions','frequency','social_reach','social_impressions','relevance_score'," +
                "'cpm','unique_impressions','unique_social_impressions','cpp','ctr','cpc','cost_per_unique_click']";

        //url for the get request
        String Campaign_Stats_Get_URL = "graph.facebook.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //setting parameters for the get request
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(Campaign_Stats_Get_URL).setPath("/v2.3/act_" + Account_ID + "/reportstats")
                .setParameter("data_columns", data_columns)
                .setParameter("date_preset", date_preset)
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

            status = oAuthExpirationTokenChecker.checkOAuthTokenException(reader, Client_ID);



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
                OverAllAdGroupStatsJSONResponse response = gson.fromJson(jsonfeed, OverAllAdGroupStatsJSONResponse.class);

                List<OverAllAdGroupResultData> results = response.data;

                AdGroupStatsLoader adGroupStatsLoader;
                List<AdGroupStatsLoader> adGroupStatsLoaderList = new ArrayList<AdGroupStatsLoader>();

                for (OverAllAdGroupResultData resultData : results) {

                    adGroupStatsLoader = new AdGroupStatsLoader();

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/

                    Date Stats_Date = StatisticsDate.getYesterday();

                    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
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


                    adGroupStatsLoader.setClient_ID(Client_ID);
                    adGroupStatsLoader.setAdGroup_ID(Long.parseLong(resultData.adgroup_id.trim()));
                    adGroupStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                    adGroupStatsLoader.setActivity_End_Date(Activity_End_Date);
                    adGroupStatsLoader.setRelevance_Score(resultData.relevance_score);
                    adGroupStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    adGroupStatsLoader.setAge_Range(resultData.age);
                    adGroupStatsLoader.setGender(resultData.gender);
                    adGroupStatsLoader.setReach(resultData.reach);
                    adGroupStatsLoader.setFrequency(resultData.frequency);
                    adGroupStatsLoader.setImpressions(resultData.impressions);
                    adGroupStatsLoader.setClicks(resultData.clicks);
                    adGroupStatsLoader.setTotal_Actions(resultData.total_actions);
                    adGroupStatsLoader.setSocial_Reach(resultData.social_reach);
                    adGroupStatsLoader.setSocial_Impressions(resultData.social_impressions);
                    adGroupStatsLoader.setUnique_Impressions(resultData.unique_impressions);
                    adGroupStatsLoader.setUnique_Social_Impressions(resultData.unique_social_impressions);
                    adGroupStatsLoader.setCPC(resultData.cpc);
                    adGroupStatsLoader.setCPM(resultData.cpm);
                    adGroupStatsLoader.setCTR(resultData.ctr);
                    adGroupStatsLoader.setCPP(resultData.cpp);
                    adGroupStatsLoader.setSpend(resultData.spend);
                    adGroupStatsLoader.setStats_Date(Stats_Date);
                    adGroupStatsLoader.setProduct_ID("");

                    adGroupStatsLoaderList.add(adGroupStatsLoader);


                }
                overAllAdGroupLevelStatsDAO.storeadgrouplevelstats(adGroupStatsLoaderList);

                //for storing into file for email
                List<CSVOverAllAdGroupStats> csvOverAllAdGroupStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvOverAllAdGroupStatsList=overAllAdGroupLevelStatsDAO.fileadgrouplevelstats(Client_ID, emailstatsdate);
                //call the CSV file writer
                String stored= OverAllAdGroupCSVWriter.writecsvfile(csvOverAllAdGroupStatsList, Client_ID, emailstatsdate);

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
