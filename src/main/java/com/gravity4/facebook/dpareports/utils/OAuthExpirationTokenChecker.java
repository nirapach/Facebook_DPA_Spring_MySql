package com.gravity4.facebook.dpareports.utils;

/**
 * Created by niranjan on 6/25/15.
 */

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Service
public class OAuthExpirationTokenChecker {

    private OAuthExpirationTokenChecker()
    {}


    String receiveremailaddress = "sundi@gravity4.com";
    @Value("${smtp.hostname}")
    String hostname;
    @Value("${smtp.smtpport}")
    int smtpport;
    @Value("${smtp.sendermailaddress}")
    String sendermailaddress;
    @Value("${smtp.sendermailpassword}")
    String sendermailpassword;
    @Value("${smtp.sendermailname}")
    String sendermailname;

    public boolean checkOAuthTokenException(BufferedReader OAuthreader, long client_id) throws IOException {
        Logger logger = LoggerFactory.getLogger(OAuthExpirationTokenChecker.class);

          /* to test whether the JSON response is oauth authentication error or actually the stats response*/
        BufferedReader reader = OAuthreader;
        String inputLine;
        boolean status = false;
        StringBuffer fbresponse = new StringBuffer();

        Email email = new SimpleEmail();

        try {
            while ((inputLine = reader.readLine()) != null) {
                fbresponse.append(inputLine);
            }
            /*convert the reader to JSON Object to check whether it is a Error or Actual Data Response*/
            String jsonfeed = fbresponse.toString();
            JSONObject JSONFeed_ID = new JSONObject(jsonfeed);


            /*
            checking whether the response has a error field
             */
            String error_property = "error";
            int error_code;
            if (JSONFeed_ID.has(error_property)) {

                //status 1 means JSON response does not have the data object
                JSONObject JSONError_Obj = JSONFeed_ID.getJSONObject(error_property);
                error_code = JSONError_Obj.getInt("code");
                if (error_code == 190) {

                    try {
                        email.setHostName(hostname);
                        email.setSmtpPort(smtpport);
                        email.setAuthentication(sendermailaddress, sendermailpassword);
                        email.setStartTLSEnabled(true);
                        email.setFrom(sendermailaddress, sendermailname);
                        email.setSubject("OAUTH Access Token Expiration for Facebook Dynamic Product Ads" +
                                " Campaign for the Business Page:" + client_id);
                        email.setMsg("OAUTH Access Token Expiration for Facebook Dynamic Product Ads Campaign for the Business Page:" + client_id);
                        email.addTo(receiveremailaddress);
                        email.send();
                            /*
                                 if error code is 190 it means OAuth Exception
                             */
                        //logger.info(JSONFeed_ID.getString("type"));
                    } catch (EmailException ee) {
                        ee.printStackTrace();
                    }
                }

            } else if (JSONFeed_ID.has("data")) {
                //status 1 means JSON response has data object
                status = true;
            }
        } catch (IOException e) {
            logger.info(String.valueOf(e));
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            throw new IOException("Problem with configuration properties file", e);

        }

        return status;
    }

}
