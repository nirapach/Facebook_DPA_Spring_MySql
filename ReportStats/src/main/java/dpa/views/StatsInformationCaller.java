package dpa.views;

/**
 * Created by niranjan on 6/19/15.
 */

import java.beans.PropertyVetoException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import dpa.model.AccountInformationLoader;
import dpa.controller.AccountInformationDAO;
import dpa.api.AccountStats;
import dpa.api.AdGroupStats;
import dpa.api.AdSetStats;
import dpa.api.CampaignStats;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class StatsInformationCaller {


    /*
    get the account information for all clients
     */
    public void getAccountInformation() throws IOException, PropertyVetoException {

        AccountInformationDAO accountInformationDAO= new AccountInformationDAO();

        List<AccountInformationLoader> accountInformationLoaderList;

        try{
            accountInformationLoaderList= accountInformationDAO.getAccountInformation();
            for(AccountInformationLoader accountInformationLoader:accountInformationLoaderList ){
                /*
                call this method to get each account ID from the table
                 */
                try {
                    getAllStats(accountInformationLoader);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public void getAllStats(AccountInformationLoader accountInformationLoader) throws IOException, URISyntaxException, PropertyVetoException, SQLException {

        /*
        objects for all the stats create here statically
         */
        Logger logger= LoggerFactory.getLogger(StatsInformationCaller.class);
        Properties config = new Properties();
        String propertyFileName = "config.properties";

        Email email = new SimpleEmail();

        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

        if (inputstream != null) {
            config.load(inputstream);
        } else {
            logger.info(String.valueOf("Configuration Properties File'" + propertyFileName + "' not found in classpath"));
            throw new FileNotFoundException("Configuration Properties File'" + propertyFileName + "' not found in classpath");
        }

        String hostname;
        int smtpport;
        String sendermailusername;
        String sendermailpassword;
        String sendermailname;
        //need to get the list of mail id's for sending emails
        String receiveremailaddress="example@example.com";
        try {
            hostname = config.getProperty("hostname");
            smtpport = Integer.parseInt(config.getProperty("smtpport"));
            sendermailusername = config.getProperty("sendermailusername");
            sendermailpassword = config.getProperty("sendermailpassword");
            sendermailname = config.getProperty("sendermailname");
        }
        catch (Exception e) {
            logger.info(String.valueOf(e));
            throw new IOException("Problem with configuration properties file", e);

        }

        AccountStats accountStats=new AccountStats();
        AdGroupStats adGroupStats= new AdGroupStats();
        AdSetStats adSetStats=new AdSetStats();
        CampaignStats campaignStats= new CampaignStats();

        /*
        extract the account ID from each accountInformationLoader Object
         */
        long Ad_Account_ID_Integer = accountInformationLoader.getAd_Account_ID();
        long Client_ID_Integer=accountInformationLoader.getApplication_Client_ID();
        String Access_Token=accountInformationLoader.getAccess_Token();

        /*
        Calling the method to get Account Level Statistics
         */
        boolean accountstats = accountStats.getAccountstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);
        //right now just the sample code is given,need to finalize on the email list and the subject header
        if(accountstats) {
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailname, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailname, sendermailusername);
                email.setSubject("Account Level Statistics got uploaded to the database");
                email.setMsg("Account Level Statistics got uploaded to the database");
                email.addTo(receiveremailaddress);
                email.send();
                logger.info("Account level stats Email Notification Sent Successfully");
            } catch (EmailException ee) {
                ee.printStackTrace();
            }
        }

        /*
        Calling the method to get Campaign Level Statistics
         */
        boolean campaignstats=campaignStats.getCampaignstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(campaignstats) {
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailname, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailname, sendermailusername);
                email.setSubject("Campaign Level Statistics got uploaded to the database");
                email.setMsg("Campaign Level Statistics got uploaded to the database");
                email.addTo(receiveremailaddress);
                email.send();
                logger.info("Campaign level stats Email Notification Sent Successfully");
            } catch (EmailException ee) {
                ee.printStackTrace();
            }
        }
        /*
        Calling the method to get AdSet Level Statistics
         */
        boolean adsetstats=adSetStats.getAdsetstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(adsetstats) {
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailname, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailname, sendermailusername);
                email.setSubject("AdSet Level Statistics got uploaded to the database");
                email.setMsg("AdSet Level Statistics got uploaded to the database");
                email.addTo(receiveremailaddress);
                email.send();
                logger.info("AdSet level stats Email Notification Sent Successfully");
            } catch (EmailException ee) {
                ee.printStackTrace();
            }
        }
         /*
        Calling the method to get AdGroup Level Statistics
         */
        boolean adgroupstats=adGroupStats.getAdgroupstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(adgroupstats) {
            try {
                email.setHostName(hostname);
                email.setSmtpPort(smtpport);
                email.setAuthentication(sendermailname, sendermailpassword);
                email.setStartTLSEnabled(true);
                email.setFrom(sendermailname, sendermailusername);
                email.setSubject("AdGroup Level Statistics got uploaded to the database");
                email.setMsg("AdGroup Level Statistics got uploaded to the database");
                email.addTo(receiveremailaddress);
                email.send();
                logger.info("AdGroup level stats Email Notification Sent Successfully");
            } catch (EmailException ee) {
                ee.printStackTrace();
            }
        }

    }


}
