package com.gravity4.facebook.dpareports.dao.Region;

/**
 * Created by niranjan on 6/18/15.
 */


import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.Region.RegionAccountCSVMapper;
import com.gravity4.facebook.dpareports.model.CSVModels.Region.CSVRegionAllAccountStats;
import com.gravity4.facebook.dpareports.model.APImodels.CountryandRegion.CountryAndRegionAccountStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class RegionAccountLevelStatsDAO extends BaseDAO {

    public List<CSVRegionAllAccountStats> fileaccountlevelstats(final long page_id,Date Stats_date) {

        List<CSVRegionAllAccountStats> overAllAccountStatsList;
        String SELECT = " SELECT * "
                + " FROM RegionLevel_AdAccount_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        overAllAccountStatsList=getJdbcTemplate().query(SELECT,new RegionAccountCSVMapper());

        return overAllAccountStatsList;
    }


    public void storeaccountlevelstats(final List<CountryAndRegionAccountStatsLoader> accountStatsLoaderList) {

        String query = "INSERT INTO RegionLevel_AdAccount_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Ad_Account_ID," +
                "Application_Ad_Account_Name," +
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
                "Client_Cost_Per_Unique_Click)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {
                CountryAndRegionAccountStatsLoader accountStatsLoader = accountStatsLoaderList.get(i);

                Date statsdate = new Date(accountStatsLoader.getStats_Date().getTime());
                Date Activity_Start_Date = new Date(accountStatsLoader.getActivity_Start_Date().getTime());
                Date Activity_End_Date = new Date(accountStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, accountStatsLoader.getClient_ID());
                statement.setLong(2, accountStatsLoader.getAccount_ID());
                statement.setString(3,accountStatsLoader.getAccount_Name());
                statement.setString(4, accountStatsLoader.getRegion());
                statement.setInt(5, accountStatsLoader.getReach());
                statement.setDouble(6, accountStatsLoader.getFrequency());
                statement.setInt(7, accountStatsLoader.getImpressions());
                statement.setInt(8, accountStatsLoader.getClicks());
                statement.setInt(9, accountStatsLoader.getTotal_Actions());
                statement.setInt(10, accountStatsLoader.getSocial_Reach());
                statement.setInt(11, accountStatsLoader.getSocial_Impressions());
                statement.setInt(12, accountStatsLoader.getUnique_Impressions());
                statement.setInt(13, accountStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, accountStatsLoader.getCPM());
                statement.setDouble(15, accountStatsLoader.getCPP());
                statement.setDouble(16, accountStatsLoader.getCPC());
                statement.setDouble(17, accountStatsLoader.getCTR());
                statement.setDouble(18, accountStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);
                statement.setDouble(22, accountStatsLoader.getCost_Per_Unique_Click());
            }

            @Override
            public int getBatchSize() {
                return accountStatsLoaderList.size();
            }
        });
    }
}

