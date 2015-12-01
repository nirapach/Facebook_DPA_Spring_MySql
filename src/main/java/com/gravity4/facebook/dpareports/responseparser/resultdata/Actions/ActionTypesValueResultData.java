package com.gravity4.facebook.dpareports.responseparser.resultdata.Actions;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;

public class ActionTypesValueResultData {

    @SerializedName("action_type")
    public String action_type;

    @SerializedName("value")
    public int value;

}
