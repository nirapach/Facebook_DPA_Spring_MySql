package dpa.responseparser.resultdata;

/**
 * Created by niranjan on 6/23/15.
 */
import java.util.List;
import java.util.Date;
import com.google.gson.annotations.SerializedName;

public class AccountsResultData {

    @SerializedName("account_id")
    public long account_id;

    @SerializedName("product_id")
    public long product_id;

    @SerializedName("date_start")
    public Date date_start;

    @SerializedName("date_stop")
    public Date date_stop;

    @SerializedName("impressions")
    public long impressions;

    @SerializedName("clicks")
    public long clicks;

    @SerializedName("spend")
    public double spend;

    /*@SerializedName("age")
    public String age;

    @SerializedName("gender")
    public String gender;


    @SerializedName("country")
    public String country;

    @SerializedName("placement")
    public String placement;

    @SerializedName("impression_device")
    public String impression_device;
*/
    @SerializedName("total_actions")
    public long total_actions;

    @SerializedName("reach")
    public long reach;

    @SerializedName("frequency")
    public double frequency;

    @SerializedName("social_reach")
    public long social_reach;

    @SerializedName("social_impressions")
    public long social_impressions;

    @SerializedName("unique_impressions")
    public long unique_impressions;

    @SerializedName("unique_social_impressions")
    public long unique_social_impressions;

    @SerializedName("cpm")
    public double cpm;

    @SerializedName("cpp")
    public double cpp;

    @SerializedName("ctr")
    public double ctr;

    @SerializedName("cpc")
    public double cpc;

    @SerializedName("cost_per_unique_click")
    public double cost_per_unique_click;


}
