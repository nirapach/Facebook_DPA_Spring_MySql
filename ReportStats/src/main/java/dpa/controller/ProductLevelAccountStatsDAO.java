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
import dpa.model.AdGroupStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class ProductLevelAccountStatsDAO {

    //default constructor
    public ProductLevelAccountStatsDAO(){}

    public static void storeaccountlevelstats(List<AccountStatsLoader> accountStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        Logger logger = LoggerFactory.getLogger(ProductLevelAccountStatsDAO.class);
        String query = "INSERT INTO `G4_DPA_REPORTS_DB`.`Product_Account_Statistics_Results`\n" +
                "(Application_Client_ID,\n" +
                "Application_Ad_Account_ID,\n" +
                "Client_Product_ID,\n" +
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
                "Client_Reports_Ad_Activity_Date_End)\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Connection connection = null;

        PreparedStatement statement = null;
        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(AccountStatsLoader accountStatsLoader:accountStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1,accountStatsLoader.getClient_ID());
                statement.setLong(2, accountStatsLoader.getAccount_ID());
                statement.setLong(3, accountStatsLoader.getProduct_ID());
                statement.setLong(4,accountStatsLoader.getReach());
                statement.setDouble(5,accountStatsLoader.getFrequency());
                statement.setLong(6,accountStatsLoader.getImpressions());
                statement.setLong(7,accountStatsLoader.getClicks());
                statement.setLong(8,accountStatsLoader.getTotal_Actions());
                statement.setLong(9,accountStatsLoader.getSocial_Reach());
                statement.setLong(10,accountStatsLoader.getSocial_Impressions());
                statement.setLong(11,accountStatsLoader.getUnique_Impressions());
                statement.setLong(12,accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(13,accountStatsLoader.getCPM());
                statement.setDouble(14,accountStatsLoader.getCPP());
                statement.setDouble(15,accountStatsLoader.getCPC());
                statement.setDouble(16,accountStatsLoader.getCTR());
                statement.setDouble(17,accountStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) accountStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) accountStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) accountStatsLoader.getActivity_End_Date());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for Account Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("ProductLevelAccountStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}
