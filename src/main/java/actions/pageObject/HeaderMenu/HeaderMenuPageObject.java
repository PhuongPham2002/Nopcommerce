package actions.pageObject.HeaderMenu;

import actions.pageObject.HeaderMenu.Apparel.ApparelPageObject;
import actions.pageObject.HeaderMenu.Books.BooksPageObject;
import actions.pageObject.HeaderMenu.Computers.ComputerPageObject;
import actions.pageObject.HeaderMenu.DigitalDownloads.DigitalDownloadsPageObject;
import actions.pageObject.HeaderMenu.Electronics.ElectronicsPageObject;
import actions.pageObject.HeaderMenu.GiftCards.GiftCardPageObject;
import actions.pageObject.HeaderMenu.Jewery.JewelryPageObject;
import commons.BasePage;
import interfaces.pageUI.HeaderMenuPageUI;
import org.openqa.selenium.WebDriver;

public class HeaderMenuPageObject extends BasePage {
    WebDriver driver;
    public HeaderMenuPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public HeaderMenuPageObject clickHeaderMenuLinkByName(String pageName){
        waitForElementVisible(driver, interfaces.pageUI.HeaderMenuPageUI.HEADER_MENU_LINK_BY_NAME,pageName);
        clickElement(driver, interfaces.pageUI.HeaderMenuPageUI.HEADER_MENU_LINK_BY_NAME,pageName);
        sleepInSecond(5);
        switch (pageName.toLowerCase()){
            case "computers":
                return new ComputerPageObject(driver);
            case "electronics":
                return new ElectronicsPageObject(driver);
            case "apparel":
                return new ApparelPageObject(driver);
            case "digital downloads":
                return new DigitalDownloadsPageObject(driver);
            case "books":
                return new BooksPageObject(driver);
            case "jewelry":
                return new JewelryPageObject(driver);
            case "gift cards":
                return new GiftCardPageObject(driver);
            default:
                throw new RuntimeException("Page name is not valid");
        }


    }


}
