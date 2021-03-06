package com.gravity4.facebook.dpareports.mapper.Region;


import com.gravity4.facebook.dpareports.model.CSVModels.Region.CSVRegionCampaignStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class RegionCampaignCSVMapper implements RowMapper<CSVRegionCampaignStats> {


    @Override
    public CSVRegionCampaignStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVRegionCampaignStats csvOverAllCampaignStats=new CSVRegionCampaignStats();


        csvOverAllCampaignStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllCampaignStats.setCampaign_ID(resultSet.getLong("Application_Campaign_ID"));
        csvOverAllCampaignStats.setCampaign_Name(resultSet.getString("Application_Campaign_Name"));
        csvOverAllCampaignStats.setRegion(resultSet.getString("Region"));
        csvOverAllCampaignStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
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
        csvOverAllCampaignStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvOverAllCampaignStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvOverAllCampaignStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvOverAllCampaignStats;
    }
}
