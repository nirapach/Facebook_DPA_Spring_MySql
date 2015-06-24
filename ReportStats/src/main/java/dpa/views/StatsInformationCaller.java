package dpa.views;

/**
 * Created by niranjan on 6/19/15.
 */

import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import dpa.model.AccountInformationLoader;
import dpa.controller.AccountInformationDAO;
import dpa.api.AccountStats;
import dpa.api.AdGroupStats;
import dpa.api.AdSetStats;
import dpa.api.CampaignStats;
import dpa.responseparser.resultdata.AccountsResultData;
import dpa.responseparser.resultdata.AdGroupResultData;
import dpa.responseparser.resultdata.AdSetResultData;
import dpa.responseparser.resultdata.CampaignResultData;

public class StatsInformationCaller {


    /*
    get the account information for all clients
     */
    public static void getAccountInformation() throws IOException, PropertyVetoException {

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
    public static void getAllStats(AccountInformationLoader accountInformationLoader) throws IOException, URISyntaxException {

        /*
        objects for all the stats create here statically
         */

        AccountStats accountStats=new AccountStats();
        AdGroupStats adGroupStats= new AdGroupStats();
        AdSetStats adSetStats=new AdSetStats();
        CampaignStats campaignStats= new CampaignStats();

        /*
        extract the account ID from each accountInformationLoader Object
         */
        int Ad_Account_ID_Integer = accountInformationLoader.getAd_Account_ID();
        int Client_ID_Integer=accountInformationLoader.getApplication_Client_ID();
        String Access_Token=accountInformationLoader.getAccess_Token();

        /*
        Calling the method to get Account Level Statistics
         */
        accountStats.getAccountstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        /*
        Calling the method to get Campaign Level Statistics
         */
        campaignStats.getCampaignstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

        /*
        Calling the method to get AdSet Level Statistics
         */
        adSetStats.getAdsetstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);

         /*
        Calling the method to get AdGroup Level Statistics
         */
        adGroupStats.getAdgroupstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);


    }


}
