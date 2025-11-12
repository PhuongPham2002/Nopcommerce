package commons.helpers;

import actions.pageObject.*;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.Set;


public class RegisterLoginHelper extends BaseTest {
    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private String registeredEmail;

    private static Set<Cookie> nopCommerceCookie;

    public static Set<Cookie> getNopCommerceCookie() {
        return nopCommerceCookie;
    }
    @BeforeTest
    public void preconditionBeforeTest(){
        getBrowserDriver()
        homePage = PageGenerator.getHomePage(getDriver());
        registerPage = homePage.clickRegisterLink();

        //Register new account:
        registerPage.fillRegisterForm(RegisterDataHelper.provideValidRequiredFields());
        registeredEmail = registerPage.getRegisteredEmailAddress();
        homePage = registerPage.clickRegisterButton();

        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),"Your registration completed");
        homePage = registerPage.clickLogoutLink();

        //Login with registered data:
        loginPage = homePage.clickLoginLink();
        loginPage.enterLoginForm(registeredEmail,RegisterDataHelper.PASSWORD);
        homePage=loginPage.clickLoginButton();

        nopCommerceCookie =driver.manage().getCookies();
        log.info("all cookie value :"+nopCommerceCookie);
        driver.quit();

    }


}
