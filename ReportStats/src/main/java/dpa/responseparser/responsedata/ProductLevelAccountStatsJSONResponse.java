package dpa.responseparser.responsedata;

/**
 * Created by niranjan on 6/23/15.
 */
import java.util.List;
import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.ProductLevelAccountsResultData;

public class ProductLevelAccountStatsJSONResponse {

    public List<ProductLevelAccountsResultData> resultdata;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;



}

