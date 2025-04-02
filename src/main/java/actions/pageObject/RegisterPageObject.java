package actions.pageObject;

import commons.BasePage;
import interfaces.RegisterPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click register button ")
    public AccountPageObject clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver,RegisterPageUI.REGISTER_BUTTON);
        return PageGenerator.getAccountPage(driver);


    }

    @Step("Get firstname required message")
    public String getFirstnameRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.FIRSTNAME_REQUIRED_MESSAGE);
    }

    @Step("Get lastname required message")
    public String getLastnameRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.LASTNAME_REQUIRED_MESSAGE);
    }

    @Step("Get email required message")
    public String getEmailRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.EMAIL_REQUIRED_MESSAGE);
    }

    @Step("Get password required message")
    public String getPasswordRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.PASSWORD_REQUIRED_MESSAGE);
    }

    @Step("Enter firstname textbox with value: {0}")
    public void enterFirstnameTextbox(String firstName) {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.FIRSTNAME_TEXTBOX,firstName);
    }
    @Step("Check to genderRadio with female option")
    public void checkGenderRadio() {
        waitForElementVisible(driver,RegisterPageUI.FEMALE_GENDER_RADIO_BUTTON);
      checkCheckboxOrRadio(driver,RegisterPageUI.FEMALE_GENDER_RADIO_BUTTON);
    }

    @Step("Enter lastname textbox with value: {0}")
    public void enterLastnameTextbox(String lastName) {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.LASTNAME_TEXTBOX,lastName);

    }
    @Step("Enter emailAddress textbox with value:{0}")
    public void enterEmailAddressTextbox(String email) {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.EMAIL_TEXTBOX,email);
    }
    @Step("Enter company name with value:{0}")
    public void enterCompanyNameTextbox(String companyName) {
        waitForElementVisible(driver,RegisterPageUI.COMPANY_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.COMPANY_TEXTBOX,companyName);
    }
    @Step("Uncheck newsletter checkbox")
    public void checkNewletterCheckbox() {
        uncheckCheckboxOrRadio(driver,RegisterPageUI.NEWSLETTER_CHECKBOX);
    }
    @Step("Enter password with value:{0}")
    public void enterPasswordTextbox(String password) {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.PASSWORD_TEXTBOX,password);
    }
    @Step("Enter confirm password with value:{0}")
    public void enterConfirmPasswordTextbox(String password) {
        waitForElementVisible(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(driver,RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX,password);
    }

    @Step("Get invalid registered email message")
    public String getInvalidRegisterEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.INVALID_EMAIL_MESSAGE);
    }

    public String getRegisteredEmailAddress() {
        return getAttributeValue(driver,RegisterPageUI.EMAIL_TEXTBOX,"value");
    }

    public String getExistedEmailMessage() {
        waitForElementVisible(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
        return getElementText(driver,RegisterPageUI.EXISTED_EMAIL_MESSAGE);
    }

    public String getInvalidPasswordMessage() {
        waitForElementVisible(driver,RegisterPageUI.INVALID_PASSWORD_MESSAGE);
        return getElementText(driver,RegisterPageUI.INVALID_PASSWORD_MESSAGE);
    }

    public String getMismatchedPasswordMessage() {
        waitForElementVisible(driver,RegisterPageUI.MISMATCHED_CONFIRM_PASSWORD);
        return getElementText(driver,RegisterPageUI.MISMATCHED_CONFIRM_PASSWORD);
    }
}
