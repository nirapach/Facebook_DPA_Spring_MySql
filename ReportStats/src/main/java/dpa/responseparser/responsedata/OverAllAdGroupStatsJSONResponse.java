package dpa.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.OverAllAccountsResultData;
import dpa.responseparser.resultdata.OverAllAdGroupResultData;
import dpa.responseparser.resultdata.OverAllAdSetResultData;

import java.util.ArrayList;
import java.util.List;

public class OverAllAdGroupStatsJSONResponse {


    public List<OverAllAdGroupResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
