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

import dpa.model.AdGroupStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class AdGroupStatsDAO {

    //default constructor
    public AdGroupStatsDAO(){}



    public static void storeadgrouplevelstats(List<AdGroupStatsLoader> adGroupStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(AdSetStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Ad_Statistics_Results` " +
                "(`Application_Client_ID`,`Client_Campaign_ID`,`Client_Product_ID`," +
                "`Client_Reports_Age_Stats_Start_Range`,`Client_Reports_Age_Stats_End_Range`," +
                "`Client_Reports_Country_Stats`,`Client_Reports_Gender_Stats`,`Client_Reports_Placement`," +
                "`Client_Reports_Impression_Device`,`Client_Reports_Reach`," +
                "`Client_Reports_Frequency`,`Client_Reports_Impressions`,`Client_Reports_Clicks`," +
                "`Client_Reports_Total_Actions`,`Client_Reports_Relevancy_Score`," +
                "`Client_Reports_Social_Reach`," +
                "`Client_Reports_Social_Impressions`,`Client_Reports_Unique_Impressions`," +
                "`Client_Reports_Unique_Social_Impressions`,`Client_Reports_CPM`," +
                "`Client_Reports_CPP`,`Client_Reports_CPC`,`Client_Reports_CTR`,`Client_Reports_Spend`," +
                "`Stats_Date`,`Client_Reports_Ad_Activity_Date_Start`,`Client_Reports_Ad_Activity_Date_End`) " +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AdGroupStatsLoader adGroupStatsLoader:adGroupStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1,adGroupStatsLoader.getClient_ID());
                statement.setLong(2,adGroupStatsLoader.getAdGroup_ID());
                statement.setString(3, adGroupStatsLoader.getProduct_ID());
                statement.setInt(4,adGroupStatsLoader.getAge_Start_Range());
                statement.setInt(5,adGroupStatsLoader.getAge_End_Range());
                statement.setString(6,adGroupStatsLoader.getCountry());
                statement.setString(7,adGroupStatsLoader.getGender());
                statement.setString(8,adGroupStatsLoader.getPlacement());
                statement.setString(9,adGroupStatsLoader.getImpression_Device());
                statement.setLong(10,adGroupStatsLoader.getReach());
                statement.setDouble(11,adGroupStatsLoader.getFrequency());
                statement.setLong(12,adGroupStatsLoader.getImpressions());
                statement.setLong(13,adGroupStatsLoader.getClicks());
                statement.setLong(14,adGroupStatsLoader.getTotal_Actions());
                statement.setDouble(15,adGroupStatsLoader.getRelevany_Score());
                statement.setLong(16,adGroupStatsLoader.getSocial_Reach());
                statement.setLong(17,adGroupStatsLoader.getSocial_Impressions());
                statement.setLong(18,adGroupStatsLoader.getUnique_Impressions());
                statement.setLong(19,adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(20,adGroupStatsLoader.getCPM());
                statement.setDouble(21,adGroupStatsLoader.getCPP());
                statement.setDouble(22,adGroupStatsLoader.getCPC());
                statement.setDouble(23,adGroupStatsLoader.getCTR());
                statement.setDouble(24,adGroupStatsLoader.getSpend());
                statement.setDate(25, (java.sql.Date) adGroupStatsLoader.getStats_Date());
                statement.setDate(26, (java.sql.Date) adGroupStatsLoader.getActivity_Start_Date());
                statement.setDate(27, (java.sql.Date) adGroupStatsLoader.getActivity_End_Date());

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
