package dpa.utils;

/**
 * Created by niranjan on 6/24/15.
 */

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class ConnectionDataSourceTest {

    /*
    to test whether the connection pool is established or not
     */
    public Connection connection;
    public Statement statement;

    @Test
    public void shouldGetConnection() throws SQLException, IOException, PropertyVetoException{

        Logger logger= LoggerFactory.getLogger(ConnectionDataSourceTest.class);
        ResultSet resultSet= null;
        List<Long> data= new ArrayList<Long>();

        String query = "select Application_Client_ID from Account_Information_Master";

        try{
            connection = ConnectionDataSource.getInstance().getConnection();
            statement =connection.createStatement();
            resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                data.add(resultSet.getLong("Application_Client_ID"));
            }
            Iterator<Long> DataIterator = data.iterator();
            while(DataIterator.hasNext()){
                logger.info(String.valueOf(DataIterator.next()));
            }
        }catch(Exception e){
            logger.info("CONNECTION DATASOURCE TEST Exception");
            logger.info(String.valueOf(e));
        }
        finally {
            DBUtils.close(resultSet);
            DBUtils.close(statement);
            DBUtils.close(connection);

        }


    }



}