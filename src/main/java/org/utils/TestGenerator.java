package org.utils;

import org.constants.*;
import org.manager.TestDataManager;
import org.models.TestInfo;

import java.sql.Timestamp;

public class TestGenerator {

    public static TestInfo generate() {
        TestInfo testInfo = new TestInfo();
        testInfo.setId(ConfigConstants.ID_0);
        testInfo.setName(TestDataManager.getTestData().getTestName());
        testInfo.setSessionId(TestDataManager.getTestData().getTestSessionId());
        testInfo.setEnv(TestDataManager.getTestData().getTestEnv());
        testInfo.setBrowser(TestDataManager.getTestData().getTestBrowser());
        testInfo.setAuthorId(TestDataManager.getTestData().getTestAuthorId());
        testInfo.setStatusId(TestDataManager.getTestData().getTestStatus());
        testInfo.setMethodName(TestDataManager.getTestData().getTestMethod());
        testInfo.setStartTime(new Timestamp(TestDataManager.getTestData().getTestTimeStamp()));
        testInfo.setEndTime(new Timestamp(TestDataManager.getTestData().getTestTimeStamp()));
        return testInfo;
    }
}