package com.gravity4.facebook.dpareports.responseparser.responsedata.Placement;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Placement.PlacementAccountsResultData;

import java.util.List;

public class PlacementAccountStatsJSONResponse {

    public List<PlacementAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
