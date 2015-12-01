package com.gravity4.facebook.dpareports.responseparser.responsedata.Country;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.Country.CountryAccountsResultData;


import java.util.List;

public class CountryAccountStatsJSONResponse {

    public List<CountryAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
