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


public class ProductLevelAccountStatsDAO {

    Logger logger= LoggerFactory.getLogger(ProductLevelAccountStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public ProductLevelAccountStatsDAO(){}

    public boolean storeaccountlevelstats(List<AccountStatsLoader> accountStatsLoaderList) throws SQLException, IOException, PropertyVetoException{



        boolean success=false;
        String query = "INSERT INTO Product_Account_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_Account_ID," +
                "Client_Product_ID," +
                "Client_Reports_Reach," +
                "Client_Reports_Frequency," +
                "Client_Reports_Impressions," +
                "Client_Reports_Clicks," +
                "Client_Reports_Total_Actions," +
                "Client_Reports_Social_Reach," +
                "Client_Reports_Social_Impressions," +
                "Client_Reports_Unique_Impressions," +
                "Client_Reports_Unique_Social_Impressions," +
                "Client_Reports_CPM," +
                "Client_Reports_CPP," +
                "Client_Reports_CPC," +
                "Client_Reports_CTR," +
                "Client_Reports_Spend," +
                "Stats_Date," +
                "Client_Reports_Ad_Activity_Date_Start," +
                "Client_Reports_Ad_Activity_Date_End)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = ConnectionDataSource.getInstance().getConnection();
            statement = connection.prepareStatement(query);


            for(AccountStatsLoader accountStatsLoader:accountStatsLoaderList){

                System.out.println(accountStatsLoader.getGender());
                statement.setLong(1,accountStatsLoader.getClient_ID());
                statement.setLong(2, accountStatsLoader.getAccount_ID());
                statement.setLong(3, accountStatsLoader.getProduct_ID());
                statement.setInt(4,accountStatsLoader.getReach());
                statement.setDouble(5,accountStatsLoader.getFrequency());
                statement.setInt(6,accountStatsLoader.getImpressions());
                statement.setInt(7,accountStatsLoader.getClicks());
                statement.setInt(8,accountStatsLoader.getTotal_Actions());
                statement.setInt(9,accountStatsLoader.getSocial_Reach());
                statement.setInt(10,accountStatsLoader.getSocial_Impressions());
                statement.setInt(11,accountStatsLoader.getUnique_Impressions());
                statement.setInt(12,accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(13,accountStatsLoader.getCPM());
                statement.setDouble(14,accountStatsLoader.getCPP());
                statement.setDouble(15,accountStatsLoader.getCPC());
                statement.setDouble(16,accountStatsLoader.getCTR());
                statement.setDouble(17,accountStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) accountStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) accountStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) accountStatsLoader.getActivity_End_Date());

                int rowaffected=statement.executeUpdate();
                if(rowaffected>0){
                    success=true;
                logger.info("Row Successfully inserted for Account Statistics Level");
                }
            }




        } catch (SQLException e) {
            logger.info("ProductLevelAccountStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }
        return success;
    }
}
