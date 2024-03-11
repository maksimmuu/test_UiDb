package org.steps;

import aquality.selenium.elements.interfaces.ILabel;
import org.pages.ProjectTestsPage;
import org.testng.Assert;
import org.utils.ParserUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectTestsPageSteps {
    private final ProjectTestsPage projectTestsPage;

    public ProjectTestsPageSteps() {
        projectTestsPage = new ProjectTestsPage();
    }

    private List<String> getListTestNames() {
        List<String> listTestNames = new ArrayList<>();
        List<ILabel> allTests = projectTestsPage.getListAllTests();

        for (int i = 1; i < allTests.size(); i++) {
            ILabel testName = projectTestsPage.getTestName(allTests.get(i));
            listTestNames.add(projectTestsPage.getTextElement(testName));
        }
        return listTestNames;
    }

    public void goToTestByTestName(String testName) {
        List<ILabel> allTests = projectTestsPage.getListAllTests();
        System.out.println(allTests);

        for (int i = 1; i < allTests.size(); i++) {
            if (projectTestsPage.getTextElement(projectTestsPage.getTestName(allTests.get(i))).equals(testName)) {
                projectTestsPage.clickElement(projectTestsPage.getTestName(allTests.get(i)));
                break;
            }
        }
    }

    public void checkTestOnPageByTestName(String testName) {
        Assert.assertTrue(getListTestNames().contains(testName), String.format("Тест c названием '%s' в списке тестов не найден", testName));
    }

    public void checkDescendingTestDate() {
        List<ILabel> allTests = projectTestsPage.getListAllTests();

        for (int i = 1; i < allTests.size() - 1; i++) {
            Date testDate = ParserUtils.parseStringToDate(projectTestsPage.getTestStartTime(allTests.get(i)));
            Date nextTestDate = ParserUtils.parseStringToDate(projectTestsPage.getTestStartTime(allTests.get(i + 1)));

            if (testDate.before(nextTestDate)) {
                Assert.fail(String.format("Даты тестов '%s' и '%s' идут не по убыванию", testDate, nextTestDate));
            }
        }
        Assert.assertTrue(true);
    }

    public void checkListTestNamesOnPage(List<String> expectedTestNamesList) {

        for (String name : getListTestNames()) {
            if (!expectedTestNamesList.contains(name)) {
                Assert.fail(String.format("Имя теста '%s' со страницы отсутствует в ожидаемом листе", name));
            }
        }

        Assert.assertTrue(true);
    }
}