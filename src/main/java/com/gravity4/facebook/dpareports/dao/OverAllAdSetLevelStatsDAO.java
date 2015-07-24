package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.mapper.OverAllAccountCSVMapper;
import com.gravity4.facebook.dpareports.mapper.OverAllAdSetCSVMapper;
import com.gravity4.facebook.dpareports.model.AdSetStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVOverAllAccountStats;
import com.gravity4.facebook.dpareports.model.CSVOverAllAdSetStats;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
public class OverAllAdSetLevelStatsDAO extends BaseDAO {


    public List<CSVOverAllAdSetStats> fileadsetlevelstats(final long page_id,Date Stats_date) {

        List<CSVOverAllAdSetStats> CSVOverAllAdSetStats;
        String SELECT = " SELECT * "
                + " FROM Overall_AdSet_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        CSVOverAllAdSetStats=getJdbcTemplate().query(SELECT,new OverAllAdSetCSVMapper());

        return CSVOverAllAdSetStats;
    }



    public void storeadsetlevelstats(final List<AdSetStatsLoader> adSetStatsLoaderList){

        String query = "INSERT INTO Overall_AdSet_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_AdSet_ID," +
                "Application_Ad_AdSet_Name," +
                "Client_Reports_Age_Stats_Range," +
                "Client_Reports_Gender_Stats," +
                "Client_Reports_Reach," +
                "Client_Reports_Frequency," +
                "Client_Reports_Impressions," +
                "Client_Reports_Clicks," +
                "Client_Reports_Total_Actions," +
                "Client_Reports_Social_Reach," +
                "Client_Reports_Social_Impressions," +
                "Client_Reports_Unique_Impressions," +
                "Client_Reports_Unique_Social_Impressions," +
                "Client_Reports_CPM," +
                "Client_Reports_CPP," +
                "Client_Reports_CPC," +
                "Client_Reports_CTR," +
                "Client_Reports_Spend," +
                "Stats_Date," +
                "Client_Reports_Ad_Activity_Date_Start," +
                "Client_Reports_Ad_Activity_Date_End," +
                "Client_Cost_Per_Unique_Click)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {
                AdSetStatsLoader adSetStatsLoader = adSetStatsLoaderList.get(i);

                java.sql.Date statsdate = new java.sql.Date(adSetStatsLoader.getStats_Date().getTime());
                java.sql.Date Activity_Start_Date = new java.sql.Date(adSetStatsLoader.getActivity_Start_Date().getTime());
                java.sql.Date Activity_End_Date = new java.sql.Date(adSetStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setString(3,adSetStatsLoader.getAdSet_Name());
                statement.setString(4, adSetStatsLoader.getAge_Range());
                statement.setString(5, adSetStatsLoader.getGender());
                statement.setInt(6, adSetStatsLoader.getReach());
                statement.setDouble(7, adSetStatsLoader.getFrequency());
                statement.setInt(8, adSetStatsLoader.getImpressions());
                statement.setInt(9, adSetStatsLoader.getClicks());
                statement.setInt(10, adSetStatsLoader.getTotal_Actions());
                statement.setInt(11, adSetStatsLoader.getSocial_Reach());
                statement.setInt(12, adSetStatsLoader.getSocial_Impressions());
                statement.setInt(13, adSetStatsLoader.getUnique_Impressions());
                statement.setLong(14, adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15, adSetStatsLoader.getCPM());
                statement.setDouble(16, adSetStatsLoader.getCPP());
                statement.setDouble(17, adSetStatsLoader.getCPC());
                statement.setDouble(18, adSetStatsLoader.getCTR());
                statement.setDouble(19, adSetStatsLoader.getSpend());
                statement.setDate(20, statsdate);
                statement.setDate(21, Activity_Start_Date);
                statement.setDate(22, Activity_End_Date);
                statement.setDouble(23, adSetStatsLoader.getCost_Per_Unique_Click());

            }

            @Override
            public int getBatchSize() {
                return adSetStatsLoaderList.size();
            }
        });
    }
}

