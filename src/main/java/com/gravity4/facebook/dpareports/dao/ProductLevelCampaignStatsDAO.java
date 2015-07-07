package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/23/15.
 */

import com.gravity4.facebook.dpareports.model.CampaignStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class ProductLevelCampaignStatsDAO extends BaseDAO {

    public void storecampaignlevelstats(final List<CampaignStatsLoader> campaignStatsLoaderList){

        String query = "INSERT INTO Product_Account_Statistics_Results" +
                "(Application_Client_ID," +
                "Client_Campaign_ID," +
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

        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {
                CampaignStatsLoader campaignStatsLoader = campaignStatsLoaderList.get(i);

                statement.setLong(1, campaignStatsLoader.getClient_ID());
                statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                statement.setLong(3, campaignStatsLoader.getProduct_ID());
                statement.setLong(4, campaignStatsLoader.getReach());
                statement.setDouble(5, campaignStatsLoader.getFrequency());
                statement.setLong(6, campaignStatsLoader.getImpressions());
                statement.setLong(7, campaignStatsLoader.getClicks());
                statement.setLong(8, campaignStatsLoader.getTotal_Actions());
                statement.setLong(9, campaignStatsLoader.getSocial_Reach());
                statement.setLong(10, campaignStatsLoader.getSocial_Impressions());
                statement.setLong(11, campaignStatsLoader.getUnique_Impressions());
                statement.setLong(12, campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(12, campaignStatsLoader.getCPM());
                statement.setDouble(14, campaignStatsLoader.getCPP());
                statement.setDouble(15, campaignStatsLoader.getCPC());
                statement.setDouble(16, campaignStatsLoader.getCTR());
                statement.setDouble(17, campaignStatsLoader.getSpend());
                statement.setDate(18, (java.sql.Date) campaignStatsLoader.getStats_Date());
                statement.setDate(19, (java.sql.Date) campaignStatsLoader.getActivity_Start_Date());
                statement.setDate(20, (java.sql.Date) campaignStatsLoader.getActivity_End_Date());
            }

            @Override
            public int getBatchSize() {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
