package testcases;

import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.RegisterPageObject;
import commons.BaseTest;
import io.qameta.allure.*;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Register extends BaseTest {
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private String firstName,lastName,emailAddress,companyName,password;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url){
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        homePage = PageGenerator.getHomePageObject(driver);
        registerPage = homePage.clickRegisterLink();
        firstName ="Rosie";
        lastName ="Pham";
        emailAddress = "phuong"+generateRandomNumber()+"@gmail.com";
        companyName ="AI Automation";
        password ="123456789";

        //Data nên test như nào đối với các trường textbox như này? ví dụ ký tự đặc biệt, tên dài ngắn...
        //data bất thường  --> lúc đó có nên dùng excel ko?

        //Với case Register thì bthg còn test rất nhiều trường hợp khác với datata test --> mai check xem ntn
    }

    @Feature("Authentication")
    @Description ("Register with Empty Data")
    @Step ("Register with Empty Data")
    @Test
    public void TC01_Register_EmptyData(){
        log.info("Step 1: Click Register button");
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getFirstnameRequiredMessage(),"First name is required.");
        Assert.assertEquals(registerPage.getLastnameRequiredMessage(),"Last name is required.");
        Assert.assertEquals(registerPage.getEmailRequiredMessage(),"Email is required.");
        Assert.assertEquals(registerPage.getPasswordRequiredMessage(),"Password is required.");
    }
    @Test
    public void TC02_Register_InvalidEmail(){
//        registerPage.checkGenderRadio();
//        registerPage.enterFirstnameTextbox();
//        registerPage.enterLastnameTextbox();
//        registerPage.enterEmailAddressTextbox();
//        registerPage.enterCompanyNameTextbox();
//        registerPage.checkNewletterCheckbox();
//        registerPage.enterPasswordTextbox();
//        registerPage.emterConfirmPasswordTextbox();
//        registerPage.clickRegisterButton();
//        Assert.assertEquals(registerPage.getInvalidRegisterEmailMassage(),"");

        //Behavior là khi mà click vào enter vào trường email thì nó đã hiển thị luôn error message rồi, mình nên assert
        //ngay lúc đó rồi hay đợi click Register rồi mới assert? (nếu assert ngay lúc đó rồi thì click register xong vẫn cần assert nhỉ)


    }
    @Test
    public void TC03_Register_ValidInfo(){}
    @Test
    public void TC04_Register_ExistedEmail(){}
    @Test
    public void TC05_Register_InvalidPassword(){}
    @Test
    public void TC06_Register_MismatchedConfirmPassword(){}

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        closeBrowserDriver();


    }
}
