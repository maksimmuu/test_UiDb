package org.tests;

import org.constants.AttributeConstants;
import org.constants.ConfigConstants;
import org.manager.TestDataManager;
import org.models.AttachmentInfo;
import org.models.LogInfo;
import org.models.ProjectInfo;
import org.models.TestInfo;
import org.openqa.selenium.Cookie;
import org.pages.ProjectTestsPage;
import org.pages.ProjectsPage;
import org.requests.RequestToTokenGet;
import org.steps.ProjectTestsPageSteps;
import org.steps.ProjectsPageSteps;
import org.steps.TestInfoPageSteps;
import org.templates.SqlQueryTemplates;
import org.testng.annotations.Test;
import org.utils.*;

import java.util.*;

import static org.templates.SqlQueryTemplates.*;

public class UIDBAppTestCase extends BaseTest {
    @Test
    public void testUiDbApp() {
        String token = RequestToTokenGet.doPostRequest().asString();
        ApiUtils.checkGenerateToken(token);

        Cookie cookie = new Cookie(AttributeConstants.KEY_TOKEN, token);
        DriverUtils.addCookie(cookie);
        DriverUtils.refresh();

        ProjectsPageSteps projectsPageSteps = new ProjectsPageSteps();
        projectsPageSteps.checkPageIsDisplayed();

        ProjectsPage projectsPage = new ProjectsPage();
        String receivedVariant = projectsPage.getVariantOnPage();

        ProjectsPageSteps.checkVariantOnPage(receivedVariant);

        projectsPage.clickNexageProject();
        String conditionNexageProject = SqlQueryTemplates.getSqlCondition(AttributeConstants.FIELD_NAME, ConfigConstants.NEXAGE_PROJECT_NAME);
        List<ProjectInfo> receivedResult = CrudProjectUtils.selectProjectsWithCondition(conditionNexageProject);
        Integer nexageProjectId = receivedResult.stream().map(ProjectInfo::getId).findFirst().orElse(null);
        List<TestInfo> receivedNexageTestsFromDB = CrudTestUtils.selectTestsWithCondition(getSqlCondition(AttributeConstants.FIELD_PROJECT_ID, nexageProjectId));

        ProjectTestsPageSteps projectTestsPageSteps = new ProjectTestsPageSteps();
        projectTestsPageSteps.checkDescendingTestDate();

        List<String> testNamesFromDB = receivedNexageTestsFromDB.stream().map(TestInfo::getName).toList();
        projectTestsPageSteps.checkListTestNamesOnPage(testNamesFromDB);

        ProjectTestsPage projectTestsPage = new ProjectTestsPage();
        projectTestsPage.clickHomeButton();


        String newProjectName = StringGenerator.generate(TestDataManager.getTestData().getLengthNameProject());
        projectsPage.clickAddProject();

        DriverUtils.switchToFrame(projectsPage.getAddProjectFrame());
        projectsPageSteps.saveNewProject(newProjectName);
        projectsPageSteps.checkSuccessfulSavingOfTheProject();
        DriverUtils.switchToDefaultContent();

        projectsPageSteps.closeFormAddProject();
        projectsPageSteps.checkCloseAddProjectFrame();
        DriverUtils.refresh();

        projectsPageSteps.checkProjectOnPageByName(newProjectName);

        projectsPageSteps.goToProjectByProjectName(newProjectName);
        String conditionWithNewProjectName = getSqlCondition(AttributeConstants.FIELD_NAME, newProjectName);
        List<ProjectInfo> projectsList = CrudProjectUtils.selectProjectsWithCondition(conditionWithNewProjectName);
        Integer newProjectId = projectsList.stream().map(ProjectInfo::getId).findFirst().orElse(null);

        TestInfo newTest = TestGenerator.generate();
        newTest.setProjectId(newProjectId);
        CrudTestUtils.saveTest(newTest);

        List<TestInfo> listTestByAuthorId = CrudTestUtils.selectTestsWithCondition(getSqlCondition(AttributeConstants.FIELD_AUTHOR_ID, TestDataManager.getTestData().getTestAuthorId()));
        Integer newTestId = listTestByAuthorId.stream().map(TestInfo::getId).findFirst().orElse(null);

        DriverUtils.makeScreenShot();

        LogInfo log = LogGenerator.generate();
        log.setTestId(newTestId);
        CrudLogUtils.saveLog(log);

        AttachmentInfo attachment = AttachmentGenerator.generate();
        attachment.setTestId(newTestId);
        CrudAttachmentUtils.saveAttachment(attachment);

        projectTestsPageSteps.checkTestOnPageByTestName(TestDataManager.getTestData().getTestName());

        projectTestsPageSteps.goToTestByTestName(TestDataManager.getTestData().getTestName());

        TestInfoPageSteps testInfoPageSteps = new TestInfoPageSteps();
        testInfoPageSteps.checkTestInfo(newTest);
    }
}