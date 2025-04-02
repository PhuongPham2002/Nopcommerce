package testcases;

import actions.pageObject.AccountPageObject;
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
@Feature("Register")
public class Register extends BaseTest {
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private AccountPageObject accountPage;
    private String firstName,lastName,emailAddress,companyName,password,invalidEmail,existedEmail,invalidPassword;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url){
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        log.info("Pre-condition: Open RegisterPage");
        homePage = PageGenerator.getHomePage(driver);
        registerPage = homePage.clickRegisterLink();
        firstName ="Rosie";
        lastName ="Pham";
        emailAddress = "phuong"+generateRandomNumber()+"@gmail.com";
        invalidEmail ="phuong"+generateRandomNumber();
        companyName ="AI Automation";
        password ="123456789";
        invalidPassword ="1234";


        //Data nên test như nào đối với các trường textbox như này? ví dụ ký tự đặc biệt, tên dài ngắn...
        //data bất thường  --> lúc đó có nên dùng excel ko?

        //Với case Register thì bthg còn test rất nhiều trường hợp khác với datata test --> mai check xem ntn
    }

    @Step("TC01_Register with empty data")
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
    public void TC02_Register_InvalidEmail() throws InterruptedException {
        registerPage.checkGenderRadio();

        log.info("Enter valid firstname: "+firstName);
        registerPage.enterFirstnameTextbox(firstName);

        log.info("Enter valid lastname: "+lastName);
        registerPage.enterLastnameTextbox(lastName);

        log.info("Enter invalid email address: "+invalidEmail);
        registerPage.enterEmailAddressTextbox(invalidEmail);


        log.info("Enter valid company: "+companyName);
        registerPage.enterCompanyNameTextbox(companyName);

        registerPage.checkNewletterCheckbox();

        log.info("Enter valid password: "+password);
        registerPage.enterPasswordTextbox(password);


        log.info("Enter correct confirm password: "+password);
        registerPage.enterConfirmPasswordTextbox(password);


        registerPage.clickRegisterButton();

        log.info("Show error message: Please enter a valid email address");
        Assert.assertEquals(registerPage.getInvalidRegisterEmailMessage(),"Please enter a valid email address.");
        Thread.sleep(2000);

        //Behavior là khi mà click vào enter vào trường email thì nó đã hiển thị luôn error message rồi, mình nên assert
        //ngay lúc đó rồi hay đợi click Register rồi mới assert? (nếu assert ngay lúc đó rồi thì click register xong vẫn cần assert nhỉ)
        //có nhiều case invalid khác thì có cần check ko? nhap sai kí tự....
        //Data test, ví thử case password invalid thì có phải tạo data test nhiều loại pass invalid khác nhau?

    }

    @Test
    public void TC03_Register_ValidInfo() throws InterruptedException {

        Thread.sleep(5000);
        registerPage.checkGenderRadio();
        registerPage.enterFirstnameTextbox(firstName);
        registerPage.enterLastnameTextbox(lastName);
        registerPage.enterEmailAddressTextbox(emailAddress);
        existedEmail=registerPage.getRegisteredEmailAddress();
        registerPage.enterCompanyNameTextbox(companyName);
        registerPage.checkNewletterCheckbox();
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        accountPage = registerPage.clickRegisterButton();
        log.info("Show successful message: Your registration completed");
        Assert.assertEquals(accountPage.getSuccessfulRegisterMessage(),"Your registration completed");
        homePage = accountPage.clickLogoutButton();
        registerPage = homePage.clickRegisterLink();

    }


    @Test
    public void TC04_Register_ExistedEmail() throws InterruptedException {
        Thread.sleep(5000);
        registerPage.checkGenderRadio();
        registerPage.enterFirstnameTextbox(firstName);
        registerPage.enterLastnameTextbox(lastName);
        registerPage.enterEmailAddressTextbox(existedEmail);
        existedEmail=registerPage.getRegisteredEmailAddress();
        registerPage.enterCompanyNameTextbox(companyName);
        registerPage.checkNewletterCheckbox();
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(password);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getExistedEmailMessage(),"The specified email already exists");
    }
    @Test
    public void TC05_Register_InvalidPassword() throws InterruptedException {
        Thread.sleep(5000);
        registerPage.checkGenderRadio();
        registerPage.enterFirstnameTextbox(firstName);
        registerPage.enterLastnameTextbox(lastName);
        registerPage.enterEmailAddressTextbox(emailAddress);
        registerPage.enterCompanyNameTextbox(companyName);
        registerPage.checkNewletterCheckbox();
        registerPage.enterPasswordTextbox(invalidPassword);
        registerPage.enterConfirmPasswordTextbox(invalidPassword);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getInvalidPasswordMessage(),"Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");

    }
    @Test
    public void TC06_Register_MismatchedConfirmPassword() {
        registerPage.checkGenderRadio();
        registerPage.enterFirstnameTextbox(firstName);
        registerPage.enterLastnameTextbox(lastName);
        registerPage.enterEmailAddressTextbox(emailAddress);
        registerPage.enterCompanyNameTextbox(companyName);
        registerPage.checkNewletterCheckbox();
        registerPage.enterPasswordTextbox(password);
        registerPage.enterConfirmPasswordTextbox(invalidPassword);
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getMismatchedPasswordMessage(), "The password and confirmation password do not match.");
    }

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        closeBrowserDriver();


    }
}
