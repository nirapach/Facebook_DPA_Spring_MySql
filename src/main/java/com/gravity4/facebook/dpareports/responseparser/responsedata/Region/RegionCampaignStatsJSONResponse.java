package com.gravity4.facebook.dpareports.responseparser.responsedata.Region;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Region.RegionCampaignResultData;

import java.util.List;

public class RegionCampaignStatsJSONResponse {


    public List<RegionCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
