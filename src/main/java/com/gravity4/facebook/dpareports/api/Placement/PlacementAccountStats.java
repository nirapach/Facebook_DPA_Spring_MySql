package com.gravity4.facebook.dpareports.api.Placement;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.AgeandGender.OverAllAccountCSVWriter;
import com.gravity4.facebook.dpareports.CSVFileWriter.Placement.PlacementAccountCSVWriter;
import com.gravity4.facebook.dpareports.dao.Placement.PlacementAccountLevelStatsDAO;
import com.gravity4.facebook.dpareports.model.CSVModels.Placement.CSVPlacementAccountStats;
import com.gravity4.facebook.dpareports.model.APImodels.Placement.PlacementAccountStatsLoader;
import com.gravity4.facebook.dpareports.responseparser.responsedata.Placement.PlacementAccountStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Placement.PlacementAccountsResultData;
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
public class PlacementAccountStats {

    Logger logger = LoggerFactory.getLogger(PlacementAccountStats.class);

    @Autowired
    PlacementAccountLevelStatsDAO placementAccountLevelStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    OverAllAccountCSVWriter overAllAccountCSVWriter=new OverAllAccountCSVWriter();

    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Account Level
        */
    public String getPlacementAccountstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token,String csv_address) throws URISyntaxException, IOException, PropertyVetoException, SQLException, HTTPException {

        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        //String time_ranges = "[{\"time_start\":1437534000,\"time_stop\":1438657200}]";
        String date_preset = "yesterday";
        String data_columns = "['account_name','spend','placement','impression_device','total_actions'," +
                "'reach','clicks','impressions','frequency','social_reach','social_impressions'," +
                "'cpm','unique_impressions','unique_social_impressions','cpp','ctr','cpc','cost_per_unique_click']";

        //url for the get request
        String Campaign_Stats_Get_URL = "graph.facebook.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //setting parameters for the get request
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(Campaign_Stats_Get_URL).setPath("/v2.3/act_" + Account_ID + "/reportstats")
                .setParameter("data_columns", data_columns)
                        // .setParameter("time_ranges", time_ranges)
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

            status = oAuthExpirationTokenChecker.checkOAuthTokenException(reader, Client_ID,Account_ID_Integer);


        /*
        To write the Account Level Stats to the Database
        Only when the returned status is '1' then this data is written into the database
         */
            if (status) {
                httpResponse = httpClient.execute(httpGet);
                reader = new BufferedReader(new InputStreamReader(

                        httpResponse.getEntity().getContent()));
                String inputLine;
                StringBuffer fbresponse = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    fbresponse.append(inputLine);
                }
                String jsonfeed = fbresponse.toString();
                Gson gson = new Gson();

                PlacementAccountStatsJSONResponse response = gson.fromJson(jsonfeed, PlacementAccountStatsJSONResponse.class);

                List<PlacementAccountsResultData> results = response.data;

                PlacementAccountStatsLoader accountStatsLoader;
                List<PlacementAccountStatsLoader> accountStatsLoaderList = new ArrayList<PlacementAccountStatsLoader>();
                ;


                for (PlacementAccountsResultData resultData : results) {

                    accountStatsLoader = new PlacementAccountStatsLoader();

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/

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


                    accountStatsLoader.setClient_ID(Client_ID);
                    accountStatsLoader.setAccount_ID(Long.parseLong(resultData.account_id.trim()));
                    accountStatsLoader.setAccount_Name(resultData.account_name);
                    accountStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                    accountStatsLoader.setActivity_End_Date(Activity_End_Date);
                    accountStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    accountStatsLoader.setPlacement(resultData.placement);
                    accountStatsLoader.setImpression_Device(resultData.impression_device);
                    accountStatsLoader.setReach(resultData.reach);
                    accountStatsLoader.setFrequency(resultData.frequency);
                    accountStatsLoader.setImpressions(resultData.impressions);
                    accountStatsLoader.setClicks(resultData.clicks);
                    accountStatsLoader.setTotal_Actions(resultData.total_actions);
                    accountStatsLoader.setSocial_Reach(resultData.social_reach);
                    accountStatsLoader.setSocial_Impressions(resultData.social_impressions);
                    accountStatsLoader.setUnique_Impressions(resultData.unique_impressions);
                    accountStatsLoader.setUnique_Social_Impressions(resultData.unique_social_impressions);
                    accountStatsLoader.setCPC(resultData.cpc);
                    accountStatsLoader.setCPM(resultData.cpm);
                    accountStatsLoader.setCTR(resultData.ctr);
                    accountStatsLoader.setCPP(resultData.cpp);
                    accountStatsLoader.setSpend(resultData.spend);
                    accountStatsLoader.setStats_Date(Stats_Date);


                    accountStatsLoaderList.add(accountStatsLoader);


                }
                //for storng into database
                placementAccountLevelStatsDAO.storeaccountlevelstats(accountStatsLoaderList);
                //for storing into file for email
                List<CSVPlacementAccountStats> csvPlacementAccountStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvPlacementAccountStatsList=placementAccountLevelStatsDAO.fileaccountlevelstats(Client_ID,emailstatsdate);
                //call the CSV file writer
               String stored= PlacementAccountCSVWriter.writecsvfile(csvPlacementAccountStatsList, Client_ID, emailstatsdate,csv_address);

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
            logger.info("OverAllProductLevelAccountStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("OverAllProductLevelAccountStats HTTP Response NullPointerException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        httpClient.close();
        return store_file_name;

    }
}
