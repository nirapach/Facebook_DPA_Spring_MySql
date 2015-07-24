package com.gravity4.facebook.dpareports.api;

/**
 * Created by niranjan on 6/18/15.
 */

import com.google.gson.Gson;
import com.gravity4.facebook.dpareports.CSVFileWriter.ProductLevelCampaignCSVWriter;
import com.gravity4.facebook.dpareports.dao.ProductLevelCampaignStatsDAO;
import com.gravity4.facebook.dpareports.model.CSVProductLevelCampaignStats;
import com.gravity4.facebook.dpareports.model.CampaignStatsLoader;
import com.gravity4.facebook.dpareports.responseparser.responsedata.ProductLevelCampaignStatsJSONResponse;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevelCampaignResultData;
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
public class ProductLevelCampaignStats {

    private ProductLevelCampaignStats(){}

    Logger logger = LoggerFactory.getLogger(ProductLevelCampaignStats.class);

    @Autowired
    ProductLevelCampaignStatsDAO productLevelCampaignStatsDAO;

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
    Makes a Get API call to reportstats API to get the statistics at the Campaign Level
     */
    public String getCampaignstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException {

        //Fields in the parameters
        long Client_ID = Client_ID_Integer;
        String store_file_name = null;
        String Account_ID = Long.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "['campaign_group_name','spend','product_id','total_actions'," +
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
                ProductLevelCampaignStatsJSONResponse response = gson.fromJson(jsonfeed, ProductLevelCampaignStatsJSONResponse.class);

                List<ProductLevelCampaignResultData> results = response.data;

                CampaignStatsLoader campaignStatsLoader;
                List<CampaignStatsLoader> campaignStatsLoaderList = new ArrayList<CampaignStatsLoader>();

                for (ProductLevelCampaignResultData resultData : results) {

                    campaignStatsLoader = new CampaignStatsLoader();

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
                    campaignStatsLoader.setClient_ID(Client_ID);
                    campaignStatsLoader.setCampaign_ID(Long.parseLong(resultData.campaign_group_id.trim()));
                    campaignStatsLoader.setCampaign_Name(resultData.campaign_group_name);
                    campaignStatsLoader.setActivity_Start_Date(Activity_Start_Date);
                    campaignStatsLoader.setActivity_End_Date(Activity_End_Date);
                    campaignStatsLoader.setCost_Per_Unique_Click(resultData.cost_per_unique_click);
                    campaignStatsLoader.setProduct_ID(resultData.product_id);
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

                    campaignStatsLoader.setAge_Range("");
                    campaignStatsLoader.setGender("null");

                    campaignStatsLoaderList.add(campaignStatsLoader);

                }
                //calling method to store the data into DB
                productLevelCampaignStatsDAO.storecampaignlevelstats(campaignStatsLoaderList);


                List<CSVProductLevelCampaignStats> csvProductLevelCampaignStatsList;
                java.sql.Date emailstatsdate = new java.sql.Date(StatisticsDate.getYesterday().getTime());
                csvProductLevelCampaignStatsList=productLevelCampaignStatsDAO.fileproductlevelcampaignstats(Client_ID, emailstatsdate);
                //call the CSV file writer
                String stored= ProductLevelCampaignCSVWriter.writecsvfile(csvProductLevelCampaignStatsList, Client_ID, emailstatsdate);

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
            logger.info("ProductLevelCampaignStats HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("ProductLevelCampaignStats HTTP Response NullPinterException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        httpClient.close();
        return store_file_name;

    }
}
