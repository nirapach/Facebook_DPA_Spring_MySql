package com.gravity4.facebook.dpareports.responseparser.responsedata.Region;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Region.RegionAdGroupResultData;

import java.util.List;

public class RegionAdGroupStatsJSONResponse {


    public List<RegionAdGroupResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
