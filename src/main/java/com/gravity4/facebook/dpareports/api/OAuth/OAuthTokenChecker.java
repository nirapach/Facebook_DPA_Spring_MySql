package com.gravity4.facebook.dpareports.api.OAuth;

import com.gravity4.facebook.dpareports.utils.OAuthExpirationTokenChecker;
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

/**
 * Created by niranjan on 8/12/15.
 */

@Service
@SuppressWarnings("unchecked")
public class OAuthTokenChecker {

    Logger logger = LoggerFactory.getLogger(OAuthTokenChecker.class);

    @Autowired
    OAuthExpirationTokenChecker oAuthExpirationTokenChecker;

    /*
       Makes a Get API call to reportstats API to get the statistics at the Ad Account Level
        */
    public boolean gettokenstats(long Account_ID_Integer, long Client_ID_Integer, String Access_Token) throws URISyntaxException, IOException, PropertyVetoException, SQLException, HTTPException {

        //Fields in the parameters
        boolean OAuth=false;
        long Client_ID = Client_ID_Integer;
        String Account_ID = Long.toString(Account_ID_Integer);
        //String time_ranges = "[{\"time_start\":1437534000,\"time_stop\":1438657200}]";
        String date_preset = "yesterday";
        String data_columns = "['account_name','spend','total_actions'," +
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
            status = oAuthExpirationTokenChecker.checkOAuthToken(reader, Client_ID);

            if (status) {

                OAuth=true;
            }
            reader.close();

        } catch (ClientProtocolException e) {
            logger.info("OAuthTokenProtocolException ");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            logger.info("OAuthToken HTTP Response IO Exception");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.info("OAuthToken HTTP Response NullPointerException");
            logger.info(String.valueOf(e));
            e.printStackTrace();
        }
        httpClient.close();
        return OAuth;

    }
}
