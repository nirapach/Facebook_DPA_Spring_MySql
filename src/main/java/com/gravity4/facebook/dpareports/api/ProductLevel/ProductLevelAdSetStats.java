package com.gravity4.facebook.dpareports.api.ProductLevel;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.ProductLevel.ProductLevelAdSetCSVWriter;
import com.gravity4.facebook.dpareports.dao.ProductLevel.ProductLevelAdSetStatsDAO;
import com.gravity4.facebook.dpareports.model.APImodels.AggregatedandProductLevel.AdSetStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVModels.ProductLevel.CSVProductLevelAdSetStats;
import com.gravity4.facebook.dpareports.responseparser.responsedata.ProductLevel.ProductLevelAdSetStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevel.ProductLevelAdSetResultData;
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
public class ProductLevelAdSetStats {

    Logger logger = LoggerFactory.getLogger(ProductLevelAdSetStats.class);

    @Autowired
    ProductLevelAdSetStatsDAO productLevelAdSetStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
       Makes a Get API call to reportstats API to get the statistics at the AdSet Level
        */
    public String getAdsetstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token,String csv_address) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        //String time_ranges = "[{\"time_start\":1437534000,\"time_stop\":1438657200}]";
        String date_preset = "yesterday";
        String data_columns = "['campaign_name','spend','product_id','total_actions'," +
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
                ProductLevelAdSetStatsJSONResponse response = gson.fromJson(jsonfeed, ProductLevelAdSetStatsJSONResponse.class);

                List<ProductLevelAdSetResultData> results = response.data;

                AdSetStatsLoader adSetStatsLoader;
                List<AdSetStatsLoader> adSetStatsLoaderList = new ArrayList<AdSetStatsLoader>();

                for (ProductLevelAdSetResultData resultData : results) {

                    adSetStatsLoader = new AdSetStatsLoader();


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


                    adSetStatsLoader.setClient_ID(Client_ID);
                    adSetStatsLoader.setAdSet_ID(Long.parseLong(resultData.campaign_id.trim()));
                    adSetStatsLoader.setAdSet_Name(resultData.campaign_name);
                    adSetStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                    adSetStatsLoader.setActivity_End_Date(Activity_End_Date);
                    adSetStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    adSetStatsLoader.setProduct_ID(resultData.product_id);
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

                    adSetStatsLoader.setAge_Range("");
                    adSetStatsLoader.setGender("null");

                    adSetStatsLoaderList.add(adSetStatsLoader);


                }
                productLevelAdSetStatsDAO.storeadsetlevelstats(adSetStatsLoaderList);

                List<CSVProductLevelAdSetStats> csvProductLevelAdSetStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvProductLevelAdSetStatsList=productLevelAdSetStatsDAO.fileproductlevelaccountstats(Client_ID, emailstatsdate);
                //call the CSV file writer
                String stored= ProductLevelAdSetCSVWriter.writecsvfile(csvProductLevelAdSetStatsList, Client_ID, emailstatsdate,csv_address);

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
            logger.info("ProductLevelAdSetStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("ProductLevelAdSetStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }

        httpClient.close();
        return store_file_name;


    }
}

