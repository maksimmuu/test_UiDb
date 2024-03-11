package org.utils;

import aquality.selenium.browser.AqualityServices;
import org.constants.ConfigConstants;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class DriverUtils {
    public static void makeScreenShot() {
        File src = AqualityServices.getBrowser().getDriver().getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(src, new File(ConfigConstants.PATH_TO_SCREENSHOT));
        } catch (IOException e) {
            LoggerUtils.logError("Ошибка при создании скриншота", new RuntimeException(e));
        }
    }

    public static void refresh() {
        AqualityServices.getBrowser().refresh();
    }

    public static void addCookie(Cookie cookie) {
        AqualityServices.getBrowser().getDriver().manage().addCookie(cookie);
    }

    public static void switchToFrame(WebElement frame) {
        AqualityServices.getBrowser().getDriver().switchTo().frame(frame);
    }

    public static void switchToDefaultContent() {
        AqualityServices.getBrowser().getDriver().switchTo().defaultContent();
    }
}