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

    Logger logger= LoggerFactory.getLogger(ProductLevelAdGroupStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public ProductLevelAdGroupStatsDAO(){}


    public boolean storeadgrouplevelstats(List<AdGroupStatsLoader> adGroupStatsLoaderList) throws SQLException, IOException, PropertyVetoException{
        boolean success=false;
        String query = "INSERT INTO Product_Ad_Statistics_Results" +
                "(Application_Client_ID," +
                "Client_Ad_Group_ID," +
                "Client_Product_ID," +
                "Client_Reports_Reach," +
                "Client_Reports_Frequency," +
                "Client_Reports_Impressions," +
                "Client_Reports_Clicks," +
                "Client_Reports_Total_Actions," +
                "Client_Reports_Relevancy_Score," +
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
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            connection = ConnectionDataSource.getInstance().getConnection();
            statement = connection.prepareStatement(query);

            for(AdGroupStatsLoader adGroupStatsLoader:adGroupStatsLoaderList){

                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setLong(3, adGroupStatsLoader.getProduct_ID());
                statement.setInt(4,adGroupStatsLoader.getReach());
                statement.setDouble(5,adGroupStatsLoader.getFrequency());
                statement.setLong(6,adGroupStatsLoader.getImpressions());
                statement.setLong(7,adGroupStatsLoader.getClicks());
                statement.setLong(8,adGroupStatsLoader.getTotal_Actions());
                statement.setDouble(9,adGroupStatsLoader.getRelevance_Score());
                statement.setInt(10,adGroupStatsLoader.getSocial_Reach());
                statement.setInt(11,adGroupStatsLoader.getSocial_Impressions());
                statement.setInt(12,adGroupStatsLoader.getUnique_Impressions());
                statement.setInt(13,adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14,adGroupStatsLoader.getCPM());
                statement.setDouble(15,adGroupStatsLoader.getCPP());
                statement.setDouble(16,adGroupStatsLoader.getCPC());
                statement.setDouble(17,adGroupStatsLoader.getCTR());
                statement.setDouble(18,adGroupStatsLoader.getSpend());
                statement.setDate(19, (java.sql.Date) adGroupStatsLoader.getStats_Date());
                statement.setDate(20, (java.sql.Date) adGroupStatsLoader.getActivity_Start_Date());
                statement.setDate(21, (java.sql.Date) adGroupStatsLoader.getActivity_End_Date());

                int rowaffected=statement.executeUpdate();
                if(rowaffected>0){
                    success=true;
                    logger.info("Row Successfully inserted for Account Statistics Level");
                }
            }




        } catch (SQLException e) {
            logger.info("AdGroup SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

        return success;
    }
}
