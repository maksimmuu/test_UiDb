package org.pages;

import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

public class TestInfoPage extends BasePage {
    private final ILabel projectName = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'Project')]]/p"), "Project name");
    private final ILabel testName = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'Test name')]]/p"), "Test name");
    private final ILabel testMethodName = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'method')]]/p"), "Test method name");
    private final ILabel testStatus = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'Status')]]/p/span"), "Test status");
    private final ILabel environment = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'Environment')]]/p"), "Environment");
    private final ILabel browser = getElementFactory().getLabel(By.xpath("//div[.//h4[contains(text(),'Browser')]]/p"), "Browser");
    private final ILabel startTime = getElementFactory().getLabel(By.xpath("//p[contains(text(),'Start')]"), "Start time");
    private final ILabel endTime = getElementFactory().getLabel(By.xpath("//p[contains(text(),'End')]"), "End time");
    private final ILabel screenShot = getElementFactory().getLabel(By.xpath("//img[contains(@class,'thumbnail')]"), "ScreenShot");

    public TestInfoPage() {
        super(By.xpath("//div[contains(text(),'Common')]"), "Test info page");
    }

    public String getProjectName() {
        return getTextElement(projectName);
    }

    public String getTestName() {
        return getTextElement(testName);
    }

    public String getTestMethodName() {
        return getTextElement(testMethodName);
    }

    public String getTestStatus() {
        return getTextElement(testStatus);
    }

    public String getEnvironment() {
        return getTextElement(environment);
    }

    public String getBrowser() {
        return getTextElement(browser);
    }

    public String getTestStartTime() {
        return getTextElement(startTime);
    }

    public String getTestEndTime() {
        return getTextElement(endTime);
    }

    public ILabel getTestScreenShot() {
        return screenShot;
    }
}