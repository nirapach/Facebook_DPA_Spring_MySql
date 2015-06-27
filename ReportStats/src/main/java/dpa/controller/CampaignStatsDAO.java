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
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Account_Statistics_Results`\n" +
                "(`Application_Client_ID`,\n" +
                "`Client_Campaign_ID`,\n" +
                "`Client_Product_ID`,\n" +
                "`Client_Reports_Reach`,\n" +
                "`Client_Reports_Frequency`,\n" +
                "`Client_Reports_Impressions`,\n" +
                "`Client_Reports_Clicks`,\n" +
                "`Client_Reports_Total_Actions`,\n" +
                "`Client_Reports_Social_Reach`,\n" +
                "`Client_Reports_Social_Impressions`,\n" +
                "`Client_Reports_Unique_Impressions`,\n" +
                "`Client_Reports_Unique_Social_Impressions`,\n" +
                "`Client_Reports_CPM`,\n" +
                "`Client_Reports_CPP`,\n" +
                "`Client_Reports_CPC`,\n" +
                "`Client_Reports_CTR`,\n" +
                "`Client_Reports_Spend`,\n" +
                "`Stats_Date`,\n" +
                "`Client_Reports_Ad_Activity_Date_Start`,\n" +
                "`Client_Reports_Ad_Activity_Date_End`)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(CampaignStatsLoader campaignStatsLoader:campaignStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1,campaignStatsLoader.getClient_ID());
                statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                statement.setLong(3, campaignStatsLoader.getProduct_ID());
                /*statement.setInt(4,campaignStatsLoader.getAge_Start_Range());
                statement.setInt(5, campaignStatsLoader.getAge_End_Range());
                statement.setString(6,campaignStatsLoader.getCountry());
                statement.setString(7,campaignStatsLoader.getGender());
                statement.setString(8,campaignStatsLoader.getPlacement());
                statement.setString(9,campaignStatsLoader.getImpression_Device());*/
                statement.setLong(4,campaignStatsLoader.getReach());
                statement.setDouble(5,campaignStatsLoader.getFrequency());
                statement.setLong(6,campaignStatsLoader.getImpressions());
                statement.setLong(7,campaignStatsLoader.getClicks());
                statement.setLong(8,campaignStatsLoader.getTotal_Actions());
                statement.setLong(9,campaignStatsLoader.getSocial_Reach());
                statement.setLong(10,campaignStatsLoader.getSocial_Impressions());
                statement.setLong(11,campaignStatsLoader.getUnique_Impressions());
                statement.setLong(12,campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(12,campaignStatsLoader.getCPM());
                statement.setDouble(14,campaignStatsLoader.getCPP());
                statement.setDouble(15,campaignStatsLoader.getCPC());
                statement.setDouble(16,campaignStatsLoader.getCTR());
                statement.setDouble(17,campaignStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) campaignStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) campaignStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) campaignStatsLoader.getActivity_End_Date());

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
