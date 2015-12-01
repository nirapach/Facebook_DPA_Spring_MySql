package com.gravity4.facebook.dpareports.responseparser.responsedata.Placement;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Placement.PlacementAdSetResultData;

import java.util.List;

public class PlacementAdSetStatsJSONResponse {


    public List<PlacementAdSetResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
