package actions.pageObject;

import org.openqa.selenium.WebDriver;

public class ComputerPageObject extends HeaderMenuPageObject {
    WebDriver driver;
    public ComputerPageObject(WebDriver driver) {
        super(driver);
        this.driver= driver;
    }

//    public ComputerSubMenuPageObject clickSubMenuLink(String subMenuName) {
//        waitForElementVisible(driver, ComputerSubMenuPageUI.SUBMENU_LINK,subMenuName);
//        clickElement(driver,ComputerSubMenuPageUI.SUBMENU_LINK,subMenuName);
//        switch (subMenuName.toLowerCase()){
//            case "desktops":
//                return new DesktopsPageObject(driver);
//            case "notebooks":
//                return new NotebooksPageObject(driver);
//            case "software":
//                return new SoftwarePageObject(driver);
//            default:
//                throw new RuntimeException("Sub menu name is not valid");
//        }
//    }
}
