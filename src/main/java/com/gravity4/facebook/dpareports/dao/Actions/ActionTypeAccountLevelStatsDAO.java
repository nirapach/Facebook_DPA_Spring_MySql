package com.gravity4.facebook.dpareports.dao.Actions;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.Actions.ActionsAccountCSVMapper;
import com.gravity4.facebook.dpareports.model.APImodels.Actions.ActionTypeStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVModels.Actions.CSVActionsAccountStats;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class ActionTypeAccountLevelStatsDAO extends BaseDAO {


    public List<CSVActionsAccountStats> fileactionsdaccountlevelstats(final long page_id,Date Stats_date) {

        List<CSVActionsAccountStats> csvActionsAccountStatsList;
        String SELECT = " SELECT * "
                + " FROM ActionTypes_AdAccount_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";


        csvActionsAccountStatsList=getJdbcTemplate().query(SELECT,new ActionsAccountCSVMapper());

        return csvActionsAccountStatsList;
    }


    public void storeactionsaccountlevelstats(final List<ActionTypeStatsLoader> adGroupStatsLoaderList){


        String query = "INSERT INTO ActionTypes_AdAccount_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_AdAccount_ID," +
                "Application_AdAccount_Name," +
                "Client_Reports_Action_Type," +
                "Client_Reports_Value," +
                "Stats_Date," +
                "Client_Reports_Ad_Activity_Date_Start," +
                "Client_Reports_Ad_Activity_Date_End)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?);";

        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {

                ActionTypeStatsLoader adGroupStatsLoader = adGroupStatsLoaderList.get(i);

                Date statsdate = new Date(adGroupStatsLoader.getStats_Date().getTime());
                Date Activity_Start_Date = new Date(adGroupStatsLoader.getActivity_Start_Date().getTime());
                Date Activity_End_Date = new Date(adGroupStatsLoader.getActivity_End_Date().getTime());


                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2,adGroupStatsLoader.getID());
                statement.setString(3, adGroupStatsLoader.getName());
                statement.setString(4, adGroupStatsLoader.getType());
                statement.setInt(5, adGroupStatsLoader.getValue());
                statement.setDate(6, statsdate);
                statement.setDate(7, Activity_Start_Date);
                statement.setDate(8,Activity_End_Date);
            }

            @Override
            public int getBatchSize() {
                return adGroupStatsLoaderList.size();
            }
        });

    }

}

