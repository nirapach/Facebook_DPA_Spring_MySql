package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVOverAllAdGroupStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
public class OverAllAdGroupCSVMapper implements RowMapper<CSVOverAllAdGroupStats> {


    @Override
    public CSVOverAllAdGroupStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVOverAllAdGroupStats csvOverAllAdGroupStats=new CSVOverAllAdGroupStats();
        java.util.Date stats_date = new java.util.Date(resultSet.getDate("Stats_Date").getTime());
        java.util.Date start_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start").getTime());
        java.util.Date end_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End").getTime());

        csvOverAllAdGroupStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAdGroupStats.setAdGroup_ID(resultSet.getLong("Application_Ad_Group_ID"));
        csvOverAllAdGroupStats.setAge_Range(resultSet.getString("Client_Reports_Age_Stats_Range"));
        csvOverAllAdGroupStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvOverAllAdGroupStats.setGender(resultSet.getString("Client_Reports_Gender_Stats"));
        csvOverAllAdGroupStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvOverAllAdGroupStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvOverAllAdGroupStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvOverAllAdGroupStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvOverAllAdGroupStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvOverAllAdGroupStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvOverAllAdGroupStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvOverAllAdGroupStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvOverAllAdGroupStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvOverAllAdGroupStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvOverAllAdGroupStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvOverAllAdGroupStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvOverAllAdGroupStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvOverAllAdGroupStats.setCost_Per_Unique_Click(resultSet.getDouble("Client_Cost_Per_Unique_Click"));
        csvOverAllAdGroupStats.setRelevance_Score(resultSet.getDouble("Client_Ad_Relevance_Score"));
        csvOverAllAdGroupStats.setActivity_End_Date(end_date);
        csvOverAllAdGroupStats.setActivity_Start_Date(start_date);
        csvOverAllAdGroupStats.setStats_Date(stats_date);


        return csvOverAllAdGroupStats;
    }
}
