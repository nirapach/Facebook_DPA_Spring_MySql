package dpa.responseparser.responsedata;

import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.ProductLevelCampaignResultData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by niranjan on 6/23/15.
 */
public class ProductLevelCampaignStatsJSONResponse {



    public List<ProductLevelCampaignResultData> data;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;
}
