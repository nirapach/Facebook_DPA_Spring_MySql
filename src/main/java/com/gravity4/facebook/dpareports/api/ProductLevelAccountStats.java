package com.gravity4.facebook.dpareports.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.ProductLevelAccountCSVWriter;
import com.gravity4.facebook.dpareports.dao.ProductLevelAccountStatsDAO;
import com.gravity4.facebook.dpareports.model.AccountStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVProductLevelAccountStats;
import com.gravity4.facebook.dpareports.responseparser.responsedata.ProductLevelAccountStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevelAccountsResultData;
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
public class ProductLevelAccountStats {

    Logger logger = LoggerFactory.getLogger(ProductLevelAccountStats.class);


    @Autowired
    ProductLevelAccountStatsDAO productLevelAccountStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Account Level
        */
    public String getAccountstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException, HTTPException {


        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "['account_id','spend','product_id','total_actions'," +
                "'reach','clicks','impressions','frequency','social_reach','social_impressions'," +
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

        BufferedReader reader;
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

            BufferedReader OAuthReader = reader;

             /*
        To check for OAuth Token Expiration
         */
            status = oAuthExpirationTokenChecker.checkOAuthTokenException(OAuthReader, Client_ID);

        /*
        To write the Account Level Stats to the Database
        Only when the returned status is '1' then this data is written into the database
         */
            if (status) {

                Gson gson = new Gson();

                httpResponse = httpClient.execute(httpGet);
                reader = new BufferedReader(new InputStreamReader(
                        httpResponse.getEntity().getContent()));

                String inputLine;
                StringBuffer fbresponse = new StringBuffer();
                while ((inputLine = reader.readLine()) != null) {
                    fbresponse.append(inputLine);
                }
                String jsonfeed = fbresponse.toString();


                ProductLevelAccountStatsJSONResponse response = gson.fromJson(jsonfeed, ProductLevelAccountStatsJSONResponse.class);

                List<ProductLevelAccountsResultData> results = response.data;

                AccountStatsLoader accountStatsLoader;
                List<AccountStatsLoader> accountStatsLoaderList = new ArrayList<AccountStatsLoader>();

                for (ProductLevelAccountsResultData data : results) {

                    accountStatsLoader = new AccountStatsLoader();


            /*get yesterday's date so that it can be stored as the date on which these stats belong to since we
            are getting yesterday's datein date_preset field of the curl request*/

                    Date Stats_Date = StatisticsDate.getYesterday();

                    DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
                    String Start_Date = data.date_start;
                    String Stop_Date = data.date_stop;
                    Date Activity_Start_Date = null;
                    Date Activity_End_Date = null;
                    try {
                        Activity_Start_Date = format.parse(Start_Date);
                        Activity_End_Date = format.parse(Stop_Date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    accountStatsLoader.setClient_ID(Client_ID);
                    accountStatsLoader.setAccount_ID(Long.parseLong(data.account_id.trim()));
                    accountStatsLoader.setProduct_ID(data.product_id.trim());
                    accountStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                    accountStatsLoader.setActivity_End_Date(Activity_End_Date);
                    accountStatsLoader.setCost_Per_Unique_Click(data.cost_per_unique_click);
                    accountStatsLoader.setReach(data.reach);
                    accountStatsLoader.setFrequency(data.frequency);
                    accountStatsLoader.setImpressions(data.impressions);
                    accountStatsLoader.setClicks(data.clicks);
                    accountStatsLoader.setTotal_Actions(data.total_actions);
                    accountStatsLoader.setSocial_Reach(data.social_reach);
                    accountStatsLoader.setSocial_Impressions(data.social_impressions);
                    accountStatsLoader.setUnique_Impressions(data.unique_impressions);
                    accountStatsLoader.setUnique_Social_Impressions(data.unique_social_impressions);
                    accountStatsLoader.setCPC(data.cpc);
                    accountStatsLoader.setCPM(data.cpm);
                    accountStatsLoader.setCTR(data.ctr);
                    accountStatsLoader.setCPP(data.cpp);
                    accountStatsLoader.setSpend(data.spend);
                    accountStatsLoader.setStats_Date(Stats_Date);

                    accountStatsLoader.setAge_Range("");
                    accountStatsLoader.setGender("null");

                    //boolean success=productLevelAccountStatsDAO.storeaccountlevelstats(accountStatsLoader);

                    accountStatsLoaderList.add(accountStatsLoader);

                }

                /*
                for(AccountStatsLoader accountStatsLoadertest:accountStatsLoaderList){

                    System.out.println(accountStatsLoadertest.getGender());
                }
                */
                /*
                calling the method to store the data into database
                 */
                productLevelAccountStatsDAO.storeaccountlevelstats(accountStatsLoaderList);
//for storing into file for email

                List<CSVProductLevelAccountStats> csvProductLevelAccountStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvProductLevelAccountStatsList=productLevelAccountStatsDAO.fileproductlevelaccountstats(Client_ID, emailstatsdate);
                //call the CSV file writer
                String stored= ProductLevelAccountCSVWriter.writecsvfile(csvProductLevelAccountStatsList, Client_ID, emailstatsdate);

                if(stored!=null){
                    store_file_name=stored;
                }




            }

            reader.close();

        } catch (ClientProtocolException e) {
            logger.info("ClientProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("ProductLevelAccountStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("ProductLevelAccountStats HTTP Response NullPointerException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }


        httpClient.close();
        return store_file_name;
    }
}
