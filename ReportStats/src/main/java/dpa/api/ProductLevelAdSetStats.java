package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.ProductLevelAdSetStatsDAO;
import dpa.model.AdSetStatsLoader;
import dpa.responseparser.responsedata.ProductLevelAdSetStatsJSONResponse;
import dpa.responseparser.resultdata.ProductLevelAdSetResultData;
import dpa.utils.OAuthExpirationTokenChecker;
import dpa.utils.StatisticsDate;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;


public class ProductLevelAdSetStats {

    Logger logger= LoggerFactory.getLogger(ProductLevelAdSetStats.class);

    /*
       Makes a Get API call to reportstats API to get the statistics at the AdSet Level
        */
    public boolean getAdsetstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID=Client_ID_Integer;
        boolean store=false;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "last_90_days";
        String data_columns = "['campaign_id','spend','product_id','total_actions'," +
                "'reach','clicks','impressions','frequency','social_reach','social_impressions'," +
                "'cpm','unique_impressions','unique_social_impressions','cpp','ctr','cpc','cost_per_unique_click']";

        //url for the get request
        String Campaign_Stats_Get_URL = "graph.facebook.com";
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //setting parameters for the get request
        URIBuilder builder = new URIBuilder();
        builder.setScheme("https").setHost(Campaign_Stats_Get_URL).setPath("/v2.3/act_" +Account_ID+ "/reportstats")
                .setParameter("data_columns", data_columns)
                .setParameter("date_preset", date_preset)
                .setParameter("access_token",Access_Token);

        BufferedReader reader=null;
        int status=0;
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

            OAuthExpirationTokenChecker oAuthExpirationTokenChecker= new OAuthExpirationTokenChecker();
            status=oAuthExpirationTokenChecker.checkOAuthTokenException(reader,Client_ID);

            Gson gson= new Gson();

            if(status==1) {
                ProductLevelAdSetStatsJSONResponse response = gson.fromJson(reader, ProductLevelAdSetStatsJSONResponse.class);

                List<ProductLevelAdSetResultData> results = response.resultdata;

                AdSetStatsLoader adSetStatsLoader;
                List<AdSetStatsLoader> adSetStatsLoaderList = new ArrayList<AdSetStatsLoader>();

                for (ProductLevelAdSetResultData resultData : results) {

                    adSetStatsLoader = new AdSetStatsLoader();

                    //getting the age range and splitting it into accessible integer values
                /*String Age = resultData.age;
                String Age_Start_SubString = Age.substring(Age.lastIndexOf("-") - 1);
                String Age_End_SubString = Age.substring(Age.lastIndexOf("-") + 1);
                int Age_Start_Range = Integer.parseInt(Age_Start_SubString);
                int Age_End_Range = Integer.parseInt(Age_End_SubString);*/

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
                    Date Stats_Date = StatisticsDate.getYesterday();

                    adSetStatsLoader.setClient_ID(Client_ID);
                    adSetStatsLoader.setAdSet_ID(Long.valueOf(resultData.campaign_id));
                    adSetStatsLoader.setActivity_Start_Date(resultData.date_start);
                    adSetStatsLoader.setActivity_End_Date(resultData.date_stop);
                    adSetStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    adSetStatsLoader.setProduct_ID(Long.valueOf(resultData.product_id));
                    adSetStatsLoader.setReach(resultData.reach);
                    adSetStatsLoader.setFrequency(resultData.frequency);
                    adSetStatsLoader.setImpressions(resultData.impressions);
                    adSetStatsLoader.setClicks(resultData.clicks);
                    adSetStatsLoader.setTotal_Actions(resultData.total_actions);
                    adSetStatsLoader.setSocial_Reach(resultData.social_reach);
                    adSetStatsLoader.setSocial_Impressions(resultData.social_impressions);
                    adSetStatsLoader.setUnique_Impressions(resultData.unique_impressions);
                    adSetStatsLoader.setUnique_Social_Impressions(resultData.unique_social_impressions);
                    adSetStatsLoader.setCPC(resultData.cpc);
                    adSetStatsLoader.setCPM(resultData.cpm);
                    adSetStatsLoader.setCTR(resultData.ctr);
                    adSetStatsLoader.setCPP(resultData.cpp);
                    adSetStatsLoader.setSpend(resultData.spend);
                    adSetStatsLoader.setStats_Date(Stats_Date);

                    adSetStatsLoader.setAge_Start_Range(0);
                    adSetStatsLoader.setAge_End_Range(0);
                    adSetStatsLoader.setGender("null");

                    adSetStatsLoaderList.add(adSetStatsLoader);


                }
                ProductLevelAdSetStatsDAO.storeadsetlevelstats(adSetStatsLoaderList);


                httpClient.close();
                store= true;
            }
            reader.close();
        }
        catch (ClientProtocolException e) {
            logger.info("ClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("ProductLevelAdSetStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            logger.info("ProductLevelAdSetStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        return store;



    }
}

