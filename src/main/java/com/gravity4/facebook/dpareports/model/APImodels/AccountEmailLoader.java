package com.gravity4.facebook.dpareports.model.APImodels;

/**
 * Created by niranjan on 6/18/15.
 */
public class AccountEmailLoader {

    private String Email_ID;
    private long Client_ID;
    private String Client_Name;

    public long getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(long client_ID) {
        Client_ID = client_ID;
    }

    public String getClient_Name() {
        return Client_Name;
    }

    public void setClient_Name(String client_Name) {
        Client_Name = client_Name;
    }

    public String getEmail_ID() {
        return Email_ID;
    }

    public void setEmail_ID(String email_ID) {
        Email_ID = email_ID;
    }

    @Override
    public String toString() {
        return "AccountEmailLoader{" +
                "Email_ID='" + Email_ID + '\'' +
                ", Client_ID=" + Client_ID +
                ", Client_Name='" + Client_Name + '\'' +
                '}';
    }
}


