package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */

import dpa.model.AdGroupStatsLoader;
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


public class OverAllAdGroupLevelStatsDAO {

    //default constructor
    public OverAllAdGroupLevelStatsDAO(){}

    public static void storeadgrouplevelstats(List<AdGroupStatsLoader> adGroupStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(OverAllAdGroupLevelStatsDAO.class);
        String query = "INSERT INTO Overall_Ad_Statistics_Results\n" +
                "(Application_Client_ID,\n" +
                "Application_Ad_Group_ID,\n" +
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
                "Client_Cost_Per_Unique_Click," +
                "Client_Ad_Relevance_Score)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);\n";

        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AdGroupStatsLoader adGroupStatsLoader:adGroupStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setInt(3, adGroupStatsLoader.getAge_Start_Range());
                statement.setInt(4, adGroupStatsLoader.getAge_End_Range());
                statement.setString(5, adGroupStatsLoader.getGender());
                statement.setLong(6,adGroupStatsLoader.getReach());
                statement.setDouble(7, adGroupStatsLoader.getFrequency());
                statement.setLong(8,adGroupStatsLoader.getImpressions());
                statement.setLong(9,adGroupStatsLoader.getClicks());
                statement.setLong(10,adGroupStatsLoader.getTotal_Actions());
                statement.setLong(11,adGroupStatsLoader.getSocial_Reach());
                statement.setLong(12,adGroupStatsLoader.getSocial_Impressions());
                statement.setLong(13, adGroupStatsLoader.getUnique_Impressions());
                statement.setLong(14, adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15,adGroupStatsLoader.getCPM());
                statement.setDouble(16,adGroupStatsLoader.getCPP());
                statement.setDouble(17,adGroupStatsLoader.getCPC());
                statement.setDouble(18,adGroupStatsLoader.getCTR());
                statement.setDouble(19,adGroupStatsLoader.getSpend());
                statement.setDate(20, (Date) adGroupStatsLoader.getStats_Date());
                statement.setDate(21, (Date) adGroupStatsLoader.getActivity_Start_Date());
                statement.setDate(22, (Date) adGroupStatsLoader.getActivity_End_Date());
                statement.setDouble(23,adGroupStatsLoader.getCost_Per_Unique_Click());
                statement.setDouble(24,adGroupStatsLoader.getRelevance_Score());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for OverAllAdGroup Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("OverAllAdGroupLevelStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}

