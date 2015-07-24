package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/23/15.
 */

import com.gravity4.facebook.dpareports.mapper.ProductLevelCampaignCSVMapper;
import com.gravity4.facebook.dpareports.model.CSVProductLevelCampaignStats;
import com.gravity4.facebook.dpareports.model.CampaignStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class ProductLevelCampaignStatsDAO extends BaseDAO {


    public List<CSVProductLevelCampaignStats> fileproductlevelcampaignstats(final long page_id,Date Stats_date) {

        List<CSVProductLevelCampaignStats> csvProductLevelCampaignStatsList;
        String SELECT = " SELECT * "
                + " FROM Product_Campaign_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        csvProductLevelCampaignStatsList=getJdbcTemplate().query(SELECT,new ProductLevelCampaignCSVMapper());

        return csvProductLevelCampaignStatsList;
    }

    public void storecampaignlevelstats(final List<CampaignStatsLoader> campaignStatsLoaderList){

        String query = "INSERT INTO Product_Campaign_Statistics_Results" +
                "(Application_Client_ID," +
                "Client_Campaign_ID," +
                "Client_Campaign_Name," +
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

                CampaignStatsLoader campaignStatsLoader = campaignStatsLoaderList.get(i);

                java.sql.Date statsdate = new java.sql.Date(campaignStatsLoader.getStats_Date().getTime());
                java.sql.Date Activity_Start_Date = new java.sql.Date(campaignStatsLoader.getActivity_Start_Date().getTime());
                java.sql.Date Activity_End_Date = new java.sql.Date(campaignStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, campaignStatsLoader.getClient_ID());
                statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                statement.setString(3,campaignStatsLoader.getCampaign_Name());
                statement.setString(4, campaignStatsLoader.getProduct_ID());
                statement.setLong(5, campaignStatsLoader.getReach());
                statement.setDouble(6, campaignStatsLoader.getFrequency());
                statement.setLong(7, campaignStatsLoader.getImpressions());
                statement.setLong(8, campaignStatsLoader.getClicks());
                statement.setLong(9, campaignStatsLoader.getTotal_Actions());
                statement.setLong(10, campaignStatsLoader.getSocial_Reach());
                statement.setLong(11, campaignStatsLoader.getSocial_Impressions());
                statement.setLong(12, campaignStatsLoader.getUnique_Impressions());
                statement.setLong(13, campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(14, campaignStatsLoader.getCPM());
                statement.setDouble(15, campaignStatsLoader.getCPP());
                statement.setDouble(16, campaignStatsLoader.getCPC());
                statement.setDouble(17, campaignStatsLoader.getCTR());
                statement.setDouble(18, campaignStatsLoader.getSpend());
                statement.setDate(19, statsdate);
                statement.setDate(20, Activity_Start_Date);
                statement.setDate(21, Activity_End_Date);
            }

            @Override
            public int getBatchSize() {
                return campaignStatsLoaderList.size();  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
    }
}
