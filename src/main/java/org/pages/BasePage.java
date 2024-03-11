package org.pages;

import aquality.selenium.elements.interfaces.IElement;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public abstract class BasePage extends Form {
    protected final By uniqueElementLocator;
    protected final String namePage;

    protected BasePage(By uniqueElementLocator, String namePage) {
        super(uniqueElementLocator, namePage);
        this.uniqueElementLocator = uniqueElementLocator;
        this.namePage = namePage;
    }

    public void clickElement(IElement iElement) {
        iElement.click();
    }

    public boolean elementIsDisplayed(IElement iElement) {
        return iElement.state().isDisplayed();
    }

    public void clearAndTypeText(ITextBox textBox, String text) {
        textBox.clearAndType(text);
    }

    public String getTextElement(IElement iElement) {
        return iElement.getText();
    }

    public String getAttributeElement(IElement iElement, String attributeName) {
        return iElement.getAttribute(attributeName);
    }

    public String getNamePage() {
        return namePage;
    }

    public boolean pageIsDisplayed() {
        ILabel uniqueElement = getElementFactory().getLabel(uniqueElementLocator, String.format("Unique element of the '%s'", namePage));
        return elementIsDisplayed(uniqueElement);
    }
}