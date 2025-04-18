package actions.pageObject.HeaderMenu.GiftCards;

import actions.pageObject.HeaderMenu.HeaderMenuPageObject;
import org.openqa.selenium.WebDriver;

public class GiftCardPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public GiftCardPageObject(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }
}
