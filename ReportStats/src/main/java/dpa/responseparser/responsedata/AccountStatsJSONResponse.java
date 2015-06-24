package dpa.responseparser.responsedata;

/**
 * Created by niranjan on 6/23/15.
 */
import java.util.List;
import com.google.gson.annotations.SerializedName;
import dpa.responseparser.resultdata.AccountsResultData;

public class AccountStatsJSONResponse {

    public List<AccountsResultData> resultdata;

    @SerializedName("limit")
    public int limit;

    @SerializedName("offset")
    public int offset;

    public JSONResponsePaging paging;



}

