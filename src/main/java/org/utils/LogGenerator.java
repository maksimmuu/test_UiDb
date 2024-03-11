package org.utils;

import org.constants.ConfigConstants;
import org.models.LogInfo;

import java.io.File;

public class LogGenerator {
    public static LogInfo generate() {
        LogInfo logInfo = new LogInfo();
        logInfo.setId(ConfigConstants.ID_0);
        logInfo.setContent(new File(ConfigConstants.PATH_TO_LOGFILE));
        logInfo.setIsException(ConfigConstants.ID_0);
        return logInfo;
    }
}