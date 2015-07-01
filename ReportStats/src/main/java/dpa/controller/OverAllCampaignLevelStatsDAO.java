package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */

import dpa.model.CampaignStatsLoader;
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


public class OverAllCampaignLevelStatsDAO {

    Logger logger= LoggerFactory.getLogger(OverAllCampaignLevelStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public OverAllCampaignLevelStatsDAO(){}

    public void storecamapignlevelstats(List<CampaignStatsLoader> campaignStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        String query = "INSERT INTO Overall_Campaign_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_AdSet_ID," +
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
                "Client_Cost_Per_Unique_Click)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try {
            connection = ConnectionDataSource.getInstance().getConnection();

            for(CampaignStatsLoader campaignStatsLoader:campaignStatsLoaderList){
                statement = connection.prepareStatement(query);
                statement.setLong(1, campaignStatsLoader.getClient_ID());
                statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                statement.setInt(3, campaignStatsLoader.getAge_Start_Range());
                statement.setInt(4, campaignStatsLoader.getAge_End_Range());
                statement.setString(5, campaignStatsLoader.getGender());
                statement.setInt(6,campaignStatsLoader.getReach());
                statement.setDouble(7, campaignStatsLoader.getFrequency());
                statement.setInt(8,campaignStatsLoader.getImpressions());
                statement.setInt(9,campaignStatsLoader.getClicks());
                statement.setInt(10,campaignStatsLoader.getTotal_Actions());
                statement.setInt(11,campaignStatsLoader.getSocial_Reach());
                statement.setInt(12,campaignStatsLoader.getSocial_Impressions());
                statement.setInt(13, campaignStatsLoader.getUnique_Impressions());
                statement.setInt(14, campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15,campaignStatsLoader.getCPM());
                statement.setDouble(16,campaignStatsLoader.getCPP());
                statement.setDouble(17,campaignStatsLoader.getCPC());
                statement.setDouble(18,campaignStatsLoader.getCTR());
                statement.setDouble(19,campaignStatsLoader.getSpend());
                statement.setDate(20, (Date) campaignStatsLoader.getStats_Date());
                statement.setDate(21, (Date) campaignStatsLoader.getActivity_Start_Date());
                statement.setDate(22, (Date) campaignStatsLoader.getActivity_End_Date());
                statement.setDouble(23,campaignStatsLoader.getCost_Per_Unique_Click());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for OverAllCampaign Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("OverAllCampaignLevelStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}

