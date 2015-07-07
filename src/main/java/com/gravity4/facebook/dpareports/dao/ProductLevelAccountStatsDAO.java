package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.model.AccountStatsLoader;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class ProductLevelAccountStatsDAO extends BaseDAO {

    public void storeaccountlevelstats(List<AccountStatsLoader> accountStatsLoaderList){

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

        for (final AccountStatsLoader accountStatsLoader : accountStatsLoaderList) {

            PreparedStatementSetter pss = new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement statement) throws SQLException {
                    statement.setLong(1, accountStatsLoader.getClient_ID());
                    statement.setLong(2, accountStatsLoader.getAccount_ID());
                    statement.setLong(3, accountStatsLoader.getProduct_ID());
                    statement.setInt(4, accountStatsLoader.getReach());
                    statement.setDouble(5, accountStatsLoader.getFrequency());
                    statement.setInt(6, accountStatsLoader.getImpressions());
                    statement.setInt(7, accountStatsLoader.getClicks());
                    statement.setInt(8, accountStatsLoader.getTotal_Actions());
                    statement.setInt(9, accountStatsLoader.getSocial_Reach());
                    statement.setInt(10, accountStatsLoader.getSocial_Impressions());
                    statement.setInt(11, accountStatsLoader.getUnique_Impressions());
                    statement.setInt(12, accountStatsLoader.getUnique_Social_Impressions());
                    statement.setDouble(13, accountStatsLoader.getCPM());
                    statement.setDouble(14, accountStatsLoader.getCPP());
                    statement.setDouble(15, accountStatsLoader.getCPC());
                    statement.setDouble(16, accountStatsLoader.getCTR());
                    statement.setDouble(17, accountStatsLoader.getSpend());
                    statement.setDate(18, (java.sql.Date) accountStatsLoader.getStats_Date());
                    statement.setDate(19, (java.sql.Date) accountStatsLoader.getActivity_Start_Date());
                    statement.setDate(20, (java.sql.Date) accountStatsLoader.getActivity_End_Date());
                }
            };

            getJdbcTemplate().update(query, pss);
        }
    }
}
