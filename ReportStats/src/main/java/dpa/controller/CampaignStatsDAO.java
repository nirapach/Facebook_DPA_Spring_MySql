package dpa.controller;

/**
 * Created by niranjan on 6/23/15.
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

import dpa.model.CampaignStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class CampaignStatsDAO {

    //default constructor
    public CampaignStatsDAO(){}



    public static void storecampaignlevelstats(List<CampaignStatsLoader> campaignStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(CampaignStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Campaign_Statistics_Results` " +
                "(`Application_Client_ID`,`Client_Campaign_ID`,`Client_Product_ID`," +
                "`Client_Reports_Age_Stats_Start_Range`,`Client_Reports_Age_Stats_End_Range`," +
                "`Client_Reports_Country_Stats`,`Client_Reports_Gender_Stats`,`Client_Reports_Placement`," +
                "`Client_Reports_Impression_Device`,`Client_Reports_Reach`," +
                "`Client_Reports_Frequency`,`Client_Reports_Impressions`,`Client_Reports_Clicks`,`Client_Reports_Total_Actions`," +
                "`Client_Reports_Social_Reach`," +
                "`Client_Reports_Social_Impressions`,`Client_Reports_Unique_Impressions`," +
                "`Client_Reports_Unique_Social_Impressions`,`Client_Reports_CPM`," +
                "`Client_Reports_CPP`,`Client_Reports_CPC`,`Client_Reports_CTR`,`Client_Reports_Spend`," +
                "`Stats_Date`,`Client_Reports_Ad_Activity_Date_Start`,`Client_Reports_Ad_Activity_Date_End`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(CampaignStatsLoader campaignStatsLoader:campaignStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setInt(1,campaignStatsLoader.getClient_ID());
                statement.setInt(2,campaignStatsLoader.getCampaign_ID());
                statement.setString(3,campaignStatsLoader.getProduct_ID());
                statement.setInt(4,campaignStatsLoader.getAge_Start_Range());
                statement.setInt(5,campaignStatsLoader.getAge_End_Range());
                statement.setString(6,campaignStatsLoader.getCountry());
                statement.setString(7,campaignStatsLoader.getGender());
                statement.setString(8,campaignStatsLoader.getPlacement());
                statement.setString(9,campaignStatsLoader.getImpression_Device());
                statement.setInt(10,campaignStatsLoader.getReach());
                statement.setDouble(11,campaignStatsLoader.getFrequency());
                statement.setInt(12,campaignStatsLoader.getImpressions());
                statement.setInt(13,campaignStatsLoader.getClicks());
                statement.setInt(14,campaignStatsLoader.getTotal_Actions());
                statement.setInt(15,campaignStatsLoader.getSocial_Reach());
                statement.setInt(16,campaignStatsLoader.getSocial_Impressions());
                statement.setInt(17,campaignStatsLoader.getUnique_Impressions());
                statement.setInt(18,campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(19,campaignStatsLoader.getCPM());
                statement.setDouble(20,campaignStatsLoader.getCPP());
                statement.setDouble(21,campaignStatsLoader.getCPC());
                statement.setDouble(22,campaignStatsLoader.getCTR());
                statement.setDouble(23,campaignStatsLoader.getSpend());
                statement.setDate(24, (java.sql.Date) campaignStatsLoader.getStats_Date());
                statement.setDate(25, (java.sql.Date) campaignStatsLoader.getActivity_Start_Date());
                statement.setDate(26, (java.sql.Date) campaignStatsLoader.getActivity_End_Date());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for Campaign Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("CampaignDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}
