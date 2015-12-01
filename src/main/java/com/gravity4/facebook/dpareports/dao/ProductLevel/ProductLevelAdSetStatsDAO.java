package com.gravity4.facebook.dpareports.dao.ProductLevel;

/**
 * Created by niranjan on 6/23/15.
 */

import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.ProductLevel.ProductLevelAdSetCSVMapper;
import com.gravity4.facebook.dpareports.model.APImodels.AggregatedandProductLevel.AdSetStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVModels.ProductLevel.CSVProductLevelAdSetStats;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
@SuppressWarnings("unchecked")
public class ProductLevelAdSetStatsDAO extends BaseDAO {

    public List<CSVProductLevelAdSetStats> fileproductlevelaccountstats(final long page_id,Date Stats_date) {

        List<CSVProductLevelAdSetStats> csvProductLevelAdSetStatsList;
        String SELECT = " SELECT * "
                + " FROM Product_AdSet_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        csvProductLevelAdSetStatsList=getJdbcTemplate().query(SELECT,new ProductLevelAdSetCSVMapper());

        return csvProductLevelAdSetStatsList;
    }

    public void storeadsetlevelstats(final List<AdSetStatsLoader> adSetStatsLoaderList){

        String query = "INSERT INTO Product_AdSet_Statistics_Results" +
                "(Application_Client_ID," +
                "Client_AdSet_ID," +
                "Client_AdSet_Name," +
                "Client_Product_ID," +
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
                "Client_Reports_Ad_Activity_Date_End)" +
                "VALUES" +
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                statement.setString(4, adSetStatsLoader.getProduct_ID());
                statement.setInt(5, adSetStatsLoader.getReach());
                statement.setDouble(6, adSetStatsLoader.getFrequency());
                statement.setInt(7, adSetStatsLoader.getImpressions());
                statement.setInt(8, adSetStatsLoader.getClicks());
                statement.setInt(9, adSetStatsLoader.getTotal_Actions());
                statement.setInt(10, adSetStatsLoader.getSocial_Reach());
                statement.setInt(11, adSetStatsLoader.getSocial_Impressions());
                statement.setInt(12, adSetStatsLoader.getUnique_Impressions());
                statement.setInt(13, adSetStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, adSetStatsLoader.getCPM());
                statement.setDouble(15, adSetStatsLoader.getCPP());
                statement.setDouble(16, adSetStatsLoader.getCPC());
                statement.setDouble(17, adSetStatsLoader.getCTR());
                statement.setDouble(18, adSetStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);

            }

            @Override
            public int getBatchSize() {
                return adSetStatsLoaderList.size();
            }
        });
    }
}
