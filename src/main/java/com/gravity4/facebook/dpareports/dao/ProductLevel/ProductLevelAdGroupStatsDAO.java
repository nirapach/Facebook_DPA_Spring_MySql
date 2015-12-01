package com.gravity4.facebook.dpareports.dao.ProductLevel;

/**
 * Created by niranjan on 6/23/15.
 */

import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.ProductLevel.ProductLevelAdGroupCSVMapper;
import com.gravity4.facebook.dpareports.model.APImodels.AggregatedandProductLevel.AdGroupStatsLoader;
import com.gravity4.facebook.dpareports.model.CSVModels.ProductLevel.CSVProductLevelAdGroupStats;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class ProductLevelAdGroupStatsDAO extends BaseDAO {

    public List<CSVProductLevelAdGroupStats> fileproductleveladgroupstats(final long page_id,Date Stats_date) {

        List<CSVProductLevelAdGroupStats> csvProductLevelAdGroupStatsList;
        String SELECT = " SELECT * "
                + " FROM Product_Ad_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        csvProductLevelAdGroupStatsList=getJdbcTemplate().query(SELECT,new ProductLevelAdGroupCSVMapper());

        return csvProductLevelAdGroupStatsList;
    }


    public void storeadgrouplevelstats(final List<AdGroupStatsLoader> adGroupStatsLoaderList){

        String query = "INSERT INTO Product_Ad_Statistics_Results" +
                "(Application_Client_ID," +
                "Client_Ad_Group_ID," +
                "Client_Ad_Group_Name," +
                "Client_Product_ID," +
                "Client_Reports_Reach," +
                "Client_Reports_Frequency," +
                "Client_Reports_Impressions," +
                "Client_Reports_Clicks," +
                "Client_Reports_Total_Actions," +
                "Client_Reports_Relevancy_Score," +
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
                "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";


        getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement statement, int i) throws SQLException {

                AdGroupStatsLoader adGroupStatsLoader = adGroupStatsLoaderList.get(i);

                java.sql.Date statsdate = new java.sql.Date(adGroupStatsLoader.getStats_Date().getTime());
                java.sql.Date Activity_Start_Date = new java.sql.Date(adGroupStatsLoader.getActivity_Start_Date().getTime());
                java.sql.Date Activity_End_Date = new java.sql.Date(adGroupStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, adGroupStatsLoader.getClient_ID());
                statement.setLong(2, adGroupStatsLoader.getAdGroup_ID());
                statement.setString(3,adGroupStatsLoader.getAdGroup_Name());
                statement.setString(4, adGroupStatsLoader.getProduct_ID());
                statement.setInt(5, adGroupStatsLoader.getReach());
                statement.setDouble(6, adGroupStatsLoader.getFrequency());
                statement.setLong(7, adGroupStatsLoader.getImpressions());
                statement.setLong(8, adGroupStatsLoader.getClicks());
                statement.setLong(9, adGroupStatsLoader.getTotal_Actions());
                statement.setDouble(10, adGroupStatsLoader.getRelevance_Score());
                statement.setInt(11, adGroupStatsLoader.getSocial_Reach());
                statement.setInt(12, adGroupStatsLoader.getSocial_Impressions());
                statement.setInt(13, adGroupStatsLoader.getUnique_Impressions());
                statement.setInt(14, adGroupStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15, adGroupStatsLoader.getCPM());
                statement.setDouble(16, adGroupStatsLoader.getCPP());
                statement.setDouble(17, adGroupStatsLoader.getCPC());
                statement.setDouble(18, adGroupStatsLoader.getCTR());
                statement.setDouble(19, adGroupStatsLoader.getSpend());
                statement.setDate(20, statsdate);
                statement.setDate(21, Activity_Start_Date);
                statement.setDate(22, Activity_End_Date);

            }

            @Override
            public int getBatchSize() {
                return adGroupStatsLoaderList.size();
            }
        });
    }
}
