package org.templates;

public class SqlQueryTemplates {
    public static String selectFromTableWithCondition(String tableName, String condition) {
        return String.format("select * from %s %s", tableName, condition);
    }

    public static String insertRecordFieldsInTable(String tableName, String fields) {
        return String.format("insert into %s %s", tableName, fields);
    }

    public static String getSqlCondition(String fieldInTheTable, String valueOfTheField) {
        return String.format("where %s = '%s'", fieldInTheTable, valueOfTheField);
    }

    public static String getSqlCondition(String fieldInTheTable, int valueOfTheField) {
        return String.format("where %s = %s", fieldInTheTable, valueOfTheField);
    }
}