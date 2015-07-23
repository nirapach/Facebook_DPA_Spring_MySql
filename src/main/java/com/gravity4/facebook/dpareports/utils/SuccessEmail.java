package com.gravity4.facebook.dpareports.utils;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by niranjan on 6/30/15.
 */
@Service
public class SuccessEmail {

    @Value("${smtp.receivermailaddress}")
    String receiveremailaddress;
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
    @Value("${smtp.enable}")
    boolean enabled;

    private SuccessEmail() {
    }

    public void sendemail(String subject, String message, Long Client_ID) throws IOException {

        Logger logger = LoggerFactory.getLogger(SuccessEmail.class);

        Email email = new SimpleEmail();
        if (enabled) {

            logger.info("Inside Success Email"+enabled);
            //need to get the list of mail id's for sending emails
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailaddress, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailaddress, sendermailname);
                email.setSubject(subject + Client_ID);
                email.setMsg(message + Client_ID);
                email.addTo(receiveremailaddress);
                email.send();
                logger.info("Email Notification Sent Successfully for Application's business page:" + Client_ID);


            } catch (EmailException ee) {
                ee.printStackTrace();
            } catch (Exception e) {
                logger.info(String.valueOf(e));
                throw new IOException("Problem with configuration properties file", e);

            }
        }
    }

}
