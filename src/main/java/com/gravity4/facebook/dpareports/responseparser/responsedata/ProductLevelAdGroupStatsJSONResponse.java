package com.gravity4.facebook.dpareports.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevelAdGroupResultData;

import java.util.List;

/**
 * Created by niranjan on 6/23/15.
 */
public class ProductLevelAdGroupStatsJSONResponse {


    public List<ProductLevelAdGroupResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
