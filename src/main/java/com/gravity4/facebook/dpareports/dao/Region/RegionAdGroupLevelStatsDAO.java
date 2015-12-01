package com.gravity4.facebook.dpareports.dao.Region;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.Region.RegionAdGroupCSVMapper;
import com.gravity4.facebook.dpareports.model.CSVModels.Region.CSVRegionAdGroupStats;
import com.gravity4.facebook.dpareports.model.APImodels.CountryandRegion.CountryAndRegionAdGroupStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class RegionAdGroupLevelStatsDAO extends BaseDAO {

    public List<CSVRegionAdGroupStats> fileadgrouplevelstats(final long page_id,Date Stats_date) {

        List<CSVRegionAdGroupStats> csvOverAllAdGroupStatsList;
        String SELECT = " SELECT * "
                + " FROM RegionLevel_AdGroup_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        csvOverAllAdGroupStatsList=getJdbcTemplate().query(SELECT,new RegionAdGroupCSVMapper());

        return csvOverAllAdGroupStatsList;
    }


    public void storeadgrouplevelstats(final List<CountryAndRegionAdGroupStatsLoader> adGroupStatsLoaderList){


        String query = "INSERT INTO RegionLevel_AdGroup_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_AdGroup_ID," +
                "Application_AdGroup_Name," +
                "Region," +
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
                "Client_Cost_Per_Unique_Click," +
                "Client_Ad_Relevance_Score)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {

                CountryAndRegionAdGroupStatsLoader adGroupStatsLoader = adGroupStatsLoaderList.get(i);

                Date statsdate = new Date(adGroupStatsLoader.getStats_Date().getTime());
                Date Activity_Start_Date = new Date(adGroupStatsLoader.getActivity_Start_Date().getTime());
                Date Activity_End_Date = new Date(adGroupStatsLoader.getActivity_End_Date().getTime());


                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setString(3,adGroupStatsLoader.getAdGroup_Name());
                statement.setString(4, adGroupStatsLoader.getRegion());
                statement.setInt(5, adGroupStatsLoader.getReach());
                statement.setDouble(6, adGroupStatsLoader.getFrequency());
                statement.setInt(7, adGroupStatsLoader.getImpressions());
                statement.setInt(8, adGroupStatsLoader.getClicks());
                statement.setInt(9, adGroupStatsLoader.getTotal_Actions());
                statement.setInt(10, adGroupStatsLoader.getSocial_Reach());
                statement.setInt(11, adGroupStatsLoader.getSocial_Impressions());
                statement.setInt(12, adGroupStatsLoader.getUnique_Impressions());
                statement.setInt(13, adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, adGroupStatsLoader.getCPM());
                statement.setDouble(15, adGroupStatsLoader.getCPP());
                statement.setDouble(16, adGroupStatsLoader.getCPC());
                statement.setDouble(17, adGroupStatsLoader.getCTR());
                statement.setDouble(18, adGroupStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);
                statement.setDouble(22, adGroupStatsLoader.getCost_Per_Unique_Click());
                statement.setDouble(23, adGroupStatsLoader.getRelevance_Score());
            }

            @Override
            public int getBatchSize() {
                return adGroupStatsLoaderList.size();
            }
        });

    }
}

