package actions.pageObject.HeaderMenu.Apparel;

import actions.pageObject.HeaderMenu.HeaderMenuPageObject;
import org.openqa.selenium.WebDriver;

public class ApparelPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public ApparelPageObject(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }
}
