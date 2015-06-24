package dpa.controller;

/**
 * Created by niranjan on 6/18/15.
 */

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import dpa.utils.ConnectionDataSource;
import dpa.utils.DBUtils;
import dpa.model.AccountInformationLoader;
import dpa.api.AccountStats;

@SuppressWarnings("ALL")
public class AccountInformationDAO {

    Logger logger= LoggerFactory.getLogger(AccountInformationDAO.class);

    private Connection connection;
    private Statement statement;

    //default constructor
    public AccountInformationDAO(){}

    public List<AccountInformationLoader> getAccountInformation() throws SQLException, IOException, PropertyVetoException {

        ResultSet resultSet= null;
        AccountInformationLoader accountInformationLoader;
        List<AccountInformationLoader> accountinformationlist= new ArrayList<AccountInformationLoader>();
        String query = "select Application_Client_ID,Business_Manager_Ad_Account_ID,Application_Long_Lived_Access_Token" +
                " from Account_Information_Master";

        try{
            connection = ConnectionDataSource.getInstance().getConnection();
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            while (resultSet.next()){
                /*
                Retrieve each Client's details along with access token and store it in the object
                 */
                accountInformationLoader=new AccountInformationLoader();

                accountInformationLoader.setAd_Account_ID(resultSet.getLong("Business_Manager_Ad_Account_ID"));
                accountInformationLoader.setApplication_Client_ID(resultSet.getLong("Application_Client_ID"));
                accountInformationLoader.setAccess_Token(resultSet.getString("Application_Long_Lived_Access_Token"));

                /*
                add each client's information to the list
                 */

                accountinformationlist.add(accountInformationLoader);
            }
        }
        catch(Exception e){
            logger.info("AccountInformationDAO Exception");
            logger.info(String.valueOf(e));
        }
        finally {
                DBUtils.close(resultSet);
                DBUtils.close(statement);
                DBUtils.close(connection);

        }

        return accountinformationlist;
    }





}
