package dpa.responseparser.resultdata;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by niranjan on 6/23/15.
 */
public class AdGroupResultData {
    @SerializedName("adgroup_id")
    public int adgroup_id;

    @SerializedName("product_id")
    public int product_id;

    @SerializedName("date_start")
    public Date date_start;

    @SerializedName("date_stop")
    public Date date_stop;

    @SerializedName("impressions")
    public int impressions;

    @SerializedName("clicks")
    public int clicks;

    @SerializedName("spend")
    public double spend;

    @SerializedName("age")
    public String age;

    @SerializedName("gender")
    public String gender;

    @SerializedName("country")
    public String country;

    @SerializedName("placement")
    public String placement;

    @SerializedName("relevancy_score")
    public double relevancy_score;

    @SerializedName("impression_device")
    public String impression_device;

    @SerializedName("total_actions")
    public int total_actions;

    @SerializedName("reach")
    public int reach;

    @SerializedName("frequency")
    public double frequency;

    @SerializedName("social_reach")
    public int social_reach;

    @SerializedName("social_impressions")
    public int social_impressions;

    @SerializedName("unique_impressions")
    public int unique_impressions;

    @SerializedName("unique_social_impressions")
    public int unique_social_impressions;

    @SerializedName("cpm")
    public double cpm;


    @SerializedName("cpp")
    public double cpp;

    @SerializedName("ctr")
    public double ctr;

    @SerializedName("cpc")
    public double cpc;

    @SerializedName("cost_per_unique_click")
    public double cost_per_unique_click;


}
