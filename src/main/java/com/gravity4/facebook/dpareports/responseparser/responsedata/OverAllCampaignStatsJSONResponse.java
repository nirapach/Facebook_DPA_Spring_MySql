package com.gravity4.facebook.dpareports.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.OverAllCampaignResultData;

import java.util.List;

public class OverAllCampaignStatsJSONResponse {


    public List<OverAllCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
