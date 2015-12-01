package com.gravity4.facebook.dpareports.responseparser.responsedata.Placement;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Placement.PlacementCampaignResultData;

import java.util.List;

public class PlacementCampaignStatsJSONResponse {


    public List<PlacementCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
