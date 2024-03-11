package org.steps;

import aquality.selenium.elements.interfaces.ILabel;
import org.constants.AttributeConstants;
import org.constants.ConfigConstants;
import org.constants.RegExPatterns;
import org.manager.TestDataManager;
import org.models.ProjectInfo;
import org.models.TestInfo;
import org.pages.TestInfoPage;
import org.templates.SqlQueryTemplates;
import org.testng.Assert;
import org.utils.CrudProjectUtils;
import org.utils.ParserUtils;
import org.utils.RegExUtils;

import java.util.List;

public class TestInfoPageSteps {
    private final TestInfoPage testInfoPage;

    public TestInfoPageSteps() {
        testInfoPage = new TestInfoPage();
    }

    public void checkTestInfo(TestInfo expectedTest) {
        String actualProjectNameOnPage = testInfoPage.getProjectName();
        String sqlConditionWithActualProjectName = SqlQueryTemplates.getSqlCondition(AttributeConstants.FIELD_NAME, actualProjectNameOnPage);
        List<ProjectInfo> actualProjects = CrudProjectUtils.selectProjectsWithCondition(sqlConditionWithActualProjectName);
        Integer actualProjectId = actualProjects.stream().map(ProjectInfo::getId).findFirst().orElse(null);

        Assert.assertEquals(actualProjectId, expectedTest.getProjectId(),
                String.format("Полученный id проекта '%s' не соответсвует ожидаемому '%s'", actualProjectId, expectedTest.getProjectId()));

        Assert.assertEquals(testInfoPage.getTestName(), expectedTest.getName(),
                String.format("Полученное название теста не соответсвует ожидаемому '%s'", expectedTest.getName()));

        Assert.assertEquals(testInfoPage.getTestMethodName(), expectedTest.getMethodName(),
                String.format("Полученное название тестового метода не соответсвует ожидаемому '%s'", expectedTest.getMethodName()));

        Assert.assertEquals(testInfoPage.getTestStatus(), TestDataManager.getTestData().getTestFailed(),
                String.format("Полученный статус теста не соответсвует ожидаемому '%s'", TestDataManager.getTestData().getTestFailed()));

        Assert.assertEquals(testInfoPage.getEnvironment(), expectedTest.getEnv(),
                String.format("Полученное название окружения не соответсвует ожидаемому '%s'", expectedTest.getEnv()));

        Assert.assertEquals(testInfoPage.getBrowser(), expectedTest.getBrowser(),
                String.format("Полученное название браузера не соответсвует ожидаемому '%s'", expectedTest.getBrowser()));

        String startTimeStamp = RegExUtils.useRegExPattern(expectedTest.getStartTime().toString(), RegExPatterns.PATTERN_FOR_TIMESTAMP);
        Assert.assertTrue(testInfoPage.getTestStartTime().contains(startTimeStamp),
                String.format("Полученное время начала теста '%s' не соответствует ожидаемому '%s'", testInfoPage.getTestStartTime(), expectedTest.getStartTime().toString()));

        String endTimeStamp = RegExUtils.useRegExPattern(expectedTest.getEndTime().toString(), RegExPatterns.PATTERN_FOR_TIMESTAMP);
        Assert.assertTrue(testInfoPage.getTestEndTime().contains(endTimeStamp),
                String.format("Полученное время конца теста '%s' не соответствует ожидаемому '%s'", testInfoPage.getTestEndTime(), expectedTest.getEndTime().toString()));

        ILabel screenShotOnPage = testInfoPage.getTestScreenShot();
        String srcScreenShotOnPage = testInfoPage.getAttributeElement(screenShotOnPage, AttributeConstants.ATTRIBUTE_SRC);
        String[] words = srcScreenShotOnPage.split(RegExPatterns.PATTERN_FOR_SOURCE_IMAGE);
        String screenShotOnPageCode = words[ConfigConstants.INDEX_1];

        String encodedScreenShot = ParserUtils.parseImageToString(ConfigConstants.PATH_TO_SCREENSHOT);

        Assert.assertEquals(screenShotOnPageCode, encodedScreenShot, String.format("Код изображения со страницы '%s' не совпадает с ожидаемым '%s'",
                screenShotOnPageCode, encodedScreenShot));
    }
}