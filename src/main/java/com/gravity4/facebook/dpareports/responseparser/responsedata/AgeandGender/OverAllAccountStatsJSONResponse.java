package com.gravity4.facebook.dpareports.responseparser.responsedata.AgeandGender;

import com.google.gson.annotations.SerializedName;
import com.gravity4.facebook.dpareports.responseparser.responsedata.JSONResponsePaging;
import com.gravity4.facebook.dpareports.responseparser.resultdata.AgeandGender.OverAllAccountsResultData;

import java.util.List;

public class OverAllAccountStatsJSONResponse {

    public List<OverAllAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
