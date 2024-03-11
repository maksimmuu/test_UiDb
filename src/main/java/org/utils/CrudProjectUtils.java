package org.utils;

import org.constants.ConfigConstants;
import org.models.ProjectInfo;
import org.templates.SqlQueryTemplates;

import java.sql.ResultSet;
import java.util.List;

public class CrudProjectUtils {
    public static List<ProjectInfo> selectProjectsWithCondition(String condition) {
        ResultSet resultSet = DBUtils.executeQuery(SqlQueryTemplates.selectFromTableWithCondition(ConfigConstants.TABLE_NAME_WITH_PROJECTS, condition));
        return ParserUtils.parseResultSetToListOfObjects(resultSet, ProjectInfo.class);
    }
}