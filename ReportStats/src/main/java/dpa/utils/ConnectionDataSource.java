package dpa.utils;

/**
 * Created by niranjan on 6/18/15.
 */

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;


public class ConnectionDataSource  {

    private static ConnectionDataSource connectiondatasource;
    private BasicDataSource basicdatasource;

    private ConnectionDataSource() throws IOException, SQLException, PropertyVetoException {

        Logger logger= LoggerFactory.getLogger(ConnectionDataSource.class);
        Properties config = new Properties();
        String propertyFileName = "config.properties";

        InputStream inputstream = getClass().getClassLoader().getResourceAsStream(propertyFileName);

        if (inputstream != null) {
            config.load(inputstream);
        } else {
            logger.info(String.valueOf("Configuration Properties File'" + propertyFileName + "' not found in classpath"));
            throw new FileNotFoundException("Configuration Properties File'" + propertyFileName + "' not found in classpath");
        }
        String host;
        String port;
        String user;
        String password;
        String schema;
        try {
            host = config.getProperty("host");
            port = config.getProperty("port");
            user = config.getProperty("user");
            password = config.getProperty("password");
            schema = config.getProperty("schema");
        } catch (Exception e) {
            logger.info(String.valueOf(e));
            throw new IOException("Problem with configuration properties file", e);

        }

        //creating basicdatasource instance
        basicdatasource = new BasicDataSource();

        basicdatasource.setDriverClassName("com.mysql.jdbc.Driver");
        basicdatasource.setUsername(user);
        basicdatasource.setPassword(password);
        basicdatasource.setUrl("jdbc:mysql://"+host+":"+port+"/"+schema);
    }

    public static ConnectionDataSource getInstance() throws IOException, SQLException, PropertyVetoException{

        if (connectiondatasource==null){
            connectiondatasource=new ConnectionDataSource();
            return connectiondatasource;
        }
        else{
            return connectiondatasource;
        }

    }

    public Connection getConnection() throws SQLException {
        return this.basicdatasource.getConnection();
    }


}
