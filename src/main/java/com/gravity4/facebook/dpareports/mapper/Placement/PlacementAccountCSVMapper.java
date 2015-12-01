package com.gravity4.facebook.dpareports.mapper.Placement;


import com.gravity4.facebook.dpareports.model.CSVModels.Placement.CSVPlacementAccountStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class PlacementAccountCSVMapper implements RowMapper<CSVPlacementAccountStats> {


    @Override
    public CSVPlacementAccountStats mapRow(ResultSet resultSet, int i) throws SQLException {

        CSVPlacementAccountStats csvOverAllAccountStats=new CSVPlacementAccountStats();


        csvOverAllAccountStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAccountStats.setAccount_ID(resultSet.getLong("Application_Ad_Account_ID"));
        csvOverAllAccountStats.setAccount_Name(resultSet.getString("Application_Ad_Account_Name"));
        csvOverAllAccountStats.setPlacement(resultSet.getString("Client_Reports_Placement"));
        csvOverAllAccountStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvOverAllAccountStats.setImpression_Device(resultSet.getString("Client_Reports_Impression_Device"));
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
        csvOverAllAccountStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvOverAllAccountStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvOverAllAccountStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvOverAllAccountStats;
    }


}
