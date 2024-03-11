package org.tests;

import aquality.selenium.browser.AqualityServices;
import org.constants.ConfigConstants;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.utils.DBConnectionUtils;
import org.utils.LoggerUtils;

public abstract class BaseTest {
    @BeforeTest
    public void startTest() {
        DBConnectionUtils.getConnection();
    }

    @BeforeMethod
    public void setUp() {
        AqualityServices.getBrowser().maximize();
        AqualityServices.getBrowser().goTo(ConfigConstants.URL_WEB);
        LoggerUtils.startWriteLogsToFile();
    }

    @AfterMethod
    public void tearDown() {
        LoggerUtils.stopWriteLogsToFile();
        AqualityServices.getBrowser().quit();
    }

    @AfterTest
    public void finishTest() {
        DBConnectionUtils.closeConnection();
    }
}