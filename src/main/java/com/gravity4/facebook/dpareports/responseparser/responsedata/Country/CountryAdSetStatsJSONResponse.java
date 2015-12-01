package com.gravity4.facebook.dpareports.responseparser.responsedata.Country;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Country.CountryAdSetResultData;


import java.util.List;

public class CountryAdSetStatsJSONResponse {


    public List<CountryAdSetResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;


    public JSONResponsePaging paging;

}
