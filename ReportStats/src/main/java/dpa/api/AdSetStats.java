package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.AdSetStatsDAO;
import dpa.controller.CampaignStatsDAO;
import dpa.model.AdSetStatsLoader;
import dpa.responseparser.responsedata.AdSetStatsJSONResponse;
import dpa.responseparser.resultdata.AdGroupResultData;
import dpa.responseparser.resultdata.AdSetResultData;
import dpa.responseparser.resultdata.CampaignResultData;
import dpa.views.StatsInformationCaller;
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


public class AdSetStats {

    Logger logger= LoggerFactory.getLogger(AdSetStats.class);
    java.util.Calendar calendar = new java.util.GregorianCalendar();
    /*
       Makes a Get API call to reportstats API to get the statistics at the AdSet Level
        */
    public void getAdsetstats(int Account_ID_Integer,int Client_ID_Integer,String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        int Client_ID=Client_ID_Integer;
        String Account_ID = Integer.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "[\"campaign_id\",\"product_id\",\"spend\",\"age\",\"gender\",\"country\"," +
                "\"placement\",\"impression_device\",\"total_actions\",\"reach\",\"clicks\",\"impressions\",\"frequency\",\"social_reach\"," +
                "\"social_impressions\"," +
                "\"cpm\",\"unique_impressions\",\"unique_social_impressions\",\"cpp\",\"ctr\",\"cpc\"," +
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

        //declaring the httpget request
        HttpGet httpGet = new HttpGet(builder.build());
        //getting the httpresponse
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
        } catch (IOException e) {
            logger.info("AdSetStats HTTP Response");
            logger.info(String.valueOf(httpResponse));
            e.printStackTrace();
        }

        System.out.println("GET Response Status: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        /*String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }*/
        reader.close();

        Gson gson= new Gson();

        AdSetStatsJSONResponse response = gson.fromJson(reader,AdSetStatsJSONResponse.class);

        List<AdSetResultData> results = response.resultdata;

        AdSetStatsLoader adSetStatsLoader;
        List<AdSetStatsLoader> adSetStatsLoaderList=new ArrayList<AdSetStatsLoader>();

        for(AdSetResultData resultData:results){

            adSetStatsLoader = new AdSetStatsLoader();

            //getting the age range and splitting it into accessible integer values
            String Age=resultData.age;
            String Age_Start_SubString=Age.substring(Age.lastIndexOf("-")-1);
            String Age_End_SubString=Age.substring(Age.lastIndexOf("-")+1);
            int Age_Start_Range = Integer.parseInt(Age_Start_SubString);
            int Age_End_Range= Integer.parseInt(Age_End_SubString);

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
            Date Stats_Date =calendar.getTime();

            adSetStatsLoader.setClient_ID(Client_ID);
            adSetStatsLoader.setAdSet_ID(resultData.campaign_id);
            adSetStatsLoader.setActivity_Start_Date(resultData.date_start);
            adSetStatsLoader.setActivity_End_Date(resultData.date_stop);
            adSetStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
            adSetStatsLoader.setCountry(resultData.country);
            adSetStatsLoader.setAge_Start_Range(Age_Start_Range);
            adSetStatsLoader.setAge_End_Range(Age_End_Range);
            adSetStatsLoader.setGender(resultData.gender);
            adSetStatsLoader.setPlacement(resultData.placement);
            adSetStatsLoader.setImpression_Device(resultData.impression_device);
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

            adSetStatsLoaderList.add(adSetStatsLoader);



        }
        AdSetStatsDAO.storeadsetlevelstats(adSetStatsLoaderList);

        /*Save the Json Response
        String jsonresponse = response.toString();
        JSONObject JsonCampignStats = new JSONObject(jsonresponse);*/
        httpClient.close();

    }
}

