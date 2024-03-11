package org.manager;

import org.constants.ConfigConstants;
import org.models.TestData;
import org.utils.ParserUtils;

public class TestDataManager {
    private static final TestData TEST_DATA = ParserUtils.parseJsonToObject(ConfigConstants.PATH_TO_TEST_DATA_FILE, TestData.class);

    public static TestData getTestData() {
        return TEST_DATA;
    }
}