package actions.pageObject;

import commons.BasePage;
import interfaces.AccountPageUI;
import interfaces.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickElement(driver,HomePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);

    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }

    public LoginPageObject clickLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickElement(driver,HomePageUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }
}
