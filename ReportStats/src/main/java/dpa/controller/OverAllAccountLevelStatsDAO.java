package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;

import dpa.model.AccountStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class OverAllAccountLevelStatsDAO {

    //default constructor
    public OverAllAccountLevelStatsDAO(){}

    public static void storeaccountlevelstats(List<AccountStatsLoader> accountStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(OverAllAccountLevelStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Overall_Account_Statistics_Results`\n" +
                "(Application_Client_ID,\n" +
                "Application_Ad_Account_ID,\n" +
                "Client_Reports_Age_Stats_Start_Range,\n" +
                "Client_Reports_Age_Stats_End_Range,\n" +
                "Client_Reports_Country_Stats,\n" +
                "Client_Reports_Gender_Stats,\n" +
                "Client_Reports_Reach,\n" +
                "Client_Reports_Frequency,\n" +
                "Client_Reports_Impressions,\n" +
                "Client_Reports_Clicks,\n" +
                "Client_Reports_Total_Actions,\n" +
                "Client_Reports_Social_Reach,\n" +
                "Client_Reports_Social_Impressions,\n" +
                "Client_Reports_Unique_Impressions,\n" +
                "Client_Reports_Unique_Social_Impressions,\n" +
                "Client_Reports_CPM,\n" +
                "Client_Reports_CPP,\n" +
                "Client_Reports_CPC,\n" +
                "Client_Reports_CTR,\n" +
                "Client_Reports_Spend,\n" +
                "Stats_Date,\n" +
                "Client_Reports_Ad_Activity_Date_Start,\n" +
                "Client_Reports_Ad_Activity_Date_End," +
                "Client_Cost_Per_Unique_Click)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AccountStatsLoader accountStatsLoader:accountStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1, accountStatsLoader.getClient_ID());
                statement.setLong(2, accountStatsLoader.getAccount_ID());
                statement.setInt(3, accountStatsLoader.getAge_Start_Range());
                statement.setInt(4, accountStatsLoader.getAge_End_Range());
                statement.setString(5, accountStatsLoader.getGender());
                statement.setLong(6,accountStatsLoader.getReach());
                statement.setDouble(7, accountStatsLoader.getFrequency());
                statement.setLong(8,accountStatsLoader.getImpressions());
                statement.setLong(9,accountStatsLoader.getClicks());
                statement.setLong(10,accountStatsLoader.getTotal_Actions());
                statement.setLong(11,accountStatsLoader.getSocial_Reach());
                statement.setLong(12,accountStatsLoader.getSocial_Impressions());
                statement.setLong(13, accountStatsLoader.getUnique_Impressions());
                statement.setLong(14, accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15,accountStatsLoader.getCPM());
                statement.setDouble(16,accountStatsLoader.getCPP());
                statement.setDouble(17,accountStatsLoader.getCPC());
                statement.setDouble(18,accountStatsLoader.getCTR());
                statement.setDouble(19,accountStatsLoader.getSpend());
                statement.setDate(20, (java.sql.Date) accountStatsLoader.getStats_Date());
                statement.setDate(21, (java.sql.Date) accountStatsLoader.getActivity_Start_Date());
                statement.setDate(22, (java.sql.Date) accountStatsLoader.getActivity_End_Date());
                statement.setDouble(23,accountStatsLoader.getCost_Per_Unique_Click());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for OverAllAccount Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("OverAllLevelAccountStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}

