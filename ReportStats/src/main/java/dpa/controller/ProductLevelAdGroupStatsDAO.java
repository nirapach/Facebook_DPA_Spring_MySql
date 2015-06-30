package dpa.controller;

/**
 * Created by niranjan on 6/23/15.
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;

import dpa.model.AdGroupStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class ProductLevelAdGroupStatsDAO {

    //default constructor
    public ProductLevelAdGroupStatsDAO(){}



    public static void storeadgrouplevelstats(List<AdGroupStatsLoader> adGroupStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(ProductLevelAdSetStatsDAO.class);
        String query = "INSERT INTO Product_Ad_Statistics_Results\n" +
                "(Application_Client_ID,\n" +
                "Client_Ad_Group_ID,\n" +
                "Client_Product_ID,\n" +
                "Client_Reports_Reach,\n" +
                "Client_Reports_Frequency,\n" +
                "Client_Reports_Impressions,\n" +
                "Client_Reports_Clicks,\n" +
                "Client_Reports_Total_Actions,\n" +
                "Client_Reports_Relevancy_Score,\n" +
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
                "Client_Reports_Ad_Activity_Date_End)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AdGroupStatsLoader adGroupStatsLoader:adGroupStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setLong(3, adGroupStatsLoader.getProduct_ID());
                statement.setLong(4,adGroupStatsLoader.getReach());
                statement.setDouble(5,adGroupStatsLoader.getFrequency());
                statement.setLong(6,adGroupStatsLoader.getImpressions());
                statement.setLong(7,adGroupStatsLoader.getClicks());
                statement.setLong(8,adGroupStatsLoader.getTotal_Actions());
                statement.setDouble(9,adGroupStatsLoader.getRelevance_Score());
                statement.setLong(10,adGroupStatsLoader.getSocial_Reach());
                statement.setLong(11,adGroupStatsLoader.getSocial_Impressions());
                statement.setLong(12,adGroupStatsLoader.getUnique_Impressions());
                statement.setLong(13,adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14,adGroupStatsLoader.getCPM());
                statement.setDouble(15,adGroupStatsLoader.getCPP());
                statement.setDouble(16,adGroupStatsLoader.getCPC());
                statement.setDouble(17,adGroupStatsLoader.getCTR());
                statement.setDouble(18,adGroupStatsLoader.getSpend());
                statement.setDate(19, (java.sql.Date) adGroupStatsLoader.getStats_Date());
                statement.setDate(20, (java.sql.Date) adGroupStatsLoader.getActivity_Start_Date());
                statement.setDate(21, (java.sql.Date) adGroupStatsLoader.getActivity_End_Date());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for Ad Group Level Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("AdGroup SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}
