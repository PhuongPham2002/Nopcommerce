package actions.pageObject.HeaderMenu.DigitalDownloads;

import actions.pageObject.HeaderMenu.HeaderMenuPageObject;
import org.openqa.selenium.WebDriver;

public class DigitalDownloadsPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public DigitalDownloadsPageObject(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }
}
