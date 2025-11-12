package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.LoginPageUI;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageObject extends BasePage {


    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Click login button")
    public HomePageObject clickLoginButton() {
        waitForElementClickable( LoginPageUI.LOGIN_BUTTON);
        clickElement(LoginPageUI.LOGIN_BUTTON);
        return PageGenerator.getHomePage(driver);
    }

    @Step("Enter login form with email & password")
    public void enterLoginForm(String emailAddress, String password){
        enterTextboxByID(LoginPageUI.LOGIN_FORM_TEXTBOX_ID,emailAddress,"Email");
        enterTextboxByID(LoginPageUI.LOGIN_FORM_TEXTBOX_ID,password,"Password");
    }
    @Step("Get error login message")
    public String getErrorLoginMessage(){
        waitForElementVisible(LoginPageUI.LOGIN_ERROR_MESSAGE);
        return getElementText(LoginPageUI.LOGIN_ERROR_MESSAGE);
    }

    @Step("Get unregistered error message")
    public String getUnregisteredErrorMessage() {
        waitForElementVisible(LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
        return getElementText(LoginPageUI.UNREGISTERED_EMAIL_ERROR_MESSAGE);
    }

    public String getEmptyPasswordErrorMessage() {
        waitForElementVisible(LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getInvalidPasswordErrorMessage() {
        waitForElementVisible(LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
        return getElementText(LoginPageUI.EMPTY_PASSWORD_ERROR_MESSAGE);
    }

    public String getWrongPasswordErrorMessage() {
        waitForElementVisible(LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
        return getElementText(LoginPageUI.WRONG_PASSWORD_ERROR_MESSAGE);
    }

    //Cucumber implement:

    public String getErrorMessage() {
        waitForElementVisible(LoginPageUI.ERROR_MESSAGE);
        return getElementText(LoginPageUI.ERROR_MESSAGE);
    }

    public void verifyErrorMessages(String expectedMessage, String position){
        switch (position){
            case "email":
                Assert.assertEquals(getErrorLoginMessage(),expectedMessage);
                break;
            case "summary":
                Assert.assertEquals(getErrorMessage(),expectedMessage);
                break;
            default:
                throw new IllegalArgumentException("Invalid position of message: "+position);
        }

    }


    public void clickOpenPasswordEyeIcon() {
        if (isEyeIconClosed()) {
            clickElement( LoginPageUI.PASSWORD_EYE_ICON);
        }

    }

    public void clickClosePasswordEyeIcon() {
        if (isEyeIconOpened()) {
            clickElement( LoginPageUI.PASSWORD_EYE_ICON);
        }

    }


    public Boolean isEyeIconOpened() {
        return isElementDisplayed(LoginPageUI.PASSWORD_OPEN);
    }

    public Boolean isEyeIconClosed() {
        return isElementDisplayed(LoginPageUI.PASSWORD_CLOSE);
    }
}

