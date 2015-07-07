package com.gravity4.facebook.dpareports.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevelCampaignResultData;

import java.util.List;

/**
 * Created by niranjan on 6/23/15.
 */
public class ProductLevelCampaignStatsJSONResponse {


    public List<ProductLevelCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;
}
