package dpa.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.AdSetResultData;

import java.util.List;

/**
 * Created by niranjan on 6/23/15.
 */
public class AdSetStatsJSONResponse {

    public List<AdSetResultData> resultdata;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;
}
