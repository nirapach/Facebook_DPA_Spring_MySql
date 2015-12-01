package com.gravity4.facebook.dpareports.views;

/**
 * Created by niranjan on 6/19/15.
 */

import com.gravity4.facebook.dpareports.api.Actions.ActionTypeAccountStats;
import com.gravity4.facebook.dpareports.api.Actions.ActionTypeAdGroupStats;
import com.gravity4.facebook.dpareports.api.Actions.ActionTypeAdSetStats;
import com.gravity4.facebook.dpareports.api.Actions.ActionTypeCampaignStats;
import com.gravity4.facebook.dpareports.api.AgeandGender.OverAllAccountStats;
import com.gravity4.facebook.dpareports.api.AgeandGender.OverAllAdGroupStats;
import com.gravity4.facebook.dpareports.api.AgeandGender.OverAllAdSetStats;
import com.gravity4.facebook.dpareports.api.AgeandGender.OverAllCampaignStats;
import com.gravity4.facebook.dpareports.api.Aggregated.AggregatedAccountStats;
import com.gravity4.facebook.dpareports.api.Aggregated.AggregatedAdGroupStats;
import com.gravity4.facebook.dpareports.api.Aggregated.AggregatedAdSetStats;
import com.gravity4.facebook.dpareports.api.Aggregated.AggregatedCampaignStats;
import com.gravity4.facebook.dpareports.api.Country.CountryAccountStats;
import com.gravity4.facebook.dpareports.api.Country.CountryAdGroupStats;
import com.gravity4.facebook.dpareports.api.Country.CountryAdSetStats;
import com.gravity4.facebook.dpareports.api.Country.CountryCampaignStats;
import com.gravity4.facebook.dpareports.api.OAuth.OAuthTokenChecker;
import com.gravity4.facebook.dpareports.api.Placement.PlacementAccountStats;
import com.gravity4.facebook.dpareports.api.Placement.PlacementAdGroupStats;
import com.gravity4.facebook.dpareports.api.Placement.PlacementAdSetStats;
import com.gravity4.facebook.dpareports.api.Placement.PlacementCampaignStats;
import com.gravity4.facebook.dpareports.api.ProductLevel.ProductLevelAccountStats;
import com.gravity4.facebook.dpareports.api.ProductLevel.ProductLevelAdGroupStats;
import com.gravity4.facebook.dpareports.api.ProductLevel.ProductLevelAdSetStats;
import com.gravity4.facebook.dpareports.api.ProductLevel.ProductLevelCampaignStats;
import com.gravity4.facebook.dpareports.api.Region.RegionAccountStats;
import com.gravity4.facebook.dpareports.api.Region.RegionAdGroupStats;
import com.gravity4.facebook.dpareports.api.Region.RegionAdSetStats;
import com.gravity4.facebook.dpareports.api.Region.RegionCampaignStats;
import com.gravity4.facebook.dpareports.dao.AccountInformationDAO;
import com.gravity4.facebook.dpareports.model.APImodels.AccountEmailLoader;
import com.gravity4.facebook.dpareports.model.APImodels.AccountInformationLoader;
import com.gravity4.facebook.dpareports.utils.CSVToExcel;
import com.gravity4.facebook.dpareports.utils.OAuthExpirationTokenChecker;
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
    public void getAccountInformation(String csv_address,String excel_address) throws IOException, PropertyVetoException {

        List<AccountInformationLoader> accountInformationLoaderList;


        try {
            //get the account information
            accountInformationLoaderList = accountInformationDAO.getAccountInformation();

            for (AccountInformationLoader accountInformationLoader : accountInformationLoaderList) {
                /*
                call this method to get each account ID from the table
                 */
                try {
                    getAllStats(accountInformationLoader,csv_address,excel_address);
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

        //Autowired the instances for all the API call classes

    @Autowired
    AggregatedAccountStats aggregatedAccountStats;
    @Autowired
    AggregatedAdGroupStats aggregatedAdGroupStats;
    @Autowired
    AggregatedAdSetStats aggregatedAdSetStats;
    @Autowired
    AggregatedCampaignStats aggregatedCampaignStats;
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
    CountryAccountStats countryAccountStats;
    @Autowired
    CountryCampaignStats countryCampaignStats;
    @Autowired
    CountryAdSetStats countryAdSetStats;
    @Autowired
    CountryAdGroupStats countryAdGroupStats;
    @Autowired
    RegionAccountStats regionAccountStats;
    @Autowired
    RegionAdGroupStats regionAdGroupStats;
    @Autowired
    RegionAdSetStats regionAdSetStats;
    @Autowired
    RegionCampaignStats regionCampaignStats;
    @Autowired
    PlacementAccountStats placementAccountStats;
    @Autowired
    PlacementAdGroupStats placementAdGroupStats;
    @Autowired
    PlacementAdSetStats placementAdSetStats;
    @Autowired
    PlacementCampaignStats placementCampaignStats;
    @Autowired
    ActionTypeAdGroupStats actionTypeAdGroupStats;
    @Autowired
    ActionTypeAdSetStats actionTypeAdSetStats;
    @Autowired
    ActionTypeCampaignStats actionTypeCampaignStats;
    @Autowired
    ActionTypeAccountStats actionTypeAccountStats;

    @Autowired
    SuccessEmail successEmail;

    @Autowired
    OAuthTokenChecker oAuthTokenChecker;

    @Autowired
    CSVToExcel csvToExcel;

    public void getAllStats(AccountInformationLoader accountInformationLoader,String csv_address,String excel_address) throws IOException, URISyntaxException, PropertyVetoException, SQLException {

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


        //check if the token is valid
        boolean OAuth=oAuthTokenChecker.gettokenstats(Ad_Account_ID_Integer,Client_ID_Integer,Access_Token);


        if(OAuth) {
            //Calling the method to get ad group action stats

            String actionadgroupstats = actionTypeAdGroupStats.getActionTypeAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);

            //Calling the method to get ad set action stats

            String actionadsetstats = actionTypeAdSetStats.getActionTypeAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);

            //Calling the method to get campaign action stats

            String actioncampaignstats = actionTypeCampaignStats.getActionTypeCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);

            //Calling the method to get account action stats

            String actionaccontstats = actionTypeAccountStats.getActionTypeAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);

            //Calling the method to get aggregated account stats

            String aggregatedaccountstats = aggregatedAccountStats.getOverAllAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get aggreegatedCampaign Level Statistics

            String aggregatedcampaignstats = aggregatedCampaignStats.getOverAllCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get aggregatedAdSet Level Statistics

            String aggregatedadsetstats = aggregatedAdSetStats.getOverAllAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get aggregatedAdGroup Level Statistics

            String aggregatedadgroupstats = aggregatedAdGroupStats.getOverAllAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get Account Level Statistics

            String accountstats = productLevelAccountStats.getAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get age and gender account stats

            String overallaccountstats = overAllAccountStats.getOverAllAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get Campaign Level Statistics

            String campaignstats = productLevelCampaignStats.getCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get age and gender Campaign Level Statistics

            String overallcampaignstats = overAllCampaignStats.getOverAllCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get AdSet Level Statistics

            String adsetstats = productLevelAdSetStats.getAdsetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get Age and Gender AdSet Level Statistics

            String overalladsetstats = overAllAdSetStats.getOverAllAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get AdGroup Level Statistics

            String adgroupstats = productLevelAdGroupStats.getAdgroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get Age and Gender AdGroup Level Statistics

            String overalladgroupstats = overAllAdGroupStats.getOverAllAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get CountryandRegion level account stats Statistics

            String countryaccountstats = countryAccountStats.getCountryAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get CountryandRegion level adset stats Statistics

            String countryadsetstats = countryAdSetStats.getCountryAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get CountryandRegion level adgroup stats Statistics

            String countryadgroupstats = countryAdGroupStats.getCountryAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get CountryandRegion level campaign stats Statistics

            String countrycampaignstats = countryCampaignStats.getCountryCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level account stats Statistics

            String regionaccountstats = regionAccountStats.getRegionAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level adset stats Statistics

            String regionadsetstats = regionAdSetStats.getRegionAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level adgroup stats Statistics

            String regionadgroupstats = regionAdGroupStats.getRegionAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level campaign stats Statistics

            String regioncampaignstats = regionCampaignStats.getRegionCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level account stats Statistics

            String placementaccountstats = placementAccountStats.getPlacementAccountstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level adset stats Statistics

            String placementadsetstats = placementAdSetStats.getPlacementAdSetstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level adgroup stats Statistics

            String placementadgroupstats = placementAdGroupStats.getPlacementAdGroupstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //Calling the method to get region level campaign stats Statistics

            String placementcampaignstats = placementCampaignStats.getPlacementCampaignstats(Ad_Account_ID_Integer, Client_ID_Integer, Access_Token, csv_address);


            //adding all the csv files absolute path to the list to add them into a excel file
            List<String> csvfiles = new ArrayList<String>();

            //add all the parameters

            //this is for tickets to integrate all the mails into one
            if (
                    overallaccountstats != null && accountstats != null && campaignstats != null && overallcampaignstats != null
                            && adsetstats != null && overalladsetstats != null && adgroupstats != null && overalladgroupstats != null
                            && placementcampaignstats != null && placementadgroupstats != null && placementadsetstats != null && placementaccountstats != null
                            && regioncampaignstats != null && regionadgroupstats != null && regionadsetstats != null && regionaccountstats != null
                            && countrycampaignstats != null && countryadgroupstats != null && countryadsetstats != null && countryaccountstats != null
                            && aggregatedaccountstats != null && aggregatedadgroupstats != null && aggregatedadsetstats != null && aggregatedcampaignstats != null
                            && actionadgroupstats != null && actionadsetstats != null && actioncampaignstats != null && actionaccontstats != null
                    ) {

                csvfiles.add(aggregatedaccountstats);
                csvfiles.add(aggregatedadgroupstats);
                csvfiles.add(aggregatedadsetstats);
                csvfiles.add(aggregatedcampaignstats);
                csvfiles.add(actionaccontstats);
                csvfiles.add(actionadgroupstats);
                csvfiles.add(actionadsetstats);
                csvfiles.add(actioncampaignstats);
                csvfiles.add(overallaccountstats);
                csvfiles.add(overallcampaignstats);
                csvfiles.add(overalladsetstats);
                csvfiles.add(overalladgroupstats);
                csvfiles.add(accountstats);
                csvfiles.add(adsetstats);
                csvfiles.add(adgroupstats);
                csvfiles.add(campaignstats);
                csvfiles.add(countryaccountstats);
                csvfiles.add(countryadgroupstats);
                csvfiles.add(countryadsetstats);
                csvfiles.add(countrycampaignstats);
                csvfiles.add(regionaccountstats);
                csvfiles.add(regionadgroupstats);
                csvfiles.add(regionadsetstats);
                csvfiles.add(regioncampaignstats);
                csvfiles.add(placementaccountstats);
                csvfiles.add(placementadgroupstats);
                csvfiles.add(placementadsetstats);
                csvfiles.add(placementcampaignstats);

                String Excel_File_Name = getExcelFile(csvfiles, Client_Name, excel_address);
                logger.info("Excel File Created:" + Excel_File_Name);


                List<AccountEmailLoader> accountEmailLoaderList;

                //get the mailing list
                accountEmailLoaderList = accountInformationDAO.getAccountEmailInformation(Client_ID_Integer);

                for (AccountEmailLoader accountEmailLoader : accountEmailLoaderList) {

                    String receiver_email_address = accountEmailLoader.getEmail_ID();
                    successEmail.sendemail("DPA Statistics Successfully stored for Client:",
                            "DPA Statistics Successfully stored for Client:", client_id, Excel_File_Name, Client_Name, receiver_email_address);
                }
            }

        }

    }

    public String getExcelFile(List<String> csvfiles,String Client_Name,String excel_address){

        boolean csv_to_excel_success=false;
        boolean csvindex_success=false;

        String index_sheet="Table of Contents(Index)";
        //create object for xls files
        HSSFWorkbook workBook = new HSSFWorkbook();

        //create the Excel file in the filesystem
        String Excel_File_Name = csvToExcel.getExcelFileName(Client_Name,excel_address);

        //to create index sheet
         csvindex_success=csvToExcel.csvindex(csvfiles,workBook,index_sheet,Excel_File_Name);

        for (String csvfile : csvfiles) {

            String csv_name = csvfile.toString();
            String second_part = csv_name.split("DPAStats")[1];
            int start_index = second_part.indexOf("_");
            start_index++;
            int finish_index = second_part.indexOf(".");
            String Sheet_Name = second_part.substring(start_index, finish_index);

            csv_to_excel_success = csvToExcel.csvToXLS(workBook, csv_name, Sheet_Name + "_" + Client_Name, Excel_File_Name);

        }

        if(csv_to_excel_success) {
            return Excel_File_Name;
        }
        else{
            return null;
        }
    }

}
