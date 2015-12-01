package com.gravity4.facebook.dpareports.responseparser.responsedata.AgeandGender;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.AgeandGender.OverAllCampaignResultData;

import java.util.List;

public class OverAllCampaignStatsJSONResponse {


    public List<OverAllCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
