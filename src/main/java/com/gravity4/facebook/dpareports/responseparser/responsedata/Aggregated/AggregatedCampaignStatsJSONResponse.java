package com.gravity4.facebook.dpareports.responseparser.responsedata.Aggregated;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Aggregated.AggregatedCampaignResultData;

import java.util.List;

public class AggregatedCampaignStatsJSONResponse {


    public List<AggregatedCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
