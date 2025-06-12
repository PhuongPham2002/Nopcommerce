package actions.pageObject;

import actions.components.Footer.FooterComponent;

import actions.components.MyAccountSideBar.*;
import org.openqa.selenium.WebDriver;
import dataObjects.CustomerAddressesData;
import dataObjects.CustomerInfoData;

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
    public static ProductDetailPageObject getProductDetailPage(WebDriver driver){
        return new ProductDetailPageObject(driver);
    }

    //Data Class:

    public static CustomerInfoData getCustomerInfoData (){return new CustomerInfoData();}
    public static CustomerAddressesData getCustomerAddressesData (){return new CustomerAddressesData();
    }

    //Footer:
    public static FooterComponent getFooterComponent (WebDriver driver){return new FooterComponent(driver);}
    public static SearchPageObject getSearchPage(WebDriver driver){return new SearchPageObject(driver);}


    public static WishListPageObject getWishListPage (WebDriver driver){
        return new WishListPageObject(driver);
    }

    public static ShoppingCartPageObject getShoppingCartPage (WebDriver driver){
        return new ShoppingCartPageObject(driver);
    }

    public static NotebooksPageObject getNoteBooksPage(WebDriver driver){
        return new NotebooksPageObject(driver);

    }
    public static CompareProductsListPageObject getCompareProductsListPage(WebDriver driver){
        return new CompareProductsListPageObject(driver);
    }
    public static RecentlyViewedProductsPageObject getRecentlyReviewProduct(WebDriver driver){
        return new RecentlyViewedProductsPageObject(driver);
    }

    //Product Category Header
    public static DesktopsPageObject getDesktopsPage (WebDriver driver){
        return new DesktopsPageObject(driver);
    }

    public static ComputerPageObject getComputersPage (WebDriver driver){
        return new ComputerPageObject(driver);
    }

    public static ElectronicsPageObject getElectronicsPage (WebDriver driver){
        return new ElectronicsPageObject(driver);
    }

    public static ApparelPageObject getApparelPage (WebDriver driver){
        return new ApparelPageObject(driver);
    }

    public static DigitalDownloadsPageObject getDigitalDownloadsPage (WebDriver driver){
        return new DigitalDownloadsPageObject(driver);
    }
    public static BooksPageObject getBooksPage (WebDriver driver){
        return new BooksPageObject(driver);
    }

    public static JewelryPageObject getJewelryPage (WebDriver driver){
        return new JewelryPageObject(driver);
    }

    public static GiftCardPageObject getGiftCardPage (WebDriver driver){
        return new GiftCardPageObject(driver);
    }

    public static EstimateShippingPopupPageObject getEstimateShippingPopupPage (WebDriver driver){
        return new EstimateShippingPopupPageObject(driver);
    }

    public static CheckoutPageObject getCheckoutPage(WebDriver driver){
        return new CheckoutPageObject(driver);
    }




}
