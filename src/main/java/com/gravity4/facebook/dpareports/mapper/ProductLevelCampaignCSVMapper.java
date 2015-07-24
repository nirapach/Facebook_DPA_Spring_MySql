package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVProductLevelCampaignStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ProductLevelCampaignCSVMapper implements RowMapper<CSVProductLevelCampaignStats> {


    @Override
    public CSVProductLevelCampaignStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVProductLevelCampaignStats csvProductLevelCampaignStats=new CSVProductLevelCampaignStats();


        csvProductLevelCampaignStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvProductLevelCampaignStats.setID(resultSet.getLong("Client_Campaign_ID"));
        csvProductLevelCampaignStats.setName(resultSet.getString("Client_Campaign_Name"));
        csvProductLevelCampaignStats.setProduct_ID(resultSet.getString("Client_Product_ID"));
        csvProductLevelCampaignStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvProductLevelCampaignStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvProductLevelCampaignStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvProductLevelCampaignStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvProductLevelCampaignStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvProductLevelCampaignStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvProductLevelCampaignStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvProductLevelCampaignStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvProductLevelCampaignStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvProductLevelCampaignStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvProductLevelCampaignStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvProductLevelCampaignStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvProductLevelCampaignStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvProductLevelCampaignStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvProductLevelCampaignStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvProductLevelCampaignStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvProductLevelCampaignStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvProductLevelCampaignStats;


    }
}
