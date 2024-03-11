package org.utils;

import org.constants.ConfigConstants;
import org.models.AttachmentInfo;
import org.templates.SqlQueryTemplates;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudAttachmentUtils {
    public static void saveAttachment(AttachmentInfo attachmentInfo) {
        String sql = SqlQueryTemplates.insertRecordFieldsInTable(ConfigConstants.TABLE_NAME_WITH_ATTACHMENT,
                "(id,content,content_type,test_id)VALUES(?,?,?,?)");
        Connection connection = DBConnectionUtils.getConnection();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             FileInputStream fileInputStream = new FileInputStream(attachmentInfo.getContent())) {
            statement.setInt(ConfigConstants.INDEX_1, attachmentInfo.getId());
            statement.setBinaryStream(ConfigConstants.INDEX_2, fileInputStream);
            statement.setString(ConfigConstants.INDEX_3, attachmentInfo.getContentType());
            statement.setInt(ConfigConstants.INDEX_4, attachmentInfo.getTestId());

            statement.addBatch();
            statement.executeBatch();
        } catch (SQLException | IOException e) {
            LoggerUtils.logError("Ошибка при сохранении вложения в БД", new RuntimeException(e));
        }
    }
}