package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVProductLevelAccountStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
public class ProductLevelAccountsCSVMapper implements RowMapper<CSVProductLevelAccountStats> {


    @Override
    public CSVProductLevelAccountStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVProductLevelAccountStats csvProductLevelStats=new CSVProductLevelAccountStats();
        java.util.Date stats_date = new java.util.Date(resultSet.getDate("Stats_Date").getTime());
        java.util.Date start_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start").getTime());
        java.util.Date end_date = new java.util.Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End").getTime());

        csvProductLevelStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvProductLevelStats.setID(resultSet.getLong("Application_Ad_Account_ID"));
        csvProductLevelStats.setProduct_ID(resultSet.getString("Client_Product_ID"));
        csvProductLevelStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvProductLevelStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvProductLevelStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvProductLevelStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvProductLevelStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvProductLevelStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvProductLevelStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvProductLevelStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvProductLevelStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvProductLevelStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvProductLevelStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvProductLevelStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvProductLevelStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvProductLevelStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvProductLevelStats.setActivity_End_Date(end_date);
        csvProductLevelStats.setActivity_Start_Date(start_date);
        csvProductLevelStats.setStats_Date(stats_date);


        return csvProductLevelStats;


    }
}
