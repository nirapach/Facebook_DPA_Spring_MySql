package com.gravity4.facebook.dpareports.dao.Placement;

/**
 * Created by niranjan on 6/18/15.
 */



import com.gravity4.facebook.dpareports.dao.BaseDAO;
import com.gravity4.facebook.dpareports.mapper.Placement.PlacementCampaignCSVMapper;
import com.gravity4.facebook.dpareports.model.CSVModels.Placement.CSVPlacementCampaignStats;
import com.gravity4.facebook.dpareports.model.APImodels.Placement.PlacementCampaignStatsLoader;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Component
@SuppressWarnings("unchecked")
public class PlacementCampaignLevelStatsDAO extends BaseDAO {

    public List<CSVPlacementCampaignStats> fileadsetlevelstats(final long page_id,Date Stats_date) {

        List<CSVPlacementCampaignStats> csvOverAllCampaignStatsList;
        String SELECT = " SELECT * "
                + " FROM PlacementLevel_Campaign_Statistics_Results"
                + " WHERE Application_Client_ID="+page_id+" and Stats_Date="+"'"+Stats_date+"'";

        csvOverAllCampaignStatsList=getJdbcTemplate().query(SELECT,new PlacementCampaignCSVMapper());

        return csvOverAllCampaignStatsList;
    }

    public void storecamapignlevelstats(final List<PlacementCampaignStatsLoader> campaignStatsLoaderList) {

        String query = "INSERT INTO PlacementLevel_Campaign_Statistics_Results" +
                "(Application_Client_ID," +
                "Application_Campaign_ID," +
                "Application_Campaign_Name," +
                "Client_Reports_Placement," +
                "Client_Reports_Impression_Device," +
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

                PlacementCampaignStatsLoader campaignStatsLoader = campaignStatsLoaderList.get(i);

                Date statsdate = new Date(campaignStatsLoader.getStats_Date().getTime());
                Date Activity_Start_Date = new Date(campaignStatsLoader.getActivity_Start_Date().getTime());
                Date Activity_End_Date = new Date(campaignStatsLoader.getActivity_End_Date().getTime());

                statement.setLong(1, campaignStatsLoader.getClient_ID());
                statement.setLong(2, campaignStatsLoader.getCampaign_ID());
                statement.setString(3,campaignStatsLoader.getCampaign_Name());
                statement.setString(4, campaignStatsLoader.getPlacement());
                statement.setString(5, campaignStatsLoader.getImpression_Device());
                statement.setInt(6, campaignStatsLoader.getReach());
                statement.setDouble(7, campaignStatsLoader.getFrequency());
                statement.setInt(8, campaignStatsLoader.getImpressions());
                statement.setInt(9, campaignStatsLoader.getClicks());
                statement.setInt(10, campaignStatsLoader.getTotal_Actions());
                statement.setInt(11, campaignStatsLoader.getSocial_Reach());
                statement.setInt(12, campaignStatsLoader.getSocial_Impressions());
                statement.setInt(13, campaignStatsLoader.getUnique_Impressions());
                statement.setInt(14, campaignStatsLoader.getUnique_Social_Impressions());
                statement.setDouble(15, campaignStatsLoader.getCPM());
                statement.setDouble(16, campaignStatsLoader.getCPP());
                statement.setDouble(17, campaignStatsLoader.getCPC());
                statement.setDouble(18, campaignStatsLoader.getCTR());
                statement.setDouble(19, campaignStatsLoader.getSpend());
                statement.setDate(20, statsdate);
                statement.setDate(21, Activity_Start_Date);
                statement.setDate(22, Activity_End_Date);
                statement.setDouble(23, campaignStatsLoader.getCost_Per_Unique_Click());
            }

            @Override
            public int getBatchSize() {
                return campaignStatsLoaderList.size();
            }
        });

    }
}

