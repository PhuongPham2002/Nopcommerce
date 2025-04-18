package actions.pageObject;

import actions.pageObject.HeaderMenu.Computers.NotebooksPageObject;
import actions.pageObject.MyAccountSideBar.MyAccountSideBarPageObject;
import commons.BasePage;
import interfaces.pageUI.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {
    WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickRegisterLink() {
        waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
        clickElement(driver,HomePageUI.REGISTER_LINK);
        return PageGenerator.getRegisterPage(driver);

    }

    public String getSuccessfulRegisterMessage() {
        waitForElementVisible(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
        return getElementText(driver,HomePageUI.SUCCESSFUL_REGISTER_MESSAGE);
    }

    public LoginPageObject clickLoginLink() {
        waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
        clickElement(driver,HomePageUI.LOGIN_LINK);
        return PageGenerator.getLoginPage(driver);
    }

    public MyAccountSideBarPageObject clickMyAccountLink() {
        waitForElementClickable(driver,HomePageUI.MY_ACCOUNT_LINK);
        clickElement(driver,HomePageUI.MY_ACCOUNT_LINK);
        return PageGenerator.getMyAccountSideBarPage(driver);
    }

    public void hoverToHeaderMenuItem(String menuItemName) {
        hoverToElement(driver,HomePageUI.HEADER_MENU_ITEM_BY_NAME,menuItemName);
        sleepInSecond(3);


    }

    public NotebooksPageObject clickSubmenuItem(String subMenuItem) {
        //hoverToHeaderMenuItem(menuItemName);
        waitForElementVisible(driver,HomePageUI.DYNAMIC_SUBMENU_LINK,subMenuItem);
        clickElement(driver,HomePageUI.DYNAMIC_SUBMENU_LINK,subMenuItem);
        sleepInSecond(3);
        return new NotebooksPageObject(driver);
    }
}
