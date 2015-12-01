package com.gravity4.facebook.dpareports.model.APImodels.CountryandRegion;

import java.util.Date;

/**
 * Created by niranjan on 6/18/15.
 */
public class CountryAndRegionAdGroupStatsLoader {

    private long Client_ID;
    private long AdGroup_ID;
    private String Country;
    private String Region;
    private int Reach;
    private double Frequency;
    private int Clicks;
    private int Total_Actions;
    private int Impressions;
    private int Social_Reach;
    private double Relevance_Score;
    private int Social_Impressions;
    private int Unique_Impressions;
    private int Unique_Social_Impressions;
    private double CPM;
    private double CPP;
    private double Spend;
    private double CPC;
    private double CTR;
    private double Cost_Per_Unique_Click;
    private Date Activity_Start_Date;
    private Date Activity_End_Date;
    private Date Stats_Date;
    private String AdGroup_Name;

    /*getters and setters
     */

    public long getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(long client_ID) {
        Client_ID = client_ID;
    }

    public long getAdGroup_ID() {
        return AdGroup_ID;
    }

    public void setAdGroup_ID(long adGroup_ID) {
        AdGroup_ID = adGroup_ID;
    }


    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public int getReach() {
        return Reach;
    }

    public void setReach(int reach) {
        Reach = reach;
    }

    public double getFrequency() {
        return Frequency;
    }

    public void setFrequency(double frequency) {
        Frequency = frequency;
    }

    public int getClicks() {
        return Clicks;
    }

    public void setClicks(int clicks) {
        Clicks = clicks;
    }

    public int getTotal_Actions() {
        return Total_Actions;
    }

    public void setTotal_Actions(int total_Actions) {
        Total_Actions = total_Actions;
    }

    public int getImpressions() {
        return Impressions;
    }

    public void setImpressions(int impressions) {
        Impressions = impressions;
    }

    public int getSocial_Reach() {
        return Social_Reach;
    }

    public void setSocial_Reach(int social_Reach) {
        Social_Reach = social_Reach;
    }

    public double getRelevance_Score() {
        return Relevance_Score;
    }

    public void setRelevance_Score(double relevance_Score) {
        Relevance_Score = relevance_Score;
    }

    public int getSocial_Impressions() {
        return Social_Impressions;
    }

    public void setSocial_Impressions(int social_Impressions) {
        Social_Impressions = social_Impressions;
    }

    public int getUnique_Impressions() {
        return Unique_Impressions;
    }

    public void setUnique_Impressions(int unique_Impressions) {
        Unique_Impressions = unique_Impressions;
    }

    public int getUnique_Social_Impressions() {
        return Unique_Social_Impressions;
    }

    public void setUnique_Social_Impressions(int unique_Social_Impressions) {
        Unique_Social_Impressions = unique_Social_Impressions;
    }

    public double getCPM() {
        return CPM;
    }

    public void setCPM(double CPM) {
        this.CPM = CPM;
    }

    public double getCPP() {
        return CPP;
    }

    public void setCPP(double CPP) {
        this.CPP = CPP;
    }

    public double getSpend() {
        return Spend;
    }

    public void setSpend(double spend) {
        Spend = spend;
    }

    public double getCPC() {
        return CPC;
    }

    public void setCPC(double CPC) {
        this.CPC = CPC;
    }

    public double getCTR() {
        return CTR;
    }

    public void setCTR(double CTR) {
        this.CTR = CTR;
    }

    public double getCost_Per_Unique_Click() {
        return Cost_Per_Unique_Click;
    }

    public void setCost_Per_Unique_Click(double cost_Per_Unique_Click) {
        Cost_Per_Unique_Click = cost_Per_Unique_Click;
    }

    public Date getActivity_Start_Date() {
        return Activity_Start_Date;
    }

    public void setActivity_Start_Date(Date activity_Start_Date) {
        Activity_Start_Date = activity_Start_Date;
    }

    public Date getActivity_End_Date() {
        return Activity_End_Date;
    }

    public void setActivity_End_Date(Date activity_End_Date) {
        Activity_End_Date = activity_End_Date;
    }

    public Date getStats_Date() {
        return Stats_Date;
    }

    public void setStats_Date(Date stats_Date) {
        Stats_Date = stats_Date;
    }

    public String getAdGroup_Name() {
        return AdGroup_Name;
    }

    public void setAdGroup_Name(String adGroup_Name) {
        AdGroup_Name = adGroup_Name;
    }

    @Override
    public String toString() {
        return "CountryAndRegionAdGroupStatsLoader{" +
                "Client_ID=" + Client_ID +
                ", AdGroup_ID=" + AdGroup_ID +
                ", CountryandRegion='" + Country + '\'' +
                ", Region='" + Region + '\'' +
                ", Reach=" + Reach +
                ", Frequency=" + Frequency +
                ", Clicks=" + Clicks +
                ", Total_Actions=" + Total_Actions +
                ", Impressions=" + Impressions +
                ", Social_Reach=" + Social_Reach +
                ", Relevance_Score=" + Relevance_Score +
                ", Social_Impressions=" + Social_Impressions +
                ", Unique_Impressions=" + Unique_Impressions +
                ", Unique_Social_Impressions=" + Unique_Social_Impressions +
                ", CPM=" + CPM +
                ", CPP=" + CPP +
                ", Spend=" + Spend +
                ", CPC=" + CPC +
                ", CTR=" + CTR +
                ", Cost_Per_Unique_Click=" + Cost_Per_Unique_Click +
                ", Activity_Start_Date=" + Activity_Start_Date +
                ", Activity_End_Date=" + Activity_End_Date +
                ", Stats_Date=" + Stats_Date +
                ", AdGroup_Name='" + AdGroup_Name + '\'' +
                '}';
    }
}
