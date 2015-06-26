package dpa.utils;

/**
 * Created by niranjan on 6/25/15.
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class OAuthExpirationTokenChecker {

    public int checkOAuthTokenException(BufferedReader reader,long client_id) throws IOException {

          /* to test whether the JSON response is oauth authentication error or actually the stats response*/
        String inputLine;
        int status = 0;
        StringBuilder fbresponse = new StringBuilder();
        String propertyFileName = "config.properties";
        Logger logger= LoggerFactory.getLogger(OAuthExpirationTokenChecker.class);
        Properties config = new Properties();

        Email email = new SimpleEmail();

        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

        if (inputstream != null) {
            config.load(inputstream);
        } else {
            logger.info(String.valueOf("Configuration Properties File'" + propertyFileName + "' not found in classpath"));
            throw new FileNotFoundException("Configuration Properties File'" + propertyFileName + "' not found in classpath");
        }

        //need to get the list of mail id's for sending emails
        String receiveremailaddress="sundi@gravity4.com";
        String hostname = config.getProperty("hostname");
        int smtpport = Integer.parseInt(config.getProperty("smtpport"));
        String sendermailusername = config.getProperty("sendermailusername");
        String sendermailpassword = config.getProperty("sendermailpassword");
        String sendermailname = config.getProperty("sendermailname");

        try
        {
            while ((inputLine = reader.readLine()) != null) {
                fbresponse.append(inputLine);
            }
            /*convert the reader to JSON Object to check whether it is a Error or Actual Data Response*/
            String jsonfeed = fbresponse.toString();
            JSONObject JSONFeed_ID = new JSONObject(jsonfeed);

            /*
            checking whether the response has a error field
             */
            String error_property="error";
            int error_code;
            if(JSONFeed_ID.has(error_property)) {

                //status 1 means JSON response does not have the data object
                status=0;
                JSONObject JSONError_Obj = JSONFeed_ID.getJSONObject(error_property);
                error_code=JSONError_Obj.getInt("code");
                         if(error_code== 190) {

                             try {
                                 email.setHostName(hostname);
                                 email.setSmtpPort(smtpport);
                                 email.setAuthentication(sendermailname, sendermailpassword);
                                 email.setStartTLSEnabled(true);
                                 email.setFrom(sendermailname, sendermailusername);
                                 email.setSubject("OAUTH Access Token Expiration for Facebook Dynamic Product Ads" +
                                         " Campaign for the client:"+client_id);
                                 email.setMsg("OAUTH Access Token Expiration for Facebook Dynamic Product Ads Campaign for the client:"+client_id);
                                 email.addTo(receiveremailaddress);
                                 email.send();
                            /*
                                 if error code is 190 it means OAuth Exception
                             */
                                 logger.info(JSONFeed_ID.getString("type"));
                             }catch (EmailException ee) {
                                 ee.printStackTrace();
                             }
                         }

            }
            else if (JSONFeed_ID.has(error_property)) {
                //status 1 means JSON response has data object
                status = 1;
            }
        }catch (IOException e){
            logger.info(String.valueOf(e));
        }
        catch (Exception e) {
            logger.info(String.valueOf(e));
            throw new IOException("Problem with configuration properties file", e);

        }

        return status;
    }

}
