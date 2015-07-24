package com.gravity4.facebook.dpareports.responseparser.resultdata;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;

public class OverAllAccountsResultData {

    @SerializedName("account_id")
    public String account_id;

    @SerializedName("age")
    public String age;

    @SerializedName("gender")
    public String gender;

    @SerializedName("date_start")
    public String date_start;

    @SerializedName("date_stop")
    public String date_stop;

    @SerializedName("spend")
    public double spend;

    @SerializedName("total_actions")
    public int total_actions;

    @SerializedName("reach")
    public int reach;

    @SerializedName("clicks")
    public int clicks;

    @SerializedName("impressions")
    public int impressions;

    @SerializedName("frequency")
    public double frequency;

    @SerializedName("social_reach")
    public int social_reach;

    @SerializedName("social_impressions")
    public int social_impressions;

    @SerializedName("cpm")
    public double cpm;

    @SerializedName("unique_impressions")
    public int unique_impressions;

    @SerializedName("unique_social_impressions")
    public int unique_social_impressions;

    @SerializedName("cpp")
    public double cpp;

    @SerializedName("ctr")
    public double ctr;

    @SerializedName("cpc")
    public double cpc;

    @SerializedName("cost_per_unique_click")
    public double cost_per_unique_click;

    @SerializedName("account_name")
    public String account_name;

}
