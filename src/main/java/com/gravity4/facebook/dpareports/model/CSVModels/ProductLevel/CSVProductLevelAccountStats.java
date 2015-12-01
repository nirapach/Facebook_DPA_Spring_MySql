package com.gravity4.facebook.dpareports.model.CSVModels.ProductLevel;

import java.sql.Date;

/**
 * Created by niranjan on 7/23/15.
 */
public class CSVProductLevelAccountStats {

    private long page_ID;
    private long ID;
    private String Name;
    private String Product_ID;
    private int Reach;
    private double Frequency;
    private int Clicks;
    private int Total_Actions;
    private int Impressions;
    private int Social_Reach;
    private int Social_Impressions;
    private int Unique_Impressions;
    private int Unique_Social_Impressions;
    private double CPM;
    private double CPP;
    private double Spend;
    private double CPC;
    private double CTR;
    private Date Activity_Start_Date;
    private Date Activity_End_Date;
    private Date Stats_Date;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
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

    public void setPage_ID(long page_ID) {
        this.page_ID = page_ID;
    }

    public long getPage_ID() {
        return page_ID;
    }

    @Override
    public String toString() {
        return "CSVProductLevelAccountStats{" +
                "page_ID=" + page_ID +
                ", ID=" + ID +
                ", Name='" + Name + '\'' +
                ", Product_ID='" + Product_ID + '\'' +
                ", Reach=" + Reach +
                ", Frequency=" + Frequency +
                ", Clicks=" + Clicks +
                ", Total_Actions=" + Total_Actions +
                ", Impressions=" + Impressions +
                ", Social_Reach=" + Social_Reach +
                ", Social_Impressions=" + Social_Impressions +
                ", Unique_Impressions=" + Unique_Impressions +
                ", Unique_Social_Impressions=" + Unique_Social_Impressions +
                ", CPM=" + CPM +
                ", CPP=" + CPP +
                ", Spend=" + Spend +
                ", CPC=" + CPC +
                ", CTR=" + CTR +
                ", Activity_Start_Date=" + Activity_Start_Date +
                ", Activity_End_Date=" + Activity_End_Date +
                ", Stats_Date=" + Stats_Date +
                '}';
    }


}
