package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.ProductLevelAdGroupStatsDAO;
import dpa.model.AdGroupStatsLoader;
import dpa.responseparser.responsedata.ProductLevelAdGroupStatsJSONResponse;
import dpa.responseparser.resultdata.ProductLevelAdGroupResultData;
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


public class ProductLevelAdGroupStats {

    Logger logger= LoggerFactory.getLogger(ProductLevelAdGroupStats.class);
    /*
       Makes a Get API call to reportstats API to get the statistics at the Individual Ads Level with individual products
        level stats for each ad
        */
    public boolean getAdgroupstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID=Client_ID_Integer;
        boolean store=false;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "last_90_days";
        String data_columns = "['adgroup_id','spend','product_id','total_actions','relevance_score'," +
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

            Gson gson = new Gson();

            if(status==1) {

                ProductLevelAdGroupStatsJSONResponse response = gson.fromJson(reader, ProductLevelAdGroupStatsJSONResponse.class);

                List<ProductLevelAdGroupResultData> results = response.resultdata;

                AdGroupStatsLoader adGroupStatsLoader;
                List<AdGroupStatsLoader> adGroupStatsLoaderList = new ArrayList<AdGroupStatsLoader>();

                for (ProductLevelAdGroupResultData resultData : results) {

                    adGroupStatsLoader = new AdGroupStatsLoader();


            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
                    Date Stats_Date = StatisticsDate.getYesterday();

                    adGroupStatsLoader.setClient_ID(Client_ID);
                    adGroupStatsLoader.setAdGroup_ID(Long.valueOf(resultData.adgroup_id));
                    adGroupStatsLoader.setActivity_Start_Date(resultData.date_start);
                    adGroupStatsLoader.setActivity_End_Date(resultData.date_stop);
                    adGroupStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    adGroupStatsLoader.setProduct_ID(Long.valueOf(resultData.product_id));
                    adGroupStatsLoader.setReach(resultData.reach);
                    adGroupStatsLoader.setFrequency(resultData.frequency);
                    adGroupStatsLoader.setImpressions(resultData.impressions);
                    adGroupStatsLoader.setClicks(resultData.clicks);
                    adGroupStatsLoader.setTotal_Actions(resultData.total_actions);
                    adGroupStatsLoader.setRelevance_Score(resultData.relevance_score);
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


                    adGroupStatsLoader.setAge_Start_Range(0);
                    adGroupStatsLoader.setAge_End_Range(0);
                    adGroupStatsLoader.setGender("null");

                    adGroupStatsLoaderList.add(adGroupStatsLoader);


                }
                ProductLevelAdGroupStatsDAO.storeadgrouplevelstats(adGroupStatsLoaderList);


                httpClient.close();

                store=true;
            }
            reader.close();
        }
        catch (ClientProtocolException e) {
            logger.info("ClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("ProductLevelAdGroupStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            logger.info("ProductLevelAdGroupStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }

        return store;


    }
}


