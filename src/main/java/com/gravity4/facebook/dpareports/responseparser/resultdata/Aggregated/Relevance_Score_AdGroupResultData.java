package com.gravity4.facebook.dpareports.responseparser.resultdata.Aggregated;

import com.google.gson.annotations.SerializedName;

/**
 * Created by niranjan on 6/23/15.
 */
public class Relevance_Score_AdGroupResultData {
    @SerializedName("score")
    public int score;

    @SerializedName("positive_feedback")
    public String positive_feedback;

    @SerializedName("negative_feedback")
    public String negative_feedback;

    @SerializedName("status")
    public String status;

}
