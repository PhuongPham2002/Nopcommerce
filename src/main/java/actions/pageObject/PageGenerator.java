package actions.pageObject;

import actions.components.FooterComponent;
import actions.pageObject.HeaderMenu.Books.BooksPageObject;
import actions.pageObject.HeaderMenu.DigitalDownloads.DigitalDownloadsPageObject;
import actions.pageObject.HeaderMenu.Electronics.ElectronicsPageObject;
import actions.pageObject.HeaderMenu.GiftCards.GiftCardPageObject;
import actions.pageObject.HeaderMenu.Jewery.JewelryPageObject;
import actions.pageObject.MyAccountSideBar.*;
import org.openqa.selenium.WebDriver;
import testdata.CustomerAddressesData;
import testdata.CustomerInfoData;

public class PageGenerator {

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }
    public static HomePageObject getHomePage (WebDriver driver){
        return new HomePageObject(driver);
    }
    public static LoginPageObject getLoginPage (WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static AccountPageObject getAccountPage (WebDriver driver){
        return new AccountPageObject(driver);
    }

    //Account SideBar:
    public static CustomerInfoPageObject getCustomerInfoPage(WebDriver driver){
        return new CustomerInfoPageObject(driver);
    }
    public static AddressesPageObject getAddressesPage (WebDriver driver){
        return new AddressesPageObject(driver);
    }
    public static OrdersPageObject getOrdersPage (WebDriver driver){
        return new OrdersPageObject(driver);
    }
    public static DownloadableProductsPageObject getDownloadableProductsPage(WebDriver driver){
        return new DownloadableProductsPageObject(driver);
    }
    public static BackInStockSubscriptionsPageObject getBackInStockSubscriptionsPage (WebDriver driver){
        return new BackInStockSubscriptionsPageObject(driver);
    }
    public static RewardPointsPageObject getRewardPointsPage (WebDriver driver){
        return new RewardPointsPageObject(driver);
    }
    public static ChangePasswordPageObject getChangePasswordPage (WebDriver driver){
        return new ChangePasswordPageObject(driver);
    }
    public static MyProductReviewsPageObject getMyProductReviewPage (WebDriver driver){
        return new MyProductReviewsPageObject(driver);
    }
    public static MyAccountSideBarPageObject getMyAccountSideBarPage(WebDriver driver){
        return new MyAccountSideBarPageObject(driver);
    }

    //TopMenu:
    public static HeaderMenuPageObject getHeaderMenuPage (WebDriver driver){return new HeaderMenuPageObject(driver);}
    public static ComputerPageObject getComputerPage(WebDriver driver){
        return new ComputerPageObject(driver);
    }
    public static ElectronicsPageObject getElectronicsPage(WebDriver driver){
        return new ElectronicsPageObject(driver);
    }
    public static BooksPageObject getBooks(WebDriver driver){
        return new BooksPageObject(driver);
    }
    public static DigitalDownloadsPageObject getDigitalDownloadsPage (WebDriver driver){
        return new DigitalDownloadsPageObject(driver);
    }
    public static JewelryPageObject getJewelryPage(WebDriver driver){
        return new JewelryPageObject(driver);
    }
    public static GiftCardPageObject getGiftCardPage (WebDriver driver){
        return new GiftCardPageObject(driver);
    }

    //Data Class:

    public static CustomerInfoData getCustomerInfoData (){return new CustomerInfoData();}
    public static CustomerAddressesData getCustomerAddressesData (){return new CustomerAddressesData();
    }

    //Footer:
    public static FooterComponent getFooterComponent (WebDriver driver){return new FooterComponent(driver);}
    public static SearchPageObject getSearchPage(WebDriver driver){return new SearchPageObject(driver);}




}
