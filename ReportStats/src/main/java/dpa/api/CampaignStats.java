package dpa.api;

/**
 * Created by niranjan on 6/18/15.
 */

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;


public class CampaignStats {

    Logger logger= LoggerFactory.getLogger(CampaignStats.class);

    /*
    Makes a Get API call to reportstats API to get the statistics at the Campaign Level
     */
    public void getCampaignstats(int Account_ID_Integer,int Client_ID_Integer,String Access_Token) throws URISyntaxException, IOException {

        //Fields in the parameters
        String Account_ID = Integer.toString(Account_ID_Integer);
        String date_preset = "yesterday";
        String data_columns = "[\"campaign_group_id\",\"product_id\",\"spend\",\"age\",\"gender\",\"country\"," +
                "\"placement\",\"impression_device\",\"reach\",\"clicks\",\"impressions\",\"frequency\",\"social_reach\"," +
                "\"social_impressions\"," +
                "\"cpm\",\"unique_impressions\",\"unique_social_impressions\",\"cpp\",\"ctr\",\"cpc\"," +
                "\"cost_per_unique_click\",\"ctr\",\"spend\",\"cost_per_unique_click\"";

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
            logger.info("CampaignStats HTTP Response");
            logger.info(String.valueOf(httpResponse));
            e.printStackTrace();
        }

        System.out.println("GET Response Status: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // Save the Json Response
        String jsonresponse = response.toString();
        JSONObject JsonCampaignStats = new JSONObject(jsonresponse);
        httpClient.close();

    }
}
