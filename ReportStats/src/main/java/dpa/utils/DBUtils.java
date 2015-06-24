package dpa.utils;


/**
 * Created by niranjan on 6/18/15.
 */
import java.sql.*;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class DBUtils {


    public static void close(Connection connection)
    {
        Logger logger= LoggerFactory.getLogger(DBUtils.class);
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(String.valueOf(e));
            }
        }
    }

    public static void close(Statement statement) {
        Logger logger= LoggerFactory.getLogger(DBUtils.class);
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(String.valueOf(e));
            }
        }
    }

    public static void close(PreparedStatement statement) {
        Logger logger= LoggerFactory.getLogger(DBUtils.class);
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.error(String.valueOf(e));
            }
        }
    }

    public static void close(ResultSet resultSet) {
        Logger logger= LoggerFactory.getLogger(DBUtils.class);
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.error(String.valueOf(e));
            }
        }
    }
}

