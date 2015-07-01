package dpa.model;

import java.math.BigInteger;

/**
 * Created by niranjan on 6/18/15.
 */
public class AccountInformationLoader {

    private long Ad_Account_ID;
    private long Application_Client_ID;
    private String Access_Token;

    public long getAd_Account_ID() {
        return Ad_Account_ID;
    }

    public void setAd_Account_ID(long ad_Account_ID) {
        Ad_Account_ID = ad_Account_ID;
    }

    public long getApplication_Client_ID() {
        return Application_Client_ID;
    }

    public void setApplication_Client_ID(long application_Client_ID) {
        Application_Client_ID = application_Client_ID;
    }

    public String getAccess_Token() {
        return Access_Token;
    }

    public void setAccess_Token(String access_Token) {
        Access_Token = access_Token;
    }
}
