package com.gravity4.facebook.dpareports.responseparser.responsedata.actions;

/**
 * Created by niranjan on 6/23/15.
 */

import com.google.gson.annotations.SerializedName;

public class ActionTypeResponsePaging {

    public Cursors cursors;

    public class Cursors{

        @SerializedName("before")
        public String before;

        @SerializedName("after")
        public String after;
    }

}
