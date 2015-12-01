package com.gravity4.facebook.dpareports.mapper.Placement;



import com.gravity4.facebook.dpareports.model.CSVModels.Placement.CSVPlacementAdSetStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class PlacementAdSetCSVMapper implements RowMapper<CSVPlacementAdSetStats> {


    @Override
    public CSVPlacementAdSetStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVPlacementAdSetStats csvOverAllAdSetStats=new CSVPlacementAdSetStats();


        csvOverAllAdSetStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAdSetStats.setAdSet_ID(resultSet.getLong("Application_AdSet_ID"));
        csvOverAllAdSetStats.setAdSet_Name(resultSet.getString("Application_AdSet_Name"));
        csvOverAllAdSetStats.setPlacement(resultSet.getString("Client_Reports_Placement"));
        csvOverAllAdSetStats.setClicks(resultSet.getInt("Client_Reports_Clicks"));
        csvOverAllAdSetStats.setImpression_Device(resultSet.getString("Client_Reports_Impression_Device"));
        csvOverAllAdSetStats.setReach(resultSet.getInt("Client_Reports_Reach"));
        csvOverAllAdSetStats.setFrequency(resultSet.getDouble("Client_Reports_Frequency"));
        csvOverAllAdSetStats.setImpressions(resultSet.getInt("Client_Reports_Impressions"));
        csvOverAllAdSetStats.setTotal_Actions(resultSet.getInt("Client_Reports_Total_Actions"));
        csvOverAllAdSetStats.setSocial_Reach(resultSet.getInt("Client_Reports_Social_Reach"));
        csvOverAllAdSetStats.setSocial_Impressions(resultSet.getInt("Client_Reports_Social_Impressions"));
        csvOverAllAdSetStats.setUnique_Social_Impressions(resultSet.getInt("Client_Reports_Unique_Social_Impressions"));
        csvOverAllAdSetStats.setUnique_Impressions(resultSet.getInt("Client_Reports_Unique_Impressions"));
        csvOverAllAdSetStats.setCPM(resultSet.getDouble("Client_Reports_CPM"));
        csvOverAllAdSetStats.setCPC(resultSet.getDouble("Client_Reports_CPC"));
        csvOverAllAdSetStats.setCPP(resultSet.getDouble("Client_Reports_CPP"));
        csvOverAllAdSetStats.setCTR(resultSet.getDouble("Client_Reports_CTR"));
        csvOverAllAdSetStats.setSpend(resultSet.getDouble("Client_Reports_Spend"));
        csvOverAllAdSetStats.setCost_Per_Unique_Click(resultSet.getDouble("Client_Cost_Per_Unique_Click"));
        csvOverAllAdSetStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvOverAllAdSetStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvOverAllAdSetStats.setStats_Date(resultSet.getDate("Stats_Date"));



        return csvOverAllAdSetStats;
    }
}
