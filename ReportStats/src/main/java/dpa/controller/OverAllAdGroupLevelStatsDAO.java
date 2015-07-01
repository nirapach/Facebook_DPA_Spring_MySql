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

    Logger logger= LoggerFactory.getLogger(OverAllAdGroupLevelStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public OverAllAdGroupLevelStatsDAO(){}

    public boolean storeadgrouplevelstats(List<AdGroupStatsLoader> adGroupStatsLoaderList) throws SQLException, IOException, PropertyVetoException{


        boolean success=false;
        String query = "INSERT INTO Overall_Ad_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_Group_ID," +
                "Client_Reports_Age_Stats_Start_Range," +
                "Client_Reports_Age_Stats_End_Range," +
                "Client_Reports_Gender_Stats," +
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
                "Client_Reports_Ad_Activity_Date_End," +
                "Client_Cost_Per_Unique_Click," +
                "Client_Ad_Relevance_Score)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";


        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            statement = connection.prepareStatement(query);
            for(AdGroupStatsLoader adGroupStatsLoader:adGroupStatsLoaderList){

                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setInt(3, adGroupStatsLoader.getAge_Start_Range());
                statement.setInt(4, adGroupStatsLoader.getAge_End_Range());
                statement.setString(5, adGroupStatsLoader.getGender());
                statement.setInt(6,adGroupStatsLoader.getReach());
                statement.setDouble(7, adGroupStatsLoader.getFrequency());
                statement.setInt(8,adGroupStatsLoader.getImpressions());
                statement.setInt(9,adGroupStatsLoader.getClicks());
                statement.setInt(10,adGroupStatsLoader.getTotal_Actions());
                statement.setInt(11,adGroupStatsLoader.getSocial_Reach());
                statement.setInt(12,adGroupStatsLoader.getSocial_Impressions());
                statement.setInt(13, adGroupStatsLoader.getUnique_Impressions());
                statement.setInt(14, adGroupStatsLoader.getUnique_Social_Impressions());
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

                int rowaffected=statement.executeUpdate();
                if(rowaffected>0){
                    success=true;
                    logger.info("Row Successfully inserted for Account Statistics Level");
                }
            }




        } catch (SQLException e) {
            logger.info("OverAllAdGroupLevelStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }
return success;
    }
}

