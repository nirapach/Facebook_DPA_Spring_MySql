package com.gravity4.facebook.dpareports.responseparser.resultdata.Actions;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdSetActionTypesResultData {

    @SerializedName("campaign_id")
    public String campaign_id;

    @SerializedName("campaign_name")
    public String campaign_name;

    public List<ActionTypesValueResultData> actions;

    @SerializedName("date_start")
    public String date_start;

    @SerializedName("date_stop")
    public String date_stop;

}
