package com.gravity4.facebook.dpareports.responseparser.resultdata.Placement;

import com.google.gson.annotations.SerializedName;

/**
 * Created by niranjan on 6/23/15.
 */
public class PlacementCampaignResultData {

    @SerializedName("campaign_group_id")
    public String campaign_group_id;

    @SerializedName("placement")
    public String placement;

    @SerializedName("impression_device")
    public String impression_device;

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

    @SerializedName("campaign_group_name")
    public String campaign_group_name;


}
