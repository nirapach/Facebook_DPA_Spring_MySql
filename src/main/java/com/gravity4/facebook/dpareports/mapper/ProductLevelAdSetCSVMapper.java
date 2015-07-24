package com.gravity4.facebook.dpareports.mapper;

import com.gravity4.facebook.dpareports.model.CSVProductLevelAdSetStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ProductLevelAdSetCSVMapper implements RowMapper<CSVProductLevelAdSetStats> {


    @Override
    public CSVProductLevelAdSetStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVProductLevelAdSetStats csvProductLevelAdSetStats=new CSVProductLevelAdSetStats();

        csvProductLevelAdSetStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvProductLevelAdSetStats.setID(resultSet.getLong("Client_AdSet_ID"));
        csvProductLevelAdSetStats.setName(resultSet.getString("Client_AdSet_Name"));
        csvProductLevelAdSetStats.setProduct_ID(resultSet.getString("Client_Product_ID"));
        csvProductLevelAdSetStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvProductLevelAdSetStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvProductLevelAdSetStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvProductLevelAdSetStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvProductLevelAdSetStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvProductLevelAdSetStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvProductLevelAdSetStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvProductLevelAdSetStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvProductLevelAdSetStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvProductLevelAdSetStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvProductLevelAdSetStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvProductLevelAdSetStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvProductLevelAdSetStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvProductLevelAdSetStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvProductLevelAdSetStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvProductLevelAdSetStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvProductLevelAdSetStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvProductLevelAdSetStats;


    }
}
