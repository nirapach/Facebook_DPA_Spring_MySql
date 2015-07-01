package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */

import dpa.model.AdSetStatsLoader;
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


public class OverAllAdSetLevelStatsDAO {

    Logger logger= LoggerFactory.getLogger(OverAllAdSetLevelStatsDAO.class);

    private Connection connection;
    private PreparedStatement statement;
    //default constructor
    public OverAllAdSetLevelStatsDAO(){}

    public void storeadsetlevelstats(List<AdSetStatsLoader> adSetStatsLoaderList) throws SQLException, IOException, PropertyVetoException{

        String query = "INSERT INTO Overall_AdSet_Statistics_Results" +
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
            statement = connection.prepareStatement(query);

            for(AdSetStatsLoader adSetStatsLoader:adSetStatsLoaderList){

                statement.setLong(1, adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setInt(3, adSetStatsLoader.getAge_Start_Range());
                statement.setInt(4, adSetStatsLoader.getAge_End_Range());
                statement.setString(5, adSetStatsLoader.getGender());
                statement.setInt(6,adSetStatsLoader.getReach());
                statement.setDouble(7, adSetStatsLoader.getFrequency());
                statement.setInt(8,adSetStatsLoader.getImpressions());
                statement.setInt(9,adSetStatsLoader.getClicks());
                statement.setInt(10,adSetStatsLoader.getTotal_Actions());
                statement.setInt(11,adSetStatsLoader.getSocial_Reach());
                statement.setInt(12,adSetStatsLoader.getSocial_Impressions());
                statement.setInt(13, adSetStatsLoader.getUnique_Impressions());
                statement.setLong(14, adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15,adSetStatsLoader.getCPM());
                statement.setDouble(16,adSetStatsLoader.getCPP());
                statement.setDouble(17,adSetStatsLoader.getCPC());
                statement.setDouble(18,adSetStatsLoader.getCTR());
                statement.setDouble(19,adSetStatsLoader.getSpend());
                statement.setDate(20, (Date) adSetStatsLoader.getStats_Date());
                statement.setDate(21, (Date) adSetStatsLoader.getActivity_Start_Date());
                statement.setDate(22, (Date) adSetStatsLoader.getActivity_End_Date());
                statement.setDouble(23,adSetStatsLoader.getCost_Per_Unique_Click());

                statement.executeUpdate();
                logger.info("Row Successfully inserted for OverAllAccount Statistics Level");
            }




        } catch (SQLException e) {
            logger.info("OverAllLevelAccountStatsDAO SQL Exception");
            logger.info(String.valueOf(e));
        } finally {
            DBUtils.close(statement);
            DBUtils.close(connection);

        }

    }
}

