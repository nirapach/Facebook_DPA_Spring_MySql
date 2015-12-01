package com.gravity4.facebook.dpareports.model.CSVModels.Actions;

import java.sql.Date;

/**
 * Created by niranjan on 7/23/15.
 */
public class CSVActionsAdSetStats {


    private long Page_ID;
    private long AdSet_ID;
    private String AdSet_Name;
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

    public long getAdSet_ID() {
        return AdSet_ID;
    }

    public void setAdSet_ID(long adSet_ID) {
        AdSet_ID = adSet_ID;
    }

    public String getAdSet_Name() {
        return AdSet_Name;
    }

    public void setAdSet_Name(String adSet_Name) {
        AdSet_Name = adSet_Name;
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
        return "CSVActionsAdSetStats{" +
                "Page_ID=" + Page_ID +
                ", AdSet_ID=" + AdSet_ID +
                ", AdSet_Name='" + AdSet_Name + '\'' +
                ", Action_Type='" + Action_Type + '\'' +
                ", Action_Value=" + Action_Value +
                ", Activity_Start_Date=" + Activity_Start_Date +
                ", Activity_End_Date=" + Activity_End_Date +
                ", Stats_Date=" + Stats_Date +
                '}';
    }
}
