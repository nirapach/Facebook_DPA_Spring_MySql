package dpa.controller;

/**
 * Created by niranjan on 6/23/15.
 */
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.sql.PreparedStatement;

import dpa.model.AdSetStatsLoader;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;


public class ProductLevelAdSetStatsDAO {

    Logger logger= LoggerFactory.getLogger(ProductLevelAdSetStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public ProductLevelAdSetStatsDAO(){}



    public boolean storeadsetlevelstats(List<AdSetStatsLoader> adSetStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        boolean success=false;
        String query = "INSERT INTO Product_Account_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_AdSet_ID," +
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
            for(AdSetStatsLoader adSetStatsLoader:adSetStatsLoaderList){
                statement.setLong(1,adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setLong(3, adSetStatsLoader.getProduct_ID());
                statement.setInt(4,adSetStatsLoader.getReach());
                statement.setDouble(5,adSetStatsLoader.getFrequency());
                statement.setInt(6,adSetStatsLoader.getImpressions());
                statement.setInt(7,adSetStatsLoader.getClicks());
                statement.setInt(8,adSetStatsLoader.getTotal_Actions());
                statement.setInt(9,adSetStatsLoader.getSocial_Reach());
                statement.setInt(10,adSetStatsLoader.getSocial_Impressions());
                statement.setInt(11,adSetStatsLoader.getUnique_Impressions());
                statement.setInt(12,adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(13,adSetStatsLoader.getCPM());
                statement.setDouble(14,adSetStatsLoader.getCPP());
                statement.setDouble(15,adSetStatsLoader.getCPC());
                statement.setDouble(16,adSetStatsLoader.getCTR());
                statement.setDouble(17,adSetStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) adSetStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) adSetStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) adSetStatsLoader.getActivity_End_Date());

                int rowaffected=statement.executeUpdate();
                if(rowaffected>0){
                    success=true;
                    logger.info("Row Successfully inserted for Account Statistics Level");
                }
            }




        } catch (SQLException e) {
            logger.info("AdGroupDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

        return success;
    }
}
