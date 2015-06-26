package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.AdGroupStatsDAO;
import dpa.model.AdGroupStatsLoader;
import dpa.responseparser.responsedata.AdGroupStatsJSONResponse;
import dpa.responseparser.resultdata.AdGroupResultData;
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


public class AdGroupStats {

    Logger logger= LoggerFactory.getLogger(AdGroupStats.class);
    /*
       Makes a Get API call to reportstats API to get the statistics at the Individual Ads Level with individual products
        level stats for each ad
        */
    public boolean getAdgroupstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID=Client_ID_Integer;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "[\"adgroup_id\",\"product_id\",\"spend\",\"age\",\"gender\",\"country\"," +
                "\"placement\",\"impression_device\",\"total_actions\",\"reach\",\"clicks\",\"impressions\",\"frequency\",\"social_reach\"," +
                "\"social_impressions\"," +
                "\"cpm\",\"unique_impressions\",\"relevancy_score\",\"unique_social_impressions\",\"cpp\",\"ctr\",\"cpc\"," +
                "\"cost_per_unique_click\"";

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
        //declaring the httpget request
        HttpGet httpGet = new HttpGet(builder.build());
        //getting the httpresponse
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);


            System.out.println("GET Response Status: "
                    + httpResponse.getStatusLine().getStatusCode());

            reader = new BufferedReader(new InputStreamReader(
                    httpResponse.getEntity().getContent()));

            reader.close();
        }
        catch (ClientProtocolException e) {
            logger.info("ClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("AdGroupStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            logger.info("AdGroupStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }


        Gson gson = new Gson();

        /*
        To check for OAuth Token Expiration
         */

        OAuthExpirationTokenChecker oAuthExpirationTokenChecker= new OAuthExpirationTokenChecker();
        int status=oAuthExpirationTokenChecker.checkOAuthTokenException(reader,Client_ID);

        if(status==1) {

            AdGroupStatsJSONResponse response = gson.fromJson(reader, AdGroupStatsJSONResponse.class);

            List<AdGroupResultData> results = response.resultdata;

            AdGroupStatsLoader adGroupStatsLoader;
            List<AdGroupStatsLoader> adGroupStatsLoaderList = new ArrayList<AdGroupStatsLoader>();

            for (AdGroupResultData resultData : results) {

                adGroupStatsLoader = new AdGroupStatsLoader();

                //getting the age range and splitting it into accessible integer values
                String Age = resultData.age;
                String Age_Start_SubString = Age.substring(Age.lastIndexOf("-") - 1);
                String Age_End_SubString = Age.substring(Age.lastIndexOf("-") + 1);
                int Age_Start_Range = Integer.parseInt(Age_Start_SubString);
                int Age_End_Range = Integer.parseInt(Age_End_SubString);

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
                Date Stats_Date = StatisticsDate.getYesterday();

                adGroupStatsLoader.setClient_ID(Client_ID);
                adGroupStatsLoader.setAdGroup_ID(resultData.adgroup_id);
                adGroupStatsLoader.setActivity_Start_Date(resultData.date_start);
                adGroupStatsLoader.setActivity_End_Date(resultData.date_stop);
                adGroupStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                adGroupStatsLoader.setCountry(resultData.country);
                adGroupStatsLoader.setAge_Start_Range(Age_Start_Range);
                adGroupStatsLoader.setAge_End_Range(Age_End_Range);
                adGroupStatsLoader.setGender(resultData.gender);
                adGroupStatsLoader.setPlacement(resultData.placement);
                adGroupStatsLoader.setImpression_Device(resultData.impression_device);
                adGroupStatsLoader.setReach(resultData.reach);
                adGroupStatsLoader.setFrequency(resultData.frequency);
                adGroupStatsLoader.setImpressions(resultData.impressions);
                adGroupStatsLoader.setClicks(resultData.clicks);
                adGroupStatsLoader.setTotal_Actions(resultData.total_actions);
                adGroupStatsLoader.setRelevany_Score(resultData.relevancy_score);
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

                adGroupStatsLoaderList.add(adGroupStatsLoader);


            }
            AdGroupStatsDAO.storeadgrouplevelstats(adGroupStatsLoaderList);

            httpClient.close();

            return true;
        }

        else {
            return false;
        }
    }
}


