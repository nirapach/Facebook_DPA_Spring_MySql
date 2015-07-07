package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.model.CampaignStatsLoader;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class OverAllCampaignLevelStatsDAO extends BaseDAO {

    public void storecamapignlevelstats(List<CampaignStatsLoader> campaignStatsLoaderList){

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

        for (final CampaignStatsLoader campaignStatsLoader : campaignStatsLoaderList) {


            PreparedStatementSetter pss = new PreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement statement) throws SQLException {
                    statement.setLong(1, campaignStatsLoader.getClient_ID());
                    statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                    statement.setInt(3, campaignStatsLoader.getAge_Start_Range());
                    statement.setInt(4, campaignStatsLoader.getAge_End_Range());
                    statement.setString(5, campaignStatsLoader.getGender());
                    statement.setInt(6, campaignStatsLoader.getReach());
                    statement.setDouble(7, campaignStatsLoader.getFrequency());
                    statement.setInt(8, campaignStatsLoader.getImpressions());
                    statement.setInt(9, campaignStatsLoader.getClicks());
                    statement.setInt(10, campaignStatsLoader.getTotal_Actions());
                    statement.setInt(11, campaignStatsLoader.getSocial_Reach());
                    statement.setInt(12, campaignStatsLoader.getSocial_Impressions());
                    statement.setInt(13, campaignStatsLoader.getUnique_Impressions());
                    statement.setInt(14, campaignStatsLoader.getUnique_Social_Impressions());
                    statement.setDouble(15, campaignStatsLoader.getCPM());
                    statement.setDouble(16, campaignStatsLoader.getCPP());
                    statement.setDouble(17, campaignStatsLoader.getCPC());
                    statement.setDouble(18, campaignStatsLoader.getCTR());
                    statement.setDouble(19, campaignStatsLoader.getSpend());
                    statement.setDate(20, (Date) campaignStatsLoader.getStats_Date());
                    statement.setDate(21, (Date) campaignStatsLoader.getActivity_Start_Date());
                    statement.setDate(22, (Date) campaignStatsLoader.getActivity_End_Date());
                    statement.setDouble(23, campaignStatsLoader.getCost_Per_Unique_Click());
                }
            };

            getJdbcTemplate().update(query, pss);

        }
    }
}

