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

        csvProductLevelStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvProductLevelStats.setID(resultSet.getLong("Application_Ad_Account_ID"));
        csvProductLevelStats.setName(resultSet.getString("Application_Ad_Account_Name"));
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
        csvProductLevelStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvProductLevelStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvProductLevelStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvProductLevelStats;


    }
}
