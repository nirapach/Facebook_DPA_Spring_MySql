package com.gravity4.facebook.dpareports.model.APImodels.Actions;

import java.util.Date;

/**
 * Created by niranjan on 6/18/15.
 */
public class ActionTypeStatsLoader {


    private long Client_ID;
    private long ID;
    private String Type;
    private int Value;
    private Date Activity_Start_Date;
    private Date Activity_End_Date;
    private Date Stats_Date;
    private String Name;

    //getter and setter methods


    public long getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(long client_ID) {
        Client_ID = client_ID;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int value) {
        Value = value;
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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public String toString() {
        return "ActionTypeStatsLoader{" +
                "Client_ID=" + Client_ID +
                ", ID=" + ID +
                ", Type='" + Type + '\'' +
                ", Value=" + Value +
                ", Activity_Start_Date=" + Activity_Start_Date +
                ", Activity_End_Date=" + Activity_End_Date +
                ", Stats_Date=" + Stats_Date +
                ", Name='" + Name + '\'' +
                '}';
    }
}
