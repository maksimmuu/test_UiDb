package org.utils;

import org.constants.ConfigConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtils {
    private static Connection connection = null;

    private static void connect() {
        try {
            connection = DriverManager.getConnection(ConfigConstants.URL_DB, ConfigConstants.DB_LOGIN, ConfigConstants.DB_PASSWORD);
        } catch (SQLException e) {
            LoggerUtils.logError("Ошибка при получении connection", new RuntimeException(e));
        }
    }

    public static Connection getConnection() {
        if (connection == null) {
            connect();
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                LoggerUtils.logError("Ошибка при закрытии connection", new RuntimeException(e));
            }
        }
    }
}