package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */

import dpa.model.AdSetStatsLoader;
import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class OverAllAdSetLevelStatsDAO {

    //default constructor
    public OverAllAdSetLevelStatsDAO(){}

    public static void storeadsetlevelstats(List<AdSetStatsLoader> adSetStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(OverAllAdSetLevelStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Overall_AdSet_Statistics_Results`\n" +
                "(Application_Client_ID,\n" +
                "Application_Ad_AdSet_ID,\n" +
                "Client_Reports_Age_Stats_Start_Range,\n" +
                "Client_Reports_Age_Stats_End_Range,\n" +
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
                "Client_Reports_Ad_Activity_Date_End,\n" +
                "Client_Cost_Per_Unique_Click)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AdSetStatsLoader adSetStatsLoader:adSetStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1, adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setInt(3, adSetStatsLoader.getAge_Start_Range());
                statement.setInt(4, adSetStatsLoader.getAge_End_Range());
                statement.setString(5, adSetStatsLoader.getGender());
                statement.setLong(6,adSetStatsLoader.getReach());
                statement.setDouble(7, adSetStatsLoader.getFrequency());
                statement.setLong(8,adSetStatsLoader.getImpressions());
                statement.setLong(9,adSetStatsLoader.getClicks());
                statement.setLong(10,adSetStatsLoader.getTotal_Actions());
                statement.setLong(11,adSetStatsLoader.getSocial_Reach());
                statement.setLong(12,adSetStatsLoader.getSocial_Impressions());
                statement.setLong(13, adSetStatsLoader.getUnique_Impressions());
                statement.setLong(14, adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15,adSetStatsLoader.getCPM());
                statement.setDouble(16,adSetStatsLoader.getCPP());
                statement.setDouble(17,adSetStatsLoader.getCPC());
                statement.setDouble(18,adSetStatsLoader.getCTR());
                statement.setDouble(19,adSetStatsLoader.getSpend());
                statement.setDate(20, (Date) adSetStatsLoader.getStats_Date());
                statement.setDate(21, (Date) adSetStatsLoader.getActivity_Start_Date());
                statement.setDate(22, (Date) adSetStatsLoader.getActivity_End_Date());
                statement.setDouble(23,adSetStatsLoader.getCost_Per_Unique_Click());

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

