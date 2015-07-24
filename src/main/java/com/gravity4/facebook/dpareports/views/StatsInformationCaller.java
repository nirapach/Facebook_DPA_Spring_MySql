package com.gravity4.facebook.dpareports.views;

/**
 * Created by niranjan on 6/19/15.
 */

import com.gravity4.facebook.dpareports.api.*;
import com.gravity4.facebook.dpareports.dao.AccountInformationDAO;
import com.gravity4.facebook.dpareports.model.AccountInformationLoader;
import com.gravity4.facebook.dpareports.utils.SuccessEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;


@Service
public class StatsInformationCaller {

    @Autowired
    AccountInformationDAO accountInformationDAO;
    /*
    get the account information for all clients
     */
    public void getAccountInformation() throws IOException, PropertyVetoException {




        List<AccountInformationLoader> accountInformationLoaderList;

        try {
            accountInformationLoaderList = accountInformationDAO.getAccountInformation();
            for (AccountInformationLoader accountInformationLoader : accountInformationLoaderList) {
                /*
                call this method to get each account ID from the table
                 */
                try {
                    getAllStats(accountInformationLoader);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Autowired
    ProductLevelAccountStats productLevelAccountStats;
    @Autowired
    ProductLevelAdGroupStats productLevelAdGroupStats;
    @Autowired
    ProductLevelAdSetStats productLevelAdSetStats;
    @Autowired
    ProductLevelCampaignStats productLevelCampaignStats;
    @Autowired
    OverAllAccountStats overAllAccountStats;
    @Autowired
    OverAllAdGroupStats overAllAdGroupStats;
    @Autowired
    OverAllAdSetStats overAllAdSetStats;
    @Autowired
    OverAllCampaignStats overAllCampaignStats;

    @Autowired
    SuccessEmail successEmail;

    public void getAllStats(AccountInformationLoader accountInformationLoader) throws IOException, URISyntaxException, PropertyVetoException, SQLException {

        /*
        objects for all the stats create here statically
         */
        Logger logger = LoggerFactory.getLogger(StatsInformationCaller.class);
        long client_id = accountInformationLoader.getApplication_Client_ID();

        /*
        extract the account ID from each accountInformationLoader Object
         */
        long Ad_Account_ID_Integer = accountInformationLoader.getAd_Account_ID();
        long Client_ID_Integer = accountInformationLoader.getApplication_Client_ID();
        String Access_Token = accountInformationLoader.getAccess_Token();

        /*
        Calling the method to get Account Level Statistics
         */
        String accountstats = productLevelAccountStats.getAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);
        //right now just the sample code is given,need to finalize on the email list and the subject header
        if (accountstats!=null) {
            logger.info("Sending Email Notification for accountstats");
            successEmail.sendemail("Account Statistics at Product Level Succesfully stored for Client Page:",
                    "Account Statistics at Product Level Succesfully stored for Client Page:", client_id,accountstats);
        }

        /*
        Calling the method to get overall account stats
         */

        String overallaccountstats = overAllAccountStats.getOverAllAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);
        //right now just the sample code is given,need to finalize on the email list and the subject header
        if (overallaccountstats!=null) {
            successEmail.sendemail("Account Statistics Overall Succesfully stored for Client Page:",
                    "Account Statistics Overall Succesfully stored for Client Page:", client_id,overallaccountstats);
        }

        /*
        Calling the method to get Campaign Level Statistics
         */
        String campaignstats = productLevelCampaignStats.getCampaignstats(Ad_Account_ID_Integer,Client_ID_Integer, Access_Token);

        if (campaignstats!=null) {

            successEmail.sendemail("Campaign Statistics at Product Level Succesfully stored for Client Page:",
                    "Campaign Statistics at Product Level Succesfully stored for Client Page:", client_id,campaignstats);
        }

         /*
        Calling the method to get OverAllCampaign Level Statistics
         */
        String overallcampaignstats = overAllCampaignStats.getOverAllCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if (overallcampaignstats!=null) {
            successEmail.sendemail("Campaign Statistics Overall Succesfully stored for Client Page:",
                    "Campaign Statistics Overall Succesfully stored for Client Page:", client_id,overallcampaignstats);
        }


        /*
        Calling the method to get AdSet Level Statistics
         */
        String adsetstats = productLevelAdSetStats.getAdsetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if (adsetstats!=null) {
            successEmail.sendemail("Adset Statistics at Product Level Succesfully stored for Client Page:",
                    "Adset Statistics at Product Level Succesfully stored for Client Page:", client_id,adsetstats);
        }
        /*
        Calling the method to get OverAllAdSet Level Statistics
         */
        String overalladsetstats = overAllAdSetStats.getOverAllAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if (overalladsetstats!=null) {

            successEmail.sendemail("Adset Statistics overall Succesfully stored for Client Page:",
                    "Adset Statistics overall Succesfully stored for Client Page:", client_id,overalladsetstats);
        }
         /*
        Calling the method to get AdGroup Level Statistics
         */
       String adgroupstats = productLevelAdGroupStats.getAdgroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if (adgroupstats!=null) {

            successEmail.sendemail("AdGroup Statistics at Product Level Succesfully stored for Client Page:",
                    "AdGroup Statistics at Product Level Succesfully stored for Client Page:", client_id,adgroupstats);
        }

        /*
        Calling the method to get OverAllAdGroup Level Statistics
        */
       String overalladgroupstats = overAllAdGroupStats.getOverAllAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if (overalladgroupstats!=null) {

            successEmail.sendemail("AdGroup Statistics Overall Succesfully stored for Client Page:",
                    "AdGroup Statistics Overall Succesfully stored for Client Page:", client_id,overalladgroupstats);
        }


    }


}
