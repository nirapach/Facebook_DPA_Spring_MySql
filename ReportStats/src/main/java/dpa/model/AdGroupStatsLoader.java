package dpa.model;

import java.util.Date;

/**
 * Created by niranjan on 6/18/15.
 */
public class AdGroupStatsLoader {

    private int Client_ID;
    private int AdGroup_ID;
    private String Product_ID;
    private int Age_Start_Range;
    private int Age_End_Range;
    private String country;
    private String Gender;
    private String Placement;
    private String Impression_Device;
    private int Reach;
    private double Frequency;
    private int Clicks;
    private int Total_Actions;
    private int Impressions;
    private int Social_Reach;
    private double Relevany_Score;
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

    /*getters and setters
     */

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public int getAdGroup_ID() {
        return AdGroup_ID;
    }

    public void setAdGroup_ID(int adGroup_ID) {
        AdGroup_ID = adGroup_ID;
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

    public double getRelevany_Score() {
        return Relevany_Score;
    }

    public void setRelevany_Score(double relevany_Score) {
        Relevany_Score = relevany_Score;
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
}
