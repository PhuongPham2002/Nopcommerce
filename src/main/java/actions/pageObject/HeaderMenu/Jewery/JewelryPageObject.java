package actions.pageObject.HeaderMenu.Jewery;

import actions.pageObject.HeaderMenu.HeaderMenuPageObject;
import org.openqa.selenium.WebDriver;

public class JewelryPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public JewelryPageObject(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }
}
