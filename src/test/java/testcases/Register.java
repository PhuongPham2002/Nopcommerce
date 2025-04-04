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

    //Behavior là khi mà click vào enter vào trường email thì nó đã hiển thị luôn error message rồi, mình nên assert
    //ngay lúc đó rồi hay đợi click Register rồi mới assert? (nếu assert ngay lúc đó rồi thì click register xong vẫn cần assert nhỉ)
    //có nhiều case invalid khác thì có cần check ko? nhap sai kí tự....
    //Data test, ví thử case password invalid thì có phải tạo data test nhiều loại pass invalid khác nhau?
    //có test validation của từng trường ko? (ví dụ username ko nhập ký tự đặc biệt, dài bao nhiêu ký tự....)

    private WebDriver driver;
    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private AccountPageObject accountPage;
    private String firstName,lastName,emailAddress,companyName,password,invalidEmail,existedEmail,invalidPassword,gender;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url){
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
        gender = "Female";


        //Data nên test như nào đối với các trường textbox như này? ví dụ ký tự đặc biệt, tên dài ngắn...
        //data bất thường  --> lúc đó có nên dùng excel ko?
        //Với case Register thì bthg còn test rất nhiều trường hợp khác với datata test --> mai check xem ntn
    }

    @Test
    public void Register_01_EmptyData_Validation(){
        registerPage.clickRegisterButton();
        Assert.assertEquals(registerPage.getRequiredErrorMessage("FirstName"),"First name is required.");
        Assert.assertEquals(registerPage.getRequiredErrorMessage("LastName"),"Last name is required.");
        Assert.assertEquals(registerPage.getRequiredErrorMessage("Email"),"Email is required.");
        Assert.assertEquals(registerPage.getRequiredErrorMessage("ConfirmPassword"),"Password is required.");
    }
    @Test
    public void Register_02_InvalidEmail(){
        log.info(String.format("Register Info: %s,%s,%s,%s,%s",firstName,lastName,invalidEmail,companyName,password,gender));
        registerPage.fillRegisterForm(firstName,lastName,invalidEmail,companyName,password,gender);
        registerPage.clickRegisterButton();
        log.info("Assert Error Message: " + registerPage.getInvalidRegisterEmailMessage());
        Assert.assertEquals(registerPage.getInvalidRegisterEmailMessage(),"Please enter a valid email address.");



    }

    @Test
    public void Register_03_ValidInfo() {
        log.info(String.format("Register Info: %s,%s,%s,%s,%s,%s",firstName,lastName,emailAddress,companyName,password,gender));
        registerPage.fillRegisterForm(firstName,lastName,emailAddress,companyName,password,gender);
        existedEmail=registerPage.getRegisteredEmailAddress();
        homePage = registerPage.clickRegisterButton();
        log.info("Assert successful message: "+homePage.getSuccessfulRegisterMessage());
        Assert.assertEquals(homePage.getSuccessfulRegisterMessage(),"Your registration completed");
        homePage = registerPage.clickLogoutButton();
        registerPage = homePage.clickRegisterLink();
    }


    @Test
    public void Register_04_ExistedEmail() {
        log.info(String.format("Register Info: %s,%s,%s,%s,%s,%s",firstName,lastName,existedEmail,companyName,password,gender));
        registerPage.fillRegisterForm(firstName,lastName,existedEmail,companyName,password,gender);
        registerPage.clickRegisterButton();
        log.info("Assert ExistedEmail Error message: "+registerPage.getExistedEmailMessage());
        Assert.assertEquals(registerPage.getExistedEmailMessage(),"The specified email already exists");
    }
    @Test
    public void Register_05_InvalidPassword() {
        log.info(String.format("Register Info: %s,%s,%s,%s,%s,%s",firstName,lastName,emailAddress,companyName,invalidPassword,gender));
        registerPage.fillRegisterForm(firstName,lastName,emailAddress,companyName,invalidPassword,gender);
        registerPage.clickRegisterButton();
        log.info("Assert invalid password message: "+registerPage.getRequiredErrorMessage("Password"));
        Assert.assertEquals(registerPage.getRequiredErrorMessage("Password"),"Password must meet the following rules: must have at least 6 characters and not greater than 64 characters");

    }
    @Test
    public void Register_06_MismatchedConfirmPassword() {
        log.info(String.format("Register Info: %s,%s,%s,%s,%s,%s,%s",firstName,lastName,emailAddress,companyName,invalidPassword,password,gender));
        registerPage.fillRegisterForm(firstName,lastName,emailAddress,companyName,invalidPassword,password,gender);
        registerPage.clickRegisterButton();
        log.info("Assert invalid password message: "+registerPage.getRequiredErrorMessage("ConfirmPassword"));
        Assert.assertEquals(registerPage.getRequiredErrorMessage("ConfirmPassword"), "The password and confirmation password do not match.");
    }

    @AfterClass (alwaysRun = true)
    public void tearDown(){
        closeBrowserDriver();


    }
}
