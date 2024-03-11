package org.pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import org.openqa.selenium.By;

import java.util.List;

public class ProjectTestsPage extends BasePage {
    private final By allTests = By.xpath("//table[contains(@class,'table')]//tr");
    private final By testName = By.xpath("//td/a");
    private final By testStartTime = By.xpath("//td[4]");
    private final IButton home = getElementFactory().getButton(By.xpath("//a[contains(text(),'Home')]"), "Home button");

    public ProjectTestsPage() {
        super(By.xpath("//li[contains(text(),'Nexage')]"), "Nexage tests page");
    }

    public List<ILabel> getListAllTests() {
        return getElementFactory().findElements(allTests, ElementType.LABEL);
    }

    public ILabel getTestName(ILabel testElement) {
        return testElement.findChildElement(testName, ElementType.LABEL);
    }

    public String getTestStartTime(ILabel testElement) {
        return testElement.findChildElement(testStartTime, ElementType.LABEL).getText();
    }

    public void clickHomeButton() {
        clickElement(home);
    }
}