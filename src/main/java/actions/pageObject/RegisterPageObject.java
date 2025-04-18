package actions.pageObject;

import commons.BasePage;
import interfaces.pageUI.RegisterPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click register button to navigate to Register Page ")
    public HomePageObject clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver,RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Check to genderRadio with female option")
    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver,RegisterPageUI.GENDER_RADIO_BUTTON,gender);
      checkCheckboxOrRadio(driver,RegisterPageUI.GENDER_RADIO_BUTTON,gender);
    }


    @Step("Uncheck newsletter checkbox")
    public void checkNewletterCheckbox() {
        uncheckCheckboxOrRadio(driver,RegisterPageUI.NEWSLETTER_CHECKBOX);
    }


    @Step("Get invalid registered email message")
    public String getInvalidRegisterEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
    }

    @Step("Get registered email")
    public String getRegisteredEmailAddress() {
        return getAttributeValue(driver,RegisterPageUI.EMAIL_TEXTBOX,"value");
    }

    @Step("Get existed email message")
    public String getExistedEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
    }
    @Step("Fill in register form")
    public void fillRegisterForm(String firstname, String lastname, String emailAddress, String company, String password,String gender) {

//        checkGenderRadio(gender);
//        enterTextboxByID(driver, "FirstName",firstname);
//        enterTextboxByID(driver,"LastName",lastname);
//        enterTextboxByID(driver,"Email",emailAddress);
//        enterTextboxByID(driver,"Company",company);
//        checkNewletterCheckbox();
//        enterTextboxByID(driver,"Password",password);
//        enterTextboxByID(driver,"ConfirmPassword",password);

        checkGenderRadio(gender);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, "FirstName",firstname);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"LastName",lastname);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Email",emailAddress);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Company",company);
        checkNewletterCheckbox();
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Password",password);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"ConfirmPassword",password);
    }
    @Step("Fill in register form")
    public void fillRegisterForm(String firstname, String lastname, String emailAddress, String company, String password, String confirmPassword, String gender) {
//        checkGenderRadio(gender);
//        enterTextboxByID(driver, "FirstName",firstname);
//        enterTextboxByID(driver,"LastName",lastname);
//        enterTextboxByID(driver,"Email",emailAddress);
//        enterTextboxByID(driver,"Company",company);
//        checkNewletterCheckbox();
//        enterTextboxByID(driver,"Password",password);
//        enterTextboxByID(driver,"ConfirmPassword",confirmPassword);

        checkGenderRadio(gender);
        enterTextboxByID(driver, RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"FirstName",firstname);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"LastName",lastname);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Email",emailAddress);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Company",company);
        checkNewletterCheckbox();
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"Password",password);
        enterTextboxByID(driver,RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,"ConfirmPassword",confirmPassword);
    }
    @Step("Get error message about required field")
    public String getRequiredErrorMessage(String fieldName){
        waitForElementVisible(driver,RegisterPageUI.REGISTER_ERROR_MESSAGE_ID,fieldName);
        return getElementText(driver,RegisterPageUI.REGISTER_ERROR_MESSAGE_ID,fieldName);
    }

    @Step("Click Logout button to back to HomePage")
    public HomePageObject clickLogoutButton() {
        waitForElementClickable(driver, RegisterPageUI.LOGOUT_BUTTON);
        clickElement(driver,RegisterPageUI.LOGOUT_BUTTON);
        return PageGenerator.getHomePage(driver);
    }
}
