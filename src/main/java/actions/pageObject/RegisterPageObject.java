package actions.pageObject;

import commons.BasePage;
import interfaces.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {
    WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton() {
        waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
        clickElement(driver,RegisterPageUI.REGISTER_BUTTON);

    }

    public String getFirstnameRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.FIRSTNAME_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.FIRSTNAME_REQUIRED_MESSAGE);
    }

    public String getLastnameRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.LASTNAME_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.LASTNAME_REQUIRED_MESSAGE);
    }

    public String getEmailRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.EMAIL_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.EMAIL_REQUIRED_MESSAGE);
    }

    public String getPasswordRequiredMessage() {
        waitForElementVisible(driver,RegisterPageUI.PASSWORD_REQUIRED_MESSAGE);
        return getElementText(driver,RegisterPageUI.PASSWORD_REQUIRED_MESSAGE);
    }
}
