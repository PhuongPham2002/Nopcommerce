package testcases;

import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.RegisterPageObject;
import commons.base.BaseTest;
import commons.constants.RegisterMessageConstants;
import commons.helpers.JsonHelper;
import commons.helpers.RegisterDataHelper;
import data.provider.RegisterDataProvider;
import dataObjects.RegisterTestData;
import io.cucumber.java.ht.Epi;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Arrays;

@Epic("User Management")
@Feature("Register")
public class RegisterTests extends BaseTest {
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private String registeredEmail;

    @BeforeClass (alwaysRun = true)
    public void setupBeforeClassRun(){
        getBrowserDriver();
        homePage = PageGenerator.getHomePage(getDriver());
        log.info("Thread ID: " + Thread.currentThread().getId() +
                " with browser: " );
        log.info("Thread ID: " + Thread.currentThread().getId() +
                " with driver: " + getDriver().toString());
        registerPage = homePage.clickRegisterLink();
    }

//    @BeforeMethod
//    public void openRegisterPage(){
//        registerPage.clickRegisterLink();
//
//    }
    @Story("Valid Register")
    @Test (groups = {"test"})
    public void Register_01_RegisterWithValidRequiredField() {
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFields());
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getSuccessfulRegisterMessage(),RegisterMessageConstants.SUCCESSFULLY_REGISTER);
        JsonHelper.saveUserData(RegisterDataHelper.EMAIL_ADDRESS,RegisterDataHelper.PASSWORD);
        homePage = registerPage.clickLogoutLink();
        registerPage = homePage.clickRegisterLink();
    }

    @Test (groups = {"test"})
    public void Register_02_RegisterWithValidRequiredFieldAndGenderSelection(){
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFieldsAndGenderSelection());
        homePage = registerPage.clickRegisterButton();
        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),RegisterMessageConstants.SUCCESSFULLY_REGISTER);
        JsonHelper.saveUserData(RegisterDataHelper.EMAIL_ADDRESS,RegisterDataHelper.PASSWORD);
        homePage = registerPage.clickLogoutLink();
        registerPage = homePage.clickRegisterLink();
    }

    @Test
    public void Register_03_EmptyData_Validation(){
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("FirstName"), RegisterMessageConstants.REQUIRED_FIRST_NAME);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("LastName"),RegisterMessageConstants.REQUIRED_LAST_NAME);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("Email"),RegisterMessageConstants.REQUIRED_EMAIL);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("ConfirmPassword"),RegisterMessageConstants.REQUIRED_PASSWORD);
    }


    @Test (dataProvider = "Invalid Emails", dataProviderClass = RegisterDataProvider.class)
    public void Register_04_InvalidEmail(RegisterTestData registerTestData){
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getInvalidRegisterEmailMessage(),RegisterMessageConstants.INVALID_EMAIL);
    }

    @Test ()
    public void Register_05_ExistedEmail() {
        registerPage.fillRegisterForm(RegisterDataHelper.provideExistedEmailData(registeredEmail));
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getExistedEmailMessage(),RegisterMessageConstants.EXISTED_EMAIL);
    }
    @Test (dataProvider = "Invalid Password",dataProviderClass = RegisterDataProvider.class)

    public void Register_06_InvalidPassword(RegisterTestData registerTestData) {
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("Password"),RegisterMessageConstants.INVALID_PASSWORD);

    }
    //@Test (dataProvider = "Mismatch confirm password", dataProviderClass = RegisterDataProvider.class)
    public void Register_07_MismatchedConfirmPassword(RegisterTestData registerTestData) {
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("ConfirmPassword"), RegisterMessageConstants.MISMATCHED_PASSWORD);
    }

    @AfterMethod
    public void cleanUp(ITestResult result){
        String[] groups = result.getMethod().getGroups();
        if (groups!=null && Arrays.asList(groups).contains("needCleanUp")){
            homePage = registerPage.clickLogoutLink();
            registerPage = homePage.clickRegisterLink();
        }
    }

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        closeBrowserDriver();


    }
}
