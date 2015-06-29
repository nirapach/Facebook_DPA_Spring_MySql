package dpa.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.OverAllAccountsResultData;
import dpa.responseparser.resultdata.OverAllCampaignResultData;

import java.util.List;

public class OverAllCampaignStatsJSONResponse {

    public List<OverAllCampaignResultData> resultdata;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;

}
