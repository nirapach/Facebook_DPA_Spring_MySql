package com.gravity4.facebook.dpareports.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.OverAllAdGroupResultData;

import java.util.List;

public class OverAllAdGroupStatsJSONResponse {


    public List<OverAllAdGroupResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
