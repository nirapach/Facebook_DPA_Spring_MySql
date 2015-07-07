package com.gravity4.facebook.dpareports.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.OverAllAdSetResultData;

import java.util.List;

public class OverAllAdSetStatsJSONResponse {


    public List<OverAllAdSetResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
