package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVOverAllAccountStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
public class OverAllAccountCSVMapper implements RowMapper<CSVOverAllAccountStats> {


    @Override
    public CSVOverAllAccountStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVOverAllAccountStats csvOverAllAccountStats=new CSVOverAllAccountStats();
        java.util.Date stats_date = new java.util.Date(resultSet.getDate("Stats_Date").getTime());
        java.util.Date start_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start").getTime());
        java.util.Date end_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End").getTime());

        csvOverAllAccountStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAccountStats.setAccount_ID(resultSet.getLong("Application_Ad_Account_ID"));
        csvOverAllAccountStats.setAge_Range(resultSet.getString("Client_Reports_Age_Stats_Range"));
        csvOverAllAccountStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvOverAllAccountStats.setGender(resultSet.getString("Client_Reports_Gender_Stats"));
        csvOverAllAccountStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvOverAllAccountStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvOverAllAccountStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvOverAllAccountStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvOverAllAccountStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvOverAllAccountStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvOverAllAccountStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvOverAllAccountStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvOverAllAccountStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvOverAllAccountStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvOverAllAccountStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvOverAllAccountStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvOverAllAccountStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvOverAllAccountStats.setCost_Per_Unique_Click(resultSet.getDouble("Client_Cost_Per_Unique_Click"));
        csvOverAllAccountStats.setActivity_End_Date(end_date);
        csvOverAllAccountStats.setActivity_Start_Date(start_date);
        csvOverAllAccountStats.setStats_Date(stats_date);


        return csvOverAllAccountStats;
    }


}
