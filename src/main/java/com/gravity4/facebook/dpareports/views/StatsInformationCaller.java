package com.gravity4.facebook.dpareports.views;

/**
 * Created by niranjan on 6/19/15.
 */

import com.gravity4.facebook.dpareports.api.*;
import com.gravity4.facebook.dpareports.dao.AccountInformationDAO;
import com.gravity4.facebook.dpareports.model.AccountEmailLoader;
import com.gravity4.facebook.dpareports.model.AccountInformationLoader;
import com.gravity4.facebook.dpareports.utils.CSVToExcel;
import com.gravity4.facebook.dpareports.utils.SuccessEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Service
@SuppressWarnings("unchecked")
public class StatsInformationCaller {

    @Autowired
    AccountInformationDAO accountInformationDAO;
    /*
    get the account information for all clients
     */
    public void getAccountInformation() throws IOException, PropertyVetoException {

        List<AccountInformationLoader> accountInformationLoaderList;


        try {
            //get the account information
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

    @Autowired
    CSVToExcel csvToExcel;

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
        String Client_Name=accountInformationLoader.getBusiness_Name();

        /*
        Calling the method to get Account Level Statistics
         */
        String accountstats = productLevelAccountStats.getAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        /*
        Calling the method to get overall account stats
         */
        String overallaccountstats = overAllAccountStats.getOverAllAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        /*
        Calling the method to get Campaign Level Statistics
         */
        String campaignstats = productLevelCampaignStats.getCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

         /*
        Calling the method to get OverAllCampaign Level Statistics
         */
        String overallcampaignstats = overAllCampaignStats.getOverAllCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        /*
        Calling the method to get AdSet Level Statistics
         */
        String adsetstats = productLevelAdSetStats.getAdsetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        /*
        Calling the method to get OverAllAdSet Level Statistics
         */
        String overalladsetstats = overAllAdSetStats.getOverAllAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

         /*
        Calling the method to get AdGroup Level Statistics
         */
        String adgroupstats = productLevelAdGroupStats.getAdgroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        /*
        Calling the method to get OverAllAdGroup Level Statistics
        */
        String overalladgroupstats = overAllAdGroupStats.getOverAllAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token);

        //adding all the csv files absolute path to the list to add them into a excel file
        List<String> csvfiles = new ArrayList<String>();

        //add all the parameters

            //this is for future tickets to integrate all the mails into one
            if (overallaccountstats != null && accountstats != null && campaignstats != null && overallcampaignstats != null
                    && adsetstats != null && overalladsetstats != null && adgroupstats != null && overalladgroupstats != null) {

                csvfiles.add(overallaccountstats);
                csvfiles.add(accountstats);
                csvfiles.add(campaignstats);
                csvfiles.add(overallcampaignstats);
                csvfiles.add(adsetstats);
                csvfiles.add(overalladsetstats);
                csvfiles.add(adgroupstats);
                csvfiles.add(overalladgroupstats);

                String Excel_File_Name = getExcelFile(csvfiles,Client_Name);
                logger.info("Excel File Created:" + Excel_File_Name);


                List<AccountEmailLoader> accountEmailLoaderList;

                //get the mailing list
                accountEmailLoaderList = accountInformationDAO.getAccountEmailInformation(Ad_Account_ID_Integer);

                for (AccountEmailLoader accountEmailLoader : accountEmailLoaderList) {

                    String receiver_email_address = accountEmailLoader.getEmail_ID();

                    successEmail.sendemail("DPA Statistics Succesfully stored for Client:",
                            "DPA Statistics Succesfully stored for Client:", client_id, Excel_File_Name, Client_Name,receiver_email_address);
                }
                }


    }

    public String getExcelFile(List<String> csvfiles,String Client_Name){

        boolean csv_to_excel_success=false;
        //get the number of csv files
        int number_of_files = csvfiles.size();

        //create object for xls files
        HSSFWorkbook workBook = new HSSFWorkbook();

        //create the Excel file in the filesystem
        String Excel_File_Name = csvToExcel.getExcelFileName(Client_Name);
        System.out.println("Excel File Name:"+Excel_File_Name);

        for(int i=0; i < number_of_files; i++){

            String csv_name=csvfiles.get(i).toString();
            String second_part=csv_name.split("DPAStats")[1];
            int start_index=second_part.indexOf("_");
            int finish_index=second_part.indexOf(".");
            String Sheet_Name=second_part.substring(start_index,finish_index);

            csv_to_excel_success=csvToExcel.csvToXLS(workBook,csv_name, Sheet_Name +"_"+ Client_Name,Excel_File_Name);

        }

        if(csv_to_excel_success) {
            return Excel_File_Name;
        }
        else{
            return null;
        }
    }

}
