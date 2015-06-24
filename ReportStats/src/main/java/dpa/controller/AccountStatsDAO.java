package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.PreparedStatement;

import dpa.model.AccountStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class AccountStatsDAO {

    //default constructor
    public AccountStatsDAO(){}



    public static void storeaccountlevelstats(List<AccountStatsLoader> accountStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(AccountStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Account_Statistics_Results` " +
                "(`Application_Client_ID`,`Application_Ad_Account_ID`,`Client_Product_ID`," +
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

            for(AccountStatsLoader accountStatsLoader:accountStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1,accountStatsLoader.getClient_ID());
                statement.setLong(2,accountStatsLoader.getAccount_ID());
                statement.setString(3,accountStatsLoader.getProduct_ID());
                statement.setInt(4,accountStatsLoader.getAge_Start_Range());
                statement.setInt(5,accountStatsLoader.getAge_End_Range());
                statement.setString(6,accountStatsLoader.getCountry());
                statement.setString(7,accountStatsLoader.getGender());
                statement.setString(8,accountStatsLoader.getPlacement());
                statement.setString(9,accountStatsLoader.getImpression_Device());
                statement.setLong(10,accountStatsLoader.getReach());
                statement.setDouble(11,accountStatsLoader.getFrequency());
                statement.setLong(12,accountStatsLoader.getImpressions());
                statement.setLong(13,accountStatsLoader.getClicks());
                statement.setLong(14,accountStatsLoader.getTotal_Actions());
                statement.setLong(15,accountStatsLoader.getSocial_Reach());
                statement.setLong(16,accountStatsLoader.getSocial_Impressions());
                statement.setLong(17,accountStatsLoader.getUnique_Impressions());
                statement.setLong(18,accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(19,accountStatsLoader.getCPM());
                statement.setDouble(20,accountStatsLoader.getCPP());
                statement.setDouble(21,accountStatsLoader.getCPC());
                statement.setDouble(22,accountStatsLoader.getCTR());
                statement.setDouble(23,accountStatsLoader.getSpend());
                statement.setDate(24, (java.sql.Date) accountStatsLoader.getStats_Date());
                statement.setDate(25, (java.sql.Date) accountStatsLoader.getActivity_Start_Date());
                statement.setDate(26, (java.sql.Date) accountStatsLoader.getActivity_End_Date());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for Account Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("AccountStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}
