package com.gravity4.facebook.dpareports.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by niranjan on 6/30/15.
 */
public class SuccessEmail {

    public void sendemail(String subject, String message, Long Client_ID) throws IOException {


        Logger logger = LoggerFactory.getLogger(OAuthExpirationTokenChecker.class);

        String propertyFileName = "config.properties";

        Properties config = new Properties();

        Email email = new SimpleEmail();

        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

        if (inputstream != null)

        {
            try {
                config.load(inputstream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else

        {
            logger.info(String.valueOf("Configuration Properties File'" + propertyFileName + "' not found in classpath"));
            try {
                throw new FileNotFoundException("Configuration Properties File'" + propertyFileName + "' not found in classpath");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        String hostname;
        int smtpport;
        String sendermailaddress;
        String sendermailpassword;
        String sendermailname;
        String receiveremailaddress = "sundi@gravity4.com";

        //need to get the list of mail id's for sending emails
        try {
            hostname = config.getProperty("hostname");
            smtpport = Integer.parseInt(config.getProperty("smtpport"));
            sendermailaddress = config.getProperty("sendermailaddress");
            sendermailpassword = config.getProperty("sendermailpassword");
            sendermailname = config.getProperty("sendermailname");

            email.setHostName(hostname);
            email.setSmtpPort(smtpport);
            email.setAuthentication(sendermailaddress, sendermailpassword);
            email.setStartTLSEnabled(true);
            email.setFrom(sendermailaddress, sendermailname);
            email.setSubject(subject + Client_ID);
            email.setMsg(message + Client_ID);
            email.addTo(receiveremailaddress);
            email.send();
            logger.info("Email Notification Sent Successfully for Application:" + Client_ID);


        } catch (EmailException ee) {
            ee.printStackTrace();
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            throw new IOException("Problem with configuration properties file", e);

        }
    }

}
