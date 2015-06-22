package dpa.model;

/**
 * Created by niranjan on 6/18/15.
 */
public class AccountInformationLoader {

    private int Ad_Account_ID;
    private int Application_Client_ID;
    private String Access_Token;

    public int getAd_Account_ID() {
        return Ad_Account_ID;
    }

    public void setAd_Account_ID(int ad_Account_ID) {
        Ad_Account_ID = ad_Account_ID;
    }

    public int getApplication_Client_ID() {
        return Application_Client_ID;
    }

    public void setApplication_Client_ID(int application_Client_ID) {
        Application_Client_ID = application_Client_ID;
    }

    public String getAccess_Token() {
        return Access_Token;
    }

    public void setAccess_Token(String access_Token) {
        Access_Token = access_Token;
    }
}
