package org.steps;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ILabel;
import org.manager.TestDataManager;
import org.openqa.selenium.JavascriptExecutor;
import org.pages.ProjectsPage;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ProjectsPageSteps {
    private final ProjectsPage projectsPage;

    public ProjectsPageSteps() {
        projectsPage = new ProjectsPage();
    }

    public void saveNewProject(String projectName) {
        projectsPage.enterProjectName(projectName);
        projectsPage.clickSaveProject();
    }

    public void closeFormAddProject() {
        JavascriptExecutor js = AqualityServices.getBrowser().getDriver();
        js.executeScript("closePopUp();", projectsPage.getAddProjectFrame());
    }

    public void goToProjectByProjectName(String projectName) {
        List<ILabel> allProjects = projectsPage.getListProjects();

        for (ILabel project : allProjects) {
            if (projectsPage.getTextElement(project).equals(projectName)) {
                projectsPage.clickElement(project);
                break;
            }
        }
    }

    public void checkSuccessfulSavingOfTheProject() {
        Assert.assertTrue(projectsPage.successSavedIsDisplayed(), "Нет сообщения об успешном сохранении проекта");
    }

    public static void checkVariantOnPage(String actualVariant) {
        Assert.assertEquals(actualVariant, TestDataManager.getTestData().getVariant(),
                String.format("Полученный вариант '%s' не соответствует ожидаемому '%s'", actualVariant, TestDataManager.getTestData().getVariant()));
    }

    public void checkPageIsDisplayed() {
        Assert.assertTrue(projectsPage.pageIsDisplayed(), String.format("Страница '%s' не отображена", projectsPage.getNamePage()));
    }

    public void checkCloseAddProjectFrame() {
        Assert.assertFalse(projectsPage.successSavedIsDisplayed(), "Окно добавления проекта не закрылось");
    }

    public void checkProjectOnPageByName(String projectName) {
        List<String> allProjectNames = new ArrayList<>();

        for (ILabel project : projectsPage.getListProjects()) {
            allProjectNames.add(projectsPage.getTextElement(project));
        }

        Assert.assertTrue(allProjectNames.contains(projectName), String.format("Имя проекта '%s' не появилось в списке на странице", projectName));
    }
}