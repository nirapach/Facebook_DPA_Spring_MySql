package dpa.model;

import java.util.Date;

/**
 * Created by niranjan on 6/18/15.
 */
public class AccountStatsLoader {


    private long Client_ID;
    private long Account_ID;
    private String Product_ID;
    private int Age_Start_Range;
    private int Age_End_Range;
    private String country;
    private String Gender;
    private String Placement;
    private String Impression_Device;
    private long Reach;
    private double Frequency;
    private long Clicks;
    private long Total_Actions;
    private long Impressions;
    private long Social_Reach;
    private long Social_Impressions;
    private long Unique_Impressions;
    private long Unique_Social_Impressions;
    private double CPM;
    private double CPP;
    private double Spend;
    private double CPC;
    private double CTR;
    private double Cost_Per_Unique_Click;
    private Date Activity_Start_Date;
    private Date Activity_End_Date;
    private Date Stats_Date;

    //getter and setter methods


    public long getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(long client_ID) {
        Client_ID = client_ID;
    }

    public long getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(long account_ID) {
        Account_ID = account_ID;
    }

    public String getProduct_ID() {
        return Product_ID;
    }

    public void setProduct_ID(String product_ID) {
        Product_ID = product_ID;
    }

    public int getAge_Start_Range() {
        return Age_Start_Range;
    }

    public void setAge_Start_Range(int age_Start_Range) {
        Age_Start_Range = age_Start_Range;
    }

    public int getAge_End_Range() {
        return Age_End_Range;
    }

    public void setAge_End_Range(int age_End_Range) {
        Age_End_Range = age_End_Range;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getPlacement() {
        return Placement;
    }

    public void setPlacement(String placement) {
        Placement = placement;
    }

    public String getImpression_Device() {
        return Impression_Device;
    }

    public void setImpression_Device(String impression_Device) {
        Impression_Device = impression_Device;
    }

    public long getReach() {
        return Reach;
    }

    public void setReach(long reach) {
        Reach = reach;
    }

    public double getFrequency() {
        return Frequency;
    }

    public void setFrequency(double frequency) {
        Frequency = frequency;
    }

    public long getClicks() {
        return Clicks;
    }

    public void setClicks(long clicks) {
        Clicks = clicks;
    }

    public long getTotal_Actions() {
        return Total_Actions;
    }

    public void setTotal_Actions(long total_Actions) {
        Total_Actions = total_Actions;
    }

    public long getImpressions() {
        return Impressions;
    }

    public void setImpressions(long impressions) {
        Impressions = impressions;
    }

    public long getSocial_Reach() {
        return Social_Reach;
    }

    public void setSocial_Reach(long social_Reach) {
        Social_Reach = social_Reach;
    }

    public long getSocial_Impressions() {
        return Social_Impressions;
    }

    public void setSocial_Impressions(long social_Impressions) {
        Social_Impressions = social_Impressions;
    }

    public long getUnique_Impressions() {
        return Unique_Impressions;
    }

    public void setUnique_Impressions(long unique_Impressions) {
        Unique_Impressions = unique_Impressions;
    }

    public long getUnique_Social_Impressions() {
        return Unique_Social_Impressions;
    }

    public void setUnique_Social_Impressions(long unique_Social_Impressions) {
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
}
