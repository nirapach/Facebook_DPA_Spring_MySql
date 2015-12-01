package com.gravity4.facebook.dpareports.mapper.Actions;

import com.gravity4.facebook.dpareports.model.CSVModels.Actions.CSVActionsAdGroupStats;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by niranjan on 7/23/15.
 */
@SuppressWarnings("unchecked")
public class ActionsAdGroupCSVMapper implements RowMapper<CSVActionsAdGroupStats> {


    @Override
    public CSVActionsAdGroupStats mapRow(ResultSet resultSet, int i) throws SQLException {


        CSVActionsAdGroupStats csvOverAllAdGroupStats=new CSVActionsAdGroupStats();


        csvOverAllAdGroupStats.setPage_ID(resultSet.getLong("Application_Client_ID"));
        csvOverAllAdGroupStats.setAdGroup_ID(resultSet.getLong("Application_AdGroup_ID"));
        csvOverAllAdGroupStats.setAdGroup_Name(resultSet.getString("Application_AdGroup_Name"));
        csvOverAllAdGroupStats.setAction_Type(resultSet.getString("Client_Reports_Action_Type"));
        csvOverAllAdGroupStats.setAction_Value(resultSet.getInt("Client_Reports_Value"));
        csvOverAllAdGroupStats.setActivity_End_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_End"));
        csvOverAllAdGroupStats.setActivity_Start_Date(resultSet.getDate("Client_Reports_Ad_Activity_Date_Start"));
        csvOverAllAdGroupStats.setStats_Date(resultSet.getDate("Stats_Date"));


        return csvOverAllAdGroupStats;
    }
}
