package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.model.AccountStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class OverAllAccountLevelStatsDAO extends BaseDAO {

    public void storeaccountlevelstats(final List<AccountStatsLoader> accountStatsLoaderList) {

        String query = "INSERT INTO Overall_Account_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_Account_ID," +
                "Client_Reports_Age_Stats_Range," +
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
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {
                AccountStatsLoader accountStatsLoader = accountStatsLoaderList.get(i);

                java.sql.Date statsdate = new java.sql.Date(accountStatsLoader.getStats_Date().getTime());
                java.sql.Date Activity_Start_Date = new java.sql.Date(accountStatsLoader.getActivity_Start_Date().getTime());
                java.sql.Date Activity_End_Date = new java.sql.Date(accountStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, accountStatsLoader.getClient_ID());
                statement.setLong(2, accountStatsLoader.getAccount_ID());
                statement.setString(3, accountStatsLoader.getAge_Range());
                statement.setString(4, accountStatsLoader.getGender());
                statement.setInt(5, accountStatsLoader.getReach());
                statement.setDouble(6, accountStatsLoader.getFrequency());
                statement.setInt(7, accountStatsLoader.getImpressions());
                statement.setInt(8, accountStatsLoader.getClicks());
                statement.setInt(9, accountStatsLoader.getTotal_Actions());
                statement.setInt(10, accountStatsLoader.getSocial_Reach());
                statement.setInt(11, accountStatsLoader.getSocial_Impressions());
                statement.setInt(12, accountStatsLoader.getUnique_Impressions());
                statement.setInt(13, accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, accountStatsLoader.getCPM());
                statement.setDouble(15, accountStatsLoader.getCPP());
                statement.setDouble(16, accountStatsLoader.getCPC());
                statement.setDouble(17, accountStatsLoader.getCTR());
                statement.setDouble(18, accountStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);
                statement.setDouble(22, accountStatsLoader.getCost_Per_Unique_Click());
            }

            @Override
            public int getBatchSize() {
                return accountStatsLoaderList.size();
            }
        });
    }
}

