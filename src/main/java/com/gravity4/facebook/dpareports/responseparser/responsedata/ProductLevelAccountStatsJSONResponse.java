package com.gravity4.facebook.dpareports.responseparser.responsedata;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.resultdata.ProductLevelAccountsResultData;

import java.util.List;

public class ProductLevelAccountStatsJSONResponse {


    public List<ProductLevelAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;


}

