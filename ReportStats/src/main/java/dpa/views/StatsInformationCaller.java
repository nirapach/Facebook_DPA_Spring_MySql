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

import dpa.api.*;
import dpa.model.AccountInformationLoader;
import dpa.controller.AccountInformationDAO;
import dpa.utils.SuccessEmail;
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
        long client_id=accountInformationLoader.getApplication_Client_ID();


        SuccessEmail email=new SuccessEmail();

        /*
        Creating objects for calling the method to make the API call
         */

        ProductLevelAccountStats productLevelAccountStats =new ProductLevelAccountStats();
        ProductLevelAdGroupStats productLevelAdGroupStats = new ProductLevelAdGroupStats();
        ProductLevelAdSetStats productLevelAdSetStats =new ProductLevelAdSetStats();
        ProductLevelCampaignStats productLevelCampaignStats = new ProductLevelCampaignStats();
        OverAllAccountStats overAllAccountStats=new OverAllAccountStats();
        OverAllAdGroupStats overAllAdGroupStats= new OverAllAdGroupStats();
        OverAllAdSetStats overAllAdSetStats=new OverAllAdSetStats();
        OverAllCampaignStats overAllCampaignStats= new OverAllCampaignStats();


        /*
        extract the account ID from each accountInformationLoader Object
         */
        long Ad_Account_ID_Integer = accountInformationLoader.getAd_Account_ID();
        long Client_ID_Integer=accountInformationLoader.getApplication_Client_ID();
        String Access_Token=accountInformationLoader.getAccess_Token();

        /*
        Calling the method to get Account Level Statistics
         */
        boolean accountstats = productLevelAccountStats.getAccountstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);
        //right now just the sample code is given,need to finalize on the email list and the subject header
        if(accountstats) {
            email.sendemail("Account Statistics at Product Level Succesfully stored for:",
                    "Account Statistics at Product Level Succesfully stored for:",client_id);


        }

        /*
        Calling the method to get overall account stats
         */

        boolean overallaccountstats = overAllAccountStats.getOverAllAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);
        //right now just the sample code is given,need to finalize on the email list and the subject header
        if(overallaccountstats) {
            email.sendemail("Account Statistics Overall Succesfully stored for:",
                    "Account Statistics Overall Succesfully stored for:",client_id);
        }

        /*
        Calling the method to get Campaign Level Statistics
         */
        boolean campaignstats= productLevelCampaignStats.getCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if(campaignstats) {

            email.sendemail("Campaign Statistics at Product Level Succesfully stored for Application:",
                    "Campaign Statistics at Product Level Succesfully stored for Application:",client_id);
        }

         /*
        Calling the method to get OverAllCampaign Level Statistics
         */
        boolean overallcampaignstats= overAllCampaignStats.getOverAllCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if(overallcampaignstats) {
            email.sendemail("Campaign Statistics Overall Succesfully stored for Application:",
                    "Campaign Statistics Overall Succesfully stored for application:",client_id);
        }
        /*
        Calling the method to get AdSet Level Statistics
         */
        boolean adsetstats= productLevelAdSetStats.getAdsetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        if(adsetstats) {
            email.sendemail("Adset Statistics at Product Level Succesfully stored for Application:",
                    "Adset Statistics at Product Level Succesfully stored for Application:",client_id);
        }
        /*
        Calling the method to get OverAllAdSet Level Statistics
         */
        boolean overalladsetstats= overAllAdSetStats.getOverAllAdSetstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(overalladsetstats) {

            email.sendemail("Adset Statistics overall Succesfully stored for Application:",
                    "Adset Statistics overall Succesfully stored for application:",client_id);
        }
         /*
        Calling the method to get AdGroup Level Statistics
         */
        boolean adgroupstats= productLevelAdGroupStats.getAdgroupstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(adgroupstats) {

            email.sendemail("AdGroup Statistics at Product Level Succesfully stored for Application:",
                    "AdGroup Statistics at Product Level Succesfully stored for Application:",client_id);
        }

        /*
        Calling the method to get OverAllAdGroup Level Statistics
        */
        boolean overalladgroupstats= overAllAdGroupStats.getOverAllAdGroupstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        if(overalladgroupstats) {

            email.sendemail("AdGroup Statistics Overall Succesfully stored for Application:",
                    "AdGroup Statistics Overall Succesfully stored for Application:",client_id);
        }

    }


}
