package testcases;

import actions.pageObject.AccountPageObject;
import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.RegisterPageObject;
import commons.base.BaseTest;
import commons.constants.RegisterMessageConstants;
import data.helpers.RegisterDataHelper;
import data.provider.RegisterDataProvider;
import dataObjects.RegisterTestData;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Feature("Register")
public class RegisterTests extends BaseTest {

    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private AccountPageObject accountPage;
    private String registeredEmail;

    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url){
        driver = getBrowserDriver(browser,url);
        homePage = PageGenerator.getHomePage(driver);
        registerPage = homePage.clickRegisterLink();
    }

    @BeforeMethod
    public void openRegisterPage(){
        registerPage.clickRegisterLink();

    }

    @Test
    public void Register_01_RegisterWithValidRequiredField() {
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFields());
        registeredEmail =registerPage.getRegisteredEmailAddress();
        homePage = registerPage.clickRegisterButton();
        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),RegisterMessageConstants.SUCCESSFULLY_REGISTER);
        homePage = registerPage.clickLogoutButton();
        registerPage = homePage.clickRegisterLink();
    }

    @Test
    public void Register_02_RegisterWithValidRequiredFieldAndGenderSelection(){
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFieldsAndGenderSelection());
        registeredEmail =registerPage.getRegisteredEmailAddress();
        homePage = registerPage.clickRegisterButton();
        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),RegisterMessageConstants.SUCCESSFULLY_REGISTER);
        homePage = registerPage.clickLogoutButton();
        registerPage = homePage.clickRegisterLink();
    }

    @Test
    public void Register_01_EmptyData_Validation(){
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("FirstName"), RegisterMessageConstants.REQUIRED_FIRST_NAME);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("LastName"),RegisterMessageConstants.REQUIRED_LAST_NAME);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("Email"),RegisterMessageConstants.REQUIRED_EMAIL);
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("ConfirmPassword"),RegisterMessageConstants.REQUIRED_PASSWORD);
    }


    @Test (dataProvider = "Invalid Emails", dataProviderClass = RegisterDataProvider.class)
    public void Register_02_InvalidEmail(RegisterTestData registerTestData){
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getInvalidRegisterEmailMessage(),RegisterMessageConstants.INVALID_EMAIL);
    }


    @Test (dependsOnMethods = "Register_01_RegisterWithValidRequiredField" )
    public void Register_04_ExistedEmail() {
        registerPage.fillRegisterForm(RegisterDataHelper.provideExistedEmailData(registeredEmail));
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getExistedEmailMessage(),RegisterMessageConstants.EXISTED_EMAIL);
    }
    @Test (dataProvider = "Invalid Password",dataProviderClass = RegisterDataProvider.class)
    public void Register_05_InvalidPassword(RegisterTestData registerTestData) {
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("Password"),RegisterMessageConstants.INVALID_PASSWORD);

    }
    @Test (dataProvider = "Mismatch confirm password", dataProviderClass = RegisterDataProvider.class)
    public void Register_06_MismatchedConfirmPassword(RegisterTestData registerTestData) {
        registerPage.fillRegisterForm(registerTestData);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getErrorMessageForRequireField("ConfirmPassword"), RegisterMessageConstants.MISMATCHED_PASSWORD);
    }

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        closeBrowserDriver();


    }
}
