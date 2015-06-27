package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.CampaignStatsDAO;
import dpa.model.AccountStatsLoader;
import dpa.model.CampaignStatsLoader;
import dpa.responseparser.responsedata.CampaignStatsJSONResponse;
import dpa.responseparser.resultdata.CampaignResultData;
import dpa.utils.OAuthExpirationTokenChecker;
import dpa.utils.StatisticsDate;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
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


public class CampaignStats {

    Logger logger= LoggerFactory.getLogger(CampaignStats.class);

    /*
    Makes a Get API call to reportstats API to get the statistics at the Campaign Level
     */
    public boolean getCampaignstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID=Client_ID_Integer;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "[\"campaign_group_id\",\"product_id\",\"spend\"," +
                "\"total_actions\",\"reach\",\"clicks\",\"impressions\",\"frequency\",\"social_reach\"," +
                "\"social_impressions\"," +
                "\"cpm\",\"unique_impressions\",\"unique_social_impressions\",\"cpp\",\"ctr\",\"cpc\"," +
                "\"cost_per_unique_click\"]";

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

        }
        catch (ClientProtocolException e) {
            logger.info("ClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("CampaignStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        catch (NullPointerException e) {
            logger.info("CampaignStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        reader.close();

        Gson gson=new Gson();

        /*
        To check for OAuth Token Expiration
         */

        OAuthExpirationTokenChecker oAuthExpirationTokenChecker= new OAuthExpirationTokenChecker();
        int status=oAuthExpirationTokenChecker.checkOAuthTokenException(reader,Client_ID);

        if(status==1) {
            CampaignStatsJSONResponse response = gson.fromJson(reader, CampaignStatsJSONResponse.class);

            List<CampaignResultData> results = response.resultdata;

            CampaignStatsLoader campaignStatsLoader;
            List<CampaignStatsLoader> campaignStatsLoaderList = new ArrayList<CampaignStatsLoader>();

            for (CampaignResultData resultData : results) {

                campaignStatsLoader = new CampaignStatsLoader();

                //getting the age range and splitting it into accessible integer values
                /*String Age = resultData.age;
                String Age_Start_SubString = Age.substring(Age.lastIndexOf("-") - 1);
                String Age_End_SubString = Age.substring(Age.lastIndexOf("-") + 1);
                int Age_Start_Range = Integer.parseInt(Age_Start_SubString);
                int Age_End_Range = Integer.parseInt(Age_End_SubString);*/

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
                Date Stats_Date = StatisticsDate.getYesterday();

                campaignStatsLoader.setClient_ID(Client_ID);
                campaignStatsLoader.setCampaign_ID(resultData.campaign_group_id);
                campaignStatsLoader.setActivity_Start_Date(resultData.date_start);
                campaignStatsLoader.setActivity_End_Date(resultData.date_stop);
                campaignStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                campaignStatsLoader.setProduct_ID(resultData.product_id);
                /*campaignStatsLoader.setCountry(resultData.country);
                campaignStatsLoader.setAge_Start_Range(Age_Start_Range);
                campaignStatsLoader.setAge_End_Range(Age_End_Range);
                campaignStatsLoader.setGender(resultData.gender);
                campaignStatsLoader.setPlacement(resultData.placement);
                campaignStatsLoader.setImpression_Device(resultData.impression_device);*/
                campaignStatsLoader.setReach(resultData.reach);
                campaignStatsLoader.setFrequency(resultData.frequency);
                campaignStatsLoader.setImpressions(resultData.impressions);
                campaignStatsLoader.setClicks(resultData.clicks);
                campaignStatsLoader.setTotal_Actions(resultData.total_actions);
                campaignStatsLoader.setSocial_Reach(resultData.social_reach);
                campaignStatsLoader.setSocial_Impressions(resultData.social_impressions);
                campaignStatsLoader.setUnique_Impressions(resultData.unique_impressions);
                campaignStatsLoader.setUnique_Social_Impressions(resultData.unique_social_impressions);
                campaignStatsLoader.setCPC(resultData.cpc);
                campaignStatsLoader.setCPM(resultData.cpm);
                campaignStatsLoader.setCTR(resultData.ctr);
                campaignStatsLoader.setCPP(resultData.cpp);
                campaignStatsLoader.setSpend(resultData.spend);
                campaignStatsLoader.setStats_Date(Stats_Date);

                campaignStatsLoaderList.add(campaignStatsLoader);

            }
            CampaignStatsDAO.storecampaignlevelstats(campaignStatsLoaderList);

            httpClient.close();
            return true;
        }

        else{
            return false;
        }
    }
}
