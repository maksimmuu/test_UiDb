package org.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
    public static ResultSet executeQuery(String sql) {
        try {
            PreparedStatement preparedStatement = DBConnectionUtils.getConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            LoggerUtils.logError("Ошибка выполнения sql запроса", new RuntimeException(e));
        }
        return null;
    }

    public static void executeUpdate(String sql) {
        try {
            PreparedStatement preparedStatement = DBConnectionUtils.getConnection().prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LoggerUtils.logError("Ошибка выполнения sql запроса", new RuntimeException(e));
        }
    }
}