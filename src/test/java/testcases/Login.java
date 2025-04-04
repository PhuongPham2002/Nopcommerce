package testcases;

import actions.pageObject.HomePageObject;
import actions.pageObject.LoginPageObject;
import actions.pageObject.PageGenerator;
import commons.BaseTest;
import interfaces.LoginPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Login extends BaseTest {
    // Gom nhóm các error message có locator tương tự nhau, locator khác nhau thì vẫn phải tạo 1 locator khác.
    private HomePageObject homePage;
    private LoginPageObject loginPage;
    private String emailAddress,password,invalidEmail,unregisteredEmail,invalidPassword;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        log.info("Pre-condition: Open Login Page");
        homePage = PageGenerator.getHomePage(driver);
        loginPage = homePage.clickLoginLink();
        emailAddress = "phuong458@gmail.com";
        invalidEmail ="phuong"+generateRandomNumber();
        unregisteredEmail = "hoa@gmail.com";
        password ="123456789";
        invalidPassword ="1234";
    }

    @Test
    public void Login_01_EmptyData() {
        loginPage.clickLoginButton();
        log.info("Empty data error message: "+loginPage.getErrorLoginMessage("Email"));
        Assert.assertEquals(loginPage.getErrorLoginMessage("Email"),"Please enter your email");
    }

    @Test
    public void Login_02_InvalidEmail() {
        loginPage.enterLoginForm(invalidEmail,password);
        loginPage.clickLoginButton();
        log.info("Invalid email error message: "+loginPage.getErrorLoginMessage("Email"));
        Assert.assertEquals(loginPage.getErrorLoginMessage("Email"),"Please enter a valid email address.");
    }

    @Test
    public void Login_03_NonRegisteredEmail() {
        loginPage.enterLoginForm(unregisteredEmail,password);
        loginPage.clickLoginButton();
        log.info("Unregistered email error message: "+loginPage.getUnregisteredErrorMessage());
        Assert.assertEquals(loginPage.getUnregisteredErrorMessage(),"Login was unsuccessful. Please correct the errors and try again." +
                "\n" + "No customer account found");
    }


    @Test
    public void Login_04_EmptyPassword() {

        loginPage.enterLoginForm(emailAddress,"");
        loginPage.clickLoginButton();
        log.info("Empty password error message: "+loginPage.getEmptyPasswordErrorMessage());
        Assert.assertEquals(loginPage.getEmptyPasswordErrorMessage(),"Login was unsuccessful. Please correct the errors and try again." +
                "\n" + "No customer account found");

        //locator class  not ID
    }

    @Test
    public void Login_05_InvalidPassword(){
        loginPage.enterLoginForm(emailAddress,invalidPassword);
        loginPage.clickLoginButton();
        log.info("Invalid password error message: "+loginPage.getInvalidPasswordErrorMessage());
        Assert.assertEquals(loginPage.getInvalidPasswordErrorMessage(),"Login was unsuccessful. Please correct the errors and try again." +
                "\n" + "No customer account found");
    }

    @Test
    public void Login_06_ValidInfo() {
       loginPage.enterLoginForm(emailAddress,password);
       log.info("Log in successful - redirect to HomePage");
       homePage=loginPage.clickLoginButton();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
