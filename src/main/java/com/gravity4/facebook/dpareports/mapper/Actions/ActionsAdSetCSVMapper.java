package com.gravity4.facebook.dpareports.mapper.Actions;

import com.gravity4.facebook.dpareports.model.CSVModels.Actions.CSVActionsAdSetStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ActionsAdSetCSVMapper implements RowMapper<CSVActionsAdSetStats> {


    @Override
    public CSVActionsAdSetStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVActionsAdSetStats csvOverAllAdGroupStats=new CSVActionsAdSetStats();


        csvOverAllAdGroupStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAdGroupStats.setAdSet_ID(resultSet.getLong("Application_AdSet_ID"));
        csvOverAllAdGroupStats.setAdSet_Name(resultSet.getString("Application_AdSet_Name"));
        csvOverAllAdGroupStats.setAction_Type(resultSet.getString("Client_Reports_Action_Type"));
        csvOverAllAdGroupStats.setAction_Value(resultSet.getInt("Client_Reports_Value"));
        csvOverAllAdGroupStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvOverAllAdGroupStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvOverAllAdGroupStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvOverAllAdGroupStats;
    }
}
