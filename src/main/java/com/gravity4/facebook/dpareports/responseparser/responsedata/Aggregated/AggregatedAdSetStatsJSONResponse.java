package com.gravity4.facebook.dpareports.responseparser.responsedata.Aggregated;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Aggregated.AggregatedAdSetResultData;

import java.util.List;

public class AggregatedAdSetStatsJSONResponse {


    public List<AggregatedAdSetResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
