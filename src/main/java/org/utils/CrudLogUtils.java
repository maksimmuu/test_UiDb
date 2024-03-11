package org.utils;

import org.constants.ConfigConstants;
import org.models.LogInfo;
import org.templates.SqlQueryTemplates;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudLogUtils {
    public static void saveLog(LogInfo logInfo) {
        String sql = SqlQueryTemplates.insertRecordFieldsInTable(ConfigConstants.TABLE_NAME_WITH_LOGS,
                "(id,content,is_exception,test_id)VALUES(?,?,?,?)");
        Connection connection = DBConnectionUtils.getConnection();

        try (BufferedReader reader = new BufferedReader(new FileReader(logInfo.getContent()));
             PreparedStatement statement = connection.prepareStatement(sql)) {
            StringBuilder text = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                text.append(line);
            }

            statement.setInt(ConfigConstants.INDEX_1, logInfo.getId());
            statement.setString(ConfigConstants.INDEX_2, text.toString());
            statement.setInt(ConfigConstants.INDEX_3, logInfo.getIsException());
            statement.setInt(ConfigConstants.INDEX_4, logInfo.getTestId());

            statement.executeUpdate();
        } catch (Exception e) {
            LoggerUtils.logError("Ошибка при сохранении лога в БД", new RuntimeException(e));
        }
    }
}