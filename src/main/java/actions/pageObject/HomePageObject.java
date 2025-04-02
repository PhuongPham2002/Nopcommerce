package actions.pageObject;

import commons.BasePage;
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
}
