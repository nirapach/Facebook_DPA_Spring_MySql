package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVProductLevelAdGroupStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
public class ProductLevelAdGroupCSVMapper implements RowMapper<CSVProductLevelAdGroupStats> {


    @Override
    public CSVProductLevelAdGroupStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVProductLevelAdGroupStats csvProductLevelAdGroupStats=new CSVProductLevelAdGroupStats();
        java.util.Date stats_date = new java.util.Date(resultSet.getDate("Stats_Date").getTime());
        java.util.Date start_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start").getTime());
        java.util.Date end_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End").getTime());

        csvProductLevelAdGroupStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvProductLevelAdGroupStats.setID(resultSet.getLong("Client_Ad_Group_ID"));
        csvProductLevelAdGroupStats.setProduct_ID(resultSet.getString("Client_Product_ID"));
        csvProductLevelAdGroupStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvProductLevelAdGroupStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvProductLevelAdGroupStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvProductLevelAdGroupStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvProductLevelAdGroupStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvProductLevelAdGroupStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvProductLevelAdGroupStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvProductLevelAdGroupStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvProductLevelAdGroupStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvProductLevelAdGroupStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvProductLevelAdGroupStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvProductLevelAdGroupStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvProductLevelAdGroupStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvProductLevelAdGroupStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvProductLevelAdGroupStats.setActivity_End_Date(end_date);
        csvProductLevelAdGroupStats.setActivity_Start_Date(start_date);
        csvProductLevelAdGroupStats.setStats_Date(stats_date);
        csvProductLevelAdGroupStats.setRelevance_Score(resultSet.getDouble("Client_Reports_Relevancy_Score"));


        return csvProductLevelAdGroupStats;


    }
}
