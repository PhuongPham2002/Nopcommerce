package actions.pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class AccountPageObject extends BasePage {
    WebDriver driver;

    public AccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

}
