package dpa.responseparser.responsedata;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.OverAllAccountsResultData;

public class OverAllAccountStatsJSONResponse {

    public List<OverAllAccountsResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
