package org.utils;

import org.constants.ConfigConstants;
import org.models.TestInfo;
import org.templates.SqlQueryTemplates;

import java.sql.ResultSet;
import java.util.List;

public class CrudTestUtils {
    public static List<TestInfo> selectTestsWithCondition(String condition) {
        ResultSet resultSet = DBUtils.executeQuery(SqlQueryTemplates.selectFromTableWithCondition(ConfigConstants.TABLE_NAME_WITH_TESTS, condition));
        return ParserUtils.parseResultSetToListOfObjects(resultSet, TestInfo.class);
    }

    public static void saveTest(TestInfo testInfo) {
        String sqlQuery = SqlQueryTemplates.insertRecordFieldsInTable(ConfigConstants.TABLE_NAME_WITH_TESTS,
                String.format("(id, name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id) " +
                                "values('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",
                        testInfo.getId(), testInfo.getName(), testInfo.getStatusId(), testInfo.getMethodName(),
                        testInfo.getProjectId(), testInfo.getSessionId(), testInfo.getStartTime(), testInfo.getEndTime(),
                        testInfo.getEnv(), testInfo.getBrowser(), testInfo.getAuthorId()));
        DBUtils.executeUpdate(sqlQuery);
    }
}