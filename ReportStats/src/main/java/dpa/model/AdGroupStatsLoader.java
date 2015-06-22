package dpa.model;

/**
 * Created by niranjan on 6/18/15.
 */
public class AdGroupStatsLoader {

    private int Account_ID;
    private String Account_Name;
    private int AdGroup_ID;
    private String AdGroup_Name;
    private int Age_Start_Range;
    private int Age_End_Range;
    private int Gender;
    private String Placement;
    private String Action_Device;
    private String Impression_Device;
    private int Reach;
    private float Frequency;
    private int impressions;
    private int Social_Reach;
    private int Social_Impressions;
    private int Unique_Impressions;
    private int Unique_Social_Impressions;
    private float CPM;
    private float CPP;
    private float Spend;
    private float CPC;
    private float CTR;
    private float Cost_Per_Unique_Click;
    private float Stats_Date;

    public int getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(int account_ID) {
        Account_ID = account_ID;
    }

    public String getAccount_Name() {
        return Account_Name;
    }

    public void setAccount_Name(String account_Name) {
        Account_Name = account_Name;
    }

    public int getAdGroup_ID() {
        return AdGroup_ID;
    }

    public void setAdGroup_ID(int adGroup_ID) {
        AdGroup_ID = adGroup_ID;
    }

    public String getAdGroup_Name() {
        return AdGroup_Name;
    }

    public void setAdGroup_Name(String adGroup_Name) {
        AdGroup_Name = adGroup_Name;
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

    public int getGender() {
        return Gender;
    }

    public void setGender(int gender) {
        Gender = gender;
    }

    public String getPlacement() {
        return Placement;
    }

    public void setPlacement(String placement) {
        Placement = placement;
    }

    public String getAction_Device() {
        return Action_Device;
    }

    public void setAction_Device(String action_Device) {
        Action_Device = action_Device;
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

    public float getFrequency() {
        return Frequency;
    }

    public void setFrequency(float frequency) {
        Frequency = frequency;
    }

    public int getImpressions() {
        return impressions;
    }

    public void setImpressions(int impressions) {
        this.impressions = impressions;
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

    public float getCPM() {
        return CPM;
    }

    public void setCPM(float CPM) {
        this.CPM = CPM;
    }

    public float getCPP() {
        return CPP;
    }

    public void setCPP(float CPP) {
        this.CPP = CPP;
    }

    public float getSpend() {
        return Spend;
    }

    public void setSpend(float spend) {
        Spend = spend;
    }

    public float getCPC() {
        return CPC;
    }

    public void setCPC(float CPC) {
        this.CPC = CPC;
    }

    public float getCTR() {
        return CTR;
    }

    public void setCTR(float CTR) {
        this.CTR = CTR;
    }

    public float getCost_Per_Unique_Click() {
        return Cost_Per_Unique_Click;
    }

    public void setCost_Per_Unique_Click(float cost_Per_Unique_Click) {
        Cost_Per_Unique_Click = cost_Per_Unique_Click;
    }

    public float getStats_Date() {
        return Stats_Date;
    }

    public void setStats_Date(float stats_Date) {
        Stats_Date = stats_Date;
    }
}
