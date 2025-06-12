package actions.components.MyAccountSideBar;

import org.openqa.selenium.WebDriver;

public class RewardPointsPageObject extends MyAccountSideBarPageObject {
    WebDriver driver;

    public RewardPointsPageObject(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }
}
