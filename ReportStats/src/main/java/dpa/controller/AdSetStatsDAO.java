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

import dpa.model.AdSetStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class AdSetStatsDAO {

    //default constructor
    public AdSetStatsDAO(){}



    public static void storeadsetlevelstats(List<AdSetStatsLoader> adSetStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(AdSetStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_AdSet_Statistics_Results` " +
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

            for(AdSetStatsLoader adSetStatsLoader:adSetStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setInt(1,adSetStatsLoader.getClient_ID());
                statement.setInt(2,adSetStatsLoader.getAdSet_ID());
                statement.setString(3, adSetStatsLoader.getProduct_ID());
                statement.setInt(4,adSetStatsLoader.getAge_Start_Range());
                statement.setInt(5,adSetStatsLoader.getAge_End_Range());
                statement.setString(6,adSetStatsLoader.getCountry());
                statement.setString(7,adSetStatsLoader.getGender());
                statement.setString(8,adSetStatsLoader.getPlacement());
                statement.setString(9,adSetStatsLoader.getImpression_Device());
                statement.setInt(10,adSetStatsLoader.getReach());
                statement.setDouble(11,adSetStatsLoader.getFrequency());
                statement.setInt(12,adSetStatsLoader.getImpressions());
                statement.setInt(13,adSetStatsLoader.getClicks());
                statement.setInt(14,adSetStatsLoader.getTotal_Actions());
                statement.setInt(15,adSetStatsLoader.getSocial_Reach());
                statement.setInt(16,adSetStatsLoader.getSocial_Impressions());
                statement.setInt(17,adSetStatsLoader.getUnique_Impressions());
                statement.setInt(18,adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(19,adSetStatsLoader.getCPM());
                statement.setDouble(20,adSetStatsLoader.getCPP());
                statement.setDouble(21,adSetStatsLoader.getCPC());
                statement.setDouble(22,adSetStatsLoader.getCTR());
                statement.setDouble(23,adSetStatsLoader.getSpend());
                statement.setDate(24, (java.sql.Date) adSetStatsLoader.getStats_Date());
                statement.setDate(25, (java.sql.Date) adSetStatsLoader.getActivity_Start_Date());
                statement.setDate(26, (java.sql.Date) adSetStatsLoader.getActivity_End_Date());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for AdGroup Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("AdGroupDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}
