package org.pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import org.constants.AttributeConstants;
import org.constants.RegExPatterns;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.utils.RegExUtils;

import java.util.List;

public class ProjectsPage extends BasePage {
    private final ILabel pageFooter = getElementFactory().getLabel(By.xpath("//p[contains(@class,'footer-text')]"), "Page footer");
    private final By addProjectFrame = By.xpath("//iframe[contains(@id,'addProjectFrame')]");
    private final ILabel nexageProject = getElementFactory().getLabel(By.xpath("//a[contains(text(),'Nexage')]"), "Nexage tests button");
    private final IButton addProject = getElementFactory().getButton(By.xpath("//button[contains(text(),'Add')]"), "Add button");
    private final ITextBox inputProjectName = getElementFactory().getTextBox(By.xpath("//input[contains(@id,'projectName')]"), "Input project name");
    private final IButton saveProject = getElementFactory().getButton(By.xpath("//button[contains(text(),'Save')]"), "Save project");
    private final ILabel successSaved = getElementFactory().getLabel(By.xpath("//div[contains(@class,'success')]"), "Project saved");
    private final List<ILabel> allProjects = getElementFactory().findElements(By.xpath("//div[contains(@class,'list-group')]/a"), ElementType.LABEL);

    public ProjectsPage() {
        super(By.xpath("//div[contains(text(),'projects')]"), "Projects page");
    }

    public String getVariantOnPage() {
        String footerText = pageFooter.getAttribute(AttributeConstants.ATTRIBUTE_TEXT_CONTENT);
        return RegExUtils.useRegExPattern(footerText, RegExPatterns.PATTERN_FOR_VARIANT);
    }

    public WebElement getAddProjectFrame() {
        return AqualityServices.getBrowser().getDriver().findElement(addProjectFrame);
    }

    public List<ILabel> getListProjects() {
        return allProjects;
    }

    public void clickNexageProject() {
        clickElement(nexageProject);
    }

    public void clickAddProject() {
        clickElement(addProject);
    }

    public void enterProjectName(String projectName) {
        clearAndTypeText(inputProjectName, projectName);
    }

    public void clickSaveProject() {
        clickElement(saveProject);
    }

    public boolean successSavedIsDisplayed() {
        return elementIsDisplayed(successSaved);
    }
}