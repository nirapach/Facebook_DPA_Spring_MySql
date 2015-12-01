package com.gravity4.facebook.dpareports.model.CSVModels.Actions;

import java.sql.Date;

/**
 * Created by niranjan on 7/23/15.
 */
public class CSVActionsAccountStats {


    private long Page_ID;
    private long Account_ID;
    private String Account_Name;
    private String Action_Type;
    private int Action_Value;
    private Date Activity_Start_Date;
    private Date Activity_End_Date;
    private Date Stats_Date;


    /*
    getters and setters
     */

    public long getPage_ID() {
        return Page_ID;
    }

    public void setPage_ID(long page_ID) {
        Page_ID = page_ID;
    }

    public long getAccount_ID() {
        return Account_ID;
    }

    public void setAccount_ID(long account_ID) {
        Account_ID = account_ID;
    }

    public String getAccount_Name() {
        return Account_Name;
    }

    public void setAccount_Name(String account_Name) {
        Account_Name = account_Name;
    }

    public String getAction_Type() {
        return Action_Type;
    }

    public void setAction_Type(String action_Type) {
        Action_Type = action_Type;
    }

    public int getAction_Value() {
        return Action_Value;
    }

    public void setAction_Value(int action_Value) {
        Action_Value = action_Value;
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

    @Override
    public String toString() {
        return "CSVActionsAccountStats{" +
                "Page_ID=" + Page_ID +
                ", Account_ID=" + Account_ID +
                ", Account_Name='" + Account_Name + '\'' +
                ", Action_Type='" + Action_Type + '\'' +
                ", Action_Value=" + Action_Value +
                ", Activity_Start_Date=" + Activity_Start_Date +
                ", Activity_End_Date=" + Activity_End_Date +
                ", Stats_Date=" + Stats_Date +
                '}';
    }
}
