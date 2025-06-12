package actions.components.MyAccountSideBar;

import org.openqa.selenium.WebDriver;

public class BackInStockSubscriptionsPageObject extends MyAccountSideBarPageObject {
    WebDriver driver;

    public BackInStockSubscriptionsPageObject(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }
}
