package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVOverAllAdSetStats;
import com.gravity4.facebook.dpareports.model.CSVOverAllCampaignStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
public class OverAllCampaignCSVMapper implements RowMapper<CSVOverAllCampaignStats> {


    @Override
    public CSVOverAllCampaignStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVOverAllCampaignStats csvOverAllCampaignStats=new CSVOverAllCampaignStats();
        java.util.Date stats_date = new java.util.Date(resultSet.getDate("Stats_Date").getTime());
        java.util.Date start_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start").getTime());
        java.util.Date end_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End").getTime());

        csvOverAllCampaignStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllCampaignStats.setCampaign_ID(resultSet.getLong("Application_Campaign_ID"));
        csvOverAllCampaignStats.setAge_Range(resultSet.getString("Client_Reports_Age_Stats_Range"));
        csvOverAllCampaignStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvOverAllCampaignStats.setGender(resultSet.getString("Client_Reports_Gender_Stats"));
        csvOverAllCampaignStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvOverAllCampaignStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvOverAllCampaignStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvOverAllCampaignStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvOverAllCampaignStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvOverAllCampaignStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvOverAllCampaignStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvOverAllCampaignStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvOverAllCampaignStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvOverAllCampaignStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvOverAllCampaignStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvOverAllCampaignStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvOverAllCampaignStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvOverAllCampaignStats.setCost_Per_Unique_Click(resultSet.getDouble("Client_Cost_Per_Unique_Click"));
        csvOverAllCampaignStats.setActivity_End_Date(end_date);
        csvOverAllCampaignStats.setActivity_Start_Date(start_date);
        csvOverAllCampaignStats.setStats_Date(stats_date);


        return csvOverAllCampaignStats;
    }
}
