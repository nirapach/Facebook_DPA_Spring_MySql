package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import dpa.controller.AccountStatsDAO;
import dpa.model.AccountStatsLoader;
import dpa.responseparser.responsedata.AccountStatsJSONResponse;
import dpa.responseparser.resultdata.AccountsResultData;
import dpa.views.StatsInformationCaller;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.*;
import java.lang.Object;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import dpa.model.AccountInformationLoader;

public class AccountStats {

    Logger logger= LoggerFactory.getLogger(AccountStats.class);
    java.util.Calendar calendar = new java.util.GregorianCalendar();


    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Account Level
        */
    public void getAccountstats(int Account_ID_Integer,int Client_ID_Integer,String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {
        
        //Fields in the parameters
        int Client_ID=Client_ID_Integer;
        String Account_ID = Integer.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "[\"account_id\",\"product_id\",\"spend\",\"age\",\"gender\",\"country\"," +
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
            logger.info("AccountStats HTTP Response");
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

        Gson gson=new Gson();

        AccountStatsJSONResponse response = gson.fromJson(reader,AccountStatsJSONResponse.class);

        List<AccountsResultData> results = response.resultdata;

        AccountStatsLoader accountStatsLoader;
        List<AccountStatsLoader> accountStatsLoaderList=new ArrayList<AccountStatsLoader>();

        for(AccountsResultData resultData:results ){

            accountStatsLoader= new AccountStatsLoader();

            //getting the age range and splitting it into accessible integer values
            String Age=resultData.age;
            String Age_Start_SubString=Age.substring(Age.lastIndexOf("-")-1);
            String Age_End_SubString=Age.substring(Age.lastIndexOf("-")+1);
            int Age_Start_Range = Integer.parseInt(Age_Start_SubString);
            int Age_End_Range= Integer.parseInt(Age_End_SubString);

            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/
            Date Stats_Date =calendar.getTime();

            accountStatsLoader.setClient_ID(Client_ID);
            accountStatsLoader.setAccount_ID(resultData.account_id);
            accountStatsLoader.setActivity_Start_Date(resultData.date_start);
            accountStatsLoader.setActivity_End_Date(resultData.date_stop);
            accountStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
            accountStatsLoader.setCountry(resultData.country);
            accountStatsLoader.setAge_Start_Range(Age_Start_Range);
            accountStatsLoader.setAge_End_Range(Age_End_Range);
            accountStatsLoader.setGender(resultData.gender);
            accountStatsLoader.setPlacement(resultData.placement);
            accountStatsLoader.setImpression_Device(resultData.impression_device);
            accountStatsLoader.setReach(resultData.reach);
            accountStatsLoader.setFrequency(resultData.frequency);
            accountStatsLoader.setImpressions(resultData.impressions);
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
        AccountStatsDAO.storeaccountlevelstats(accountStatsLoaderList);
        /*
        // Save the Json Response
        String jsonresponse = response.toString();
        JSONObject JsonAccountStats = new JSONObject(jsonresponse);
        */
        httpClient.close();

    }
}
