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
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Account_Statistics_Results`\n" +
                "(`Application_Client_ID`,\n" +
                "'Application_Ad_AdSet_ID',\n" +
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

            for(AdSetStatsLoader adSetStatsLoader:adSetStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1,adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setLong(3, adSetStatsLoader.getProduct_ID());
                /*statement.setInt(4, adSetStatsLoader.getAge_Start_Range());
                statement.setInt(5, adSetStatsLoader.getAge_End_Range());
                statement.setString(6, adSetStatsLoader.getCountry());
                statement.setString(7,adSetStatsLoader.getGender());
                statement.setString(8,adSetStatsLoader.getPlacement());
                statement.setString(9,adSetStatsLoader.getImpression_Device());*/
                statement.setLong(4,adSetStatsLoader.getReach());
                statement.setDouble(5,adSetStatsLoader.getFrequency());
                statement.setLong(6,adSetStatsLoader.getImpressions());
                statement.setLong(7,adSetStatsLoader.getClicks());
                statement.setLong(8,adSetStatsLoader.getTotal_Actions());
                statement.setLong(9,adSetStatsLoader.getSocial_Reach());
                statement.setLong(10,adSetStatsLoader.getSocial_Impressions());
                statement.setLong(11,adSetStatsLoader.getUnique_Impressions());
                statement.setLong(12,adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(13,adSetStatsLoader.getCPM());
                statement.setDouble(14,adSetStatsLoader.getCPP());
                statement.setDouble(15,adSetStatsLoader.getCPC());
                statement.setDouble(16,adSetStatsLoader.getCTR());
                statement.setDouble(17,adSetStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) adSetStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) adSetStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) adSetStatsLoader.getActivity_End_Date());

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
