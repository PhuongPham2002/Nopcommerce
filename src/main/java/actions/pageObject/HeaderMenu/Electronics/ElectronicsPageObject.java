package actions.pageObject.HeaderMenu.Electronics;

import actions.pageObject.HeaderMenu.HeaderMenuPageObject;
import org.openqa.selenium.WebDriver;

public class ElectronicsPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public ElectronicsPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;

    }
}
