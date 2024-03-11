package org.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.constants.ConfigConstants;

import java.io.*;

public class LoggerUtils {
    private static final Logger log = LogManager.getLogger();
    private static PrintStream out;

    public static void logError(String errorInfo, Exception exception) {
        log.error(errorInfo, exception);
    }

    public static void startWriteLogsToFile() {
        try {
            out = new PrintStream(ConfigConstants.PATH_TO_LOGFILE);
        } catch (FileNotFoundException e) {
            logError("Ошибка начала записи логов в файл", new RuntimeException(e));
        }
        System.setOut(out);
    }

    public static void stopWriteLogsToFile() {
        out.close();
        out = null;
    }
}