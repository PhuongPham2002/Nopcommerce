package actions.pageObject;

import actions.components.Header.HeaderComponent;
import actions.components.ValidationMessageComponent;
import commons.base.BasePage;
import commons.helpers.JsonHelper;
import commons.helpers.RegisterDataHelper;
import dataObjects.RegisterTestData;
import interfaces.pageUI.RegisterPageUI;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    ValidationMessageComponent validationMessage;
    HeaderComponent header;

    public RegisterPageObject(WebDriver driver) {
        super(driver);
        this.validationMessage = new ValidationMessageComponent(driver);
        this.header = new HeaderComponent(driver);
    }

    @Step("Click register button")
    public HomePageObject clickRegisterButton() {
        waitForElementClickable( RegisterPageUI.REGISTER_BUTTON);
        clickElement( RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Select gender from gender radio")
    public void checkGenderRadio(String gender) {
        waitForElementVisible( RegisterPageUI.GENDER_RADIO_BUTTON, gender);
        checkCheckboxOrRadio( RegisterPageUI.GENDER_RADIO_BUTTON, gender);
    }


    @Step("Unselect newsletter checkbox")
    public void checkNewletterCheckbox() {
        uncheckNativeCheckbox( RegisterPageUI.NEWSLETTER_CHECKBOX);
    }


    @Step("Get invalid email message")
    public String getInvalidRegisterEmailMessage() {
        waitForElementVisible( RegisterPageUI.INVALID_EMAIL_MESSAGE);
        String errorMessage = getElementText( RegisterPageUI.INVALID_EMAIL_MESSAGE);
        Allure.step("Error message: "+errorMessage);
        return errorMessage;
    }

    @Step("Get successful registration message")
    public String getSuccessfulRegisterMessage() {
        waitForElementVisible( RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
        String successMessage = getElementText( RegisterPageUI.SUCCESSFUL_REGISTER_MESSAGE);
        Allure.step("Success message: "+successMessage);
        return successMessage;
    }

    @Step("Get registered email")
    public String getRegisteredEmailAddress() {
        return this.getDOMPropertyValue( RegisterPageUI.EMAIL_TEXTBOX, "value");
    }

    @Step("Get error message about existed email")
    public String getExistedEmailMessage() {
        waitForElementVisible( RegisterPageUI.EXISTED_EMAIL_MESSAGE);
        Allure.step("Error message: "+ RegisterPageUI.EXISTED_EMAIL_MESSAGE);
        return getElementText(RegisterPageUI.EXISTED_EMAIL_MESSAGE);
    }

    @Step("Input into register form")
    public void fillRegisterForm(RegisterTestData registerData) {
        checkGenderRadio(RegisterDataHelper.GENDER);
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, RegisterDataHelper.FIRST_NAME, "FirstName");
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID,RegisterDataHelper.LAST_NAME, "LastName");
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, RegisterDataHelper.getUniqueEmailAddress(), "Email");
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, RegisterDataHelper.COMPANY_NAME, "Company");
        checkNewletterCheckbox();
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, RegisterDataHelper.PASSWORD, "Password");
        enterTextboxByID( RegisterPageUI.REGISTER_FORM_TEXTBOX_ID, RegisterDataHelper.CONFIRM_PASSWORD, "ConfirmPassword");
    }


    @Step("Get error message about required fields")
    public String getErrorMessageForRequireField(String fieldName) {
        Allure.step("error message: "+ validationMessage.getErrorMessageForRequiredField(fieldName));
        return validationMessage.getErrorMessageForRequiredField(fieldName);
    }

    @Step("Click Logout button")
    public HomePageObject clickLogoutLink() {
        header.account.clickLogoutLink();
        return PageGenerator.getHomePage(driver);
    }
    @Step ("Click Register link")
    public void clickRegisterLink() {
        header.account.clickRegisterLink();
    }
}
