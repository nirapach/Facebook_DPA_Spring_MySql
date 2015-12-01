package com.gravity4.facebook.dpareports.responseparser.responsedata.ProductLevel;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevel.ProductLevelAccountsResultData;

import java.util.List;

public class ProductLevelAccountStatsJSONResponse {


    public List<ProductLevelAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;


}

