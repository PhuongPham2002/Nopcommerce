package actions.pageObject.HeaderMenu.Computers;

import org.openqa.selenium.WebDriver;

public class DesktopsPageObject extends ComputerSubMenuPageObject {
    WebDriver driver;

    public DesktopsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
