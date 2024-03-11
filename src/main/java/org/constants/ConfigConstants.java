package org.constants;

public interface ConfigConstants {
    String API_URL_TOKEN_GET = "http://localhost:8001/api/token/get";
    String URL_DB = "jdbc:mysql://localhost:3001";
    String LOGIN = System.getenv("login");
    String PASSWORD = System.getenv("password");
    String DB_LOGIN = System.getenv("db_login");
    String DB_PASSWORD = System.getenv("db_password");
    String URL_WEB = String.format("http://%s:%s@localhost:8001/web", LOGIN, PASSWORD);
    String DATA_FORMAT = "yyyy-MM-dd hh:mm:ss";
    String NEXAGE_PROJECT_NAME = "Nexage";
    String PATH_TO_SCREENSHOT = "screen.png";
    String PATH_TO_LOGFILE = "output.txt";
    String PATH_TO_TEST_DATA_FILE = "src/main/resources/testData.json";
    String TABLE_NAME_WITH_TESTS = "union_reporting.test";
    String TABLE_NAME_WITH_PROJECTS = "union_reporting.project";
    String TABLE_NAME_WITH_ATTACHMENT = "union_reporting.attachment";
    String TABLE_NAME_WITH_LOGS = "union_reporting.log";
    int ID_0 = 0;
    int INDEX_1 = 1;
    int INDEX_2 = 2;
    int INDEX_3 = 3;
    int INDEX_4 = 4;
}