package com.gravity4.facebook.dpareports.responseparser.resultdata.Actions;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AdGroupActionTypesResultData {

    @SerializedName("adgroup_id")
    public String adgroup_id;

    @SerializedName("adgroup_name")
    public String adgroup_name;

    public List<ActionTypesValueResultData> actions;

    @SerializedName("date_start")
    public String date_start;

    @SerializedName("date_stop")
    public String date_stop;

}
