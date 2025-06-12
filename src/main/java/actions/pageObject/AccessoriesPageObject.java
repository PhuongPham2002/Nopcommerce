package actions.pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;

public class ComputerPageObject extends BasePage {
    WebDriver driver;

    public ComputerPageObject(WebDriver driver) {
        this.driver= driver;
    }

}
