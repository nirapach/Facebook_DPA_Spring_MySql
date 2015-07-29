package com.gravity4.facebook.dpareports.utils;

import org.apache.commons.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by niranjan on 6/30/15.
 */
@Service
@SuppressWarnings("unchecked")
public class SuccessEmail {

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

    public void sendemail(String subject, String message, Long Client_ID,String filename,String Client_Name,String receiver_email) throws IOException {

        Logger logger = LoggerFactory.getLogger(SuccessEmail.class);

        String att=filename;
        String[] split=att.split("/Excel_Files/");
        String att_name=split[1];

        EmailAttachment attachment = new EmailAttachment();
        attachment.setPath(filename);
        attachment.setDisposition(EmailAttachment.ATTACHMENT);
        attachment.setDescription("DPA Campaign Reports");
        attachment.setName(att_name);

        MultiPartEmail email = new MultiPartEmail();

        if (enabled) {

            logger.info("Inside Success Email"+enabled);
            //need to get the list of mail id's for sending emails
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailaddress, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailaddress, sendermailname);
                email.setSubject(subject + Client_Name + "_" + Client_ID);
                email.setMsg(message + Client_Name+"_"+Client_ID);
                email.addTo(receiver_email);
                // add the attachment
                email.attach(attachment);
                email.send();
                logger.info("Email Notification Sent Successfully for Application's business page:" + Client_ID+"_"+Client_Name);


            } catch (EmailException ee) {
                ee.printStackTrace();
            } catch (Exception e) {
                logger.info(String.valueOf(e));
                throw new IOException("Problem with configuration properties file", e);

            }
        }
    }

}
