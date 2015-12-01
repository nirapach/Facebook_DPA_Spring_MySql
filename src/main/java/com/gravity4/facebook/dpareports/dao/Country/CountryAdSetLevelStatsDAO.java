package com.gravity4.facebook.dpareports.dao.Country;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.Country.CountryAdSetCSVMapper;
import com.gravity4.facebook.dpareports.model.CSVModels.Country.CSVCountryAllAdSetStats;
import com.gravity4.facebook.dpareports.model.APImodels.CountryandRegion.CountryandRegionAdSetStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class CountryAdSetLevelStatsDAO extends BaseDAO {


    public List<CSVCountryAllAdSetStats> fileadsetlevelstats(final long page_id,Date Stats_date) {

        List<CSVCountryAllAdSetStats> CSVOverAllAdSetStats;
        String SELECT = " SELECT * "
                + " FROM CountryLevel_AdSet_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        CSVOverAllAdSetStats=getJdbcTemplate().query(SELECT,new CountryAdSetCSVMapper());

        return CSVOverAllAdSetStats;
    }



    public void storeadsetlevelstats(final List<CountryandRegionAdSetStatsLoader> adSetStatsLoaderList){

        String query = "INSERT INTO CountryLevel_AdSet_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_AdSet_ID," +
                "Application_AdSet_Name," +
                "Country," +
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
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {
                CountryandRegionAdSetStatsLoader adSetStatsLoader = adSetStatsLoaderList.get(i);

                Date statsdate = new Date(adSetStatsLoader.getStats_Date().getTime());
                Date Activity_Start_Date = new Date(adSetStatsLoader.getActivity_Start_Date().getTime());
                Date Activity_End_Date = new Date(adSetStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, adSetStatsLoader.getClient_ID());
                statement.setLong(2, adSetStatsLoader.getAdSet_ID());
                statement.setString(3,adSetStatsLoader.getAdSet_Name());
                statement.setString(4, adSetStatsLoader.getCountry());
                statement.setInt(5, adSetStatsLoader.getReach());
                statement.setDouble(6, adSetStatsLoader.getFrequency());
                statement.setInt(7, adSetStatsLoader.getImpressions());
                statement.setInt(8, adSetStatsLoader.getClicks());
                statement.setInt(9, adSetStatsLoader.getTotal_Actions());
                statement.setInt(10, adSetStatsLoader.getSocial_Reach());
                statement.setInt(11, adSetStatsLoader.getSocial_Impressions());
                statement.setInt(12, adSetStatsLoader.getUnique_Impressions());
                statement.setLong(13, adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, adSetStatsLoader.getCPM());
                statement.setDouble(15, adSetStatsLoader.getCPP());
                statement.setDouble(16, adSetStatsLoader.getCPC());
                statement.setDouble(17, adSetStatsLoader.getCTR());
                statement.setDouble(18, adSetStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);
                statement.setDouble(22, adSetStatsLoader.getCost_Per_Unique_Click());

            }

            @Override
            public int getBatchSize() {
                return adSetStatsLoaderList.size();
            }
        });
    }
}

