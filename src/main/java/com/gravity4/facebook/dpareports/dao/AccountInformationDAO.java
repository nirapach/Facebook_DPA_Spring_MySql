package com.gravity4.facebook.dpareports.dao;

/**
 * Created by niranjan on 6/18/15.
 */

import com.gravity4.facebook.dpareports.model.AccountInformationLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class AccountInformationDAO extends BaseDAO {

    private final RowMapper<AccountInformationLoader> rowMapper = new RowMapper<AccountInformationLoader>() {
        @Override
        public AccountInformationLoader mapRow(ResultSet resultSet, int i) throws SQLException {

            AccountInformationLoader accountInformationLoader = new AccountInformationLoader();

            accountInformationLoader.setApplication_Client_ID(resultSet.getLong("Application_Page_ID"));
            accountInformationLoader.setAd_Account_ID(resultSet.getLong("Business_Manager_Ad_Account_ID"));
            accountInformationLoader.setAccess_Token(resultSet.getString("Application_Long_Lived_Access_Token"));

            return accountInformationLoader;
        }
    };

    public List<AccountInformationLoader> getAccountInformation(){

        String query = "select Application_Page_ID,Business_Manager_Ad_Account_ID,Application_Long_Lived_Access_Token" +
                " from Account_Information_Master";

        return getJdbcTemplate().query(query, rowMapper);
    }
}
