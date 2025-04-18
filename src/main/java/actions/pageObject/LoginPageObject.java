package actions.pageObject;

import commons.BasePage;
import interfaces.pageUI.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Click login button")
    public HomePageObject clickLoginButton() {
        waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
        clickElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Enter login form with email & password")
    public void enterLoginForm(String emailAddress, String password){
        enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,"Email",emailAddress);
        enterTextboxByID(driver,LoginPageUI.LOGIN_FORM_TEXTBOX_ID,"Password",password);
    }
    @Step("Get error login message")
    public String getErrorLoginMessage(String fieldName){
        waitForElementVisible(driver,LoginPageUI.LOGIN_ERROR_MESSAGE_ID,fieldName);
        return getElementText(driver,LoginPageUI.LOGIN_ERROR_MESSAGE_ID,fieldName);
    }

    @Step("Get unregistered error message")
    public String getUnregisteredErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
    }

    public String getEmptyPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getWrongPasswordErrorMessage() {
        waitForElementVisible(driver,LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver,LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
    }
}

