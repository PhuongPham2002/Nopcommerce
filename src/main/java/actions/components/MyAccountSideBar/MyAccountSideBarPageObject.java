package actions.components.MyAccountSideBar;
import actions.pageObject.PageGenerator;
import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.MyAccountSideBarPageUI;
import org.apache.logging.log4j.core.config.Order;
import org.openqa.selenium.WebDriver;

public class MyAccountSideBarPageObject extends BasePage {
    WebDriver driver;
    CustomerInfoComponent customerInfo;
    AddressesComponent addresses;
    BackInStockSubscriptionsComponent backInStockSubscriptions;
    ChangePasswordComponent changePassword;
    DownloadableProductsComponent downloadableProducts;
    MyProductReviewsComponent myProductReviews;
    OrdersComponent order;
    RewardPointsComponent rewardPoints;


    public MyAccountSideBarPageObject(WebDriver driver){
        this.driver = driver;
        customerInfo= new CustomerInfoComponent(driver);
        addresses = new AddressesComponent(driver);
        backInStockSubscriptions = new BackInStockSubscriptionsComponent(driver);
        changePassword = new ChangePasswordComponent(driver);
        downloadableProducts = new DownloadableProductsComponent(driver);
        myProductReviews = new MyProductReviewsComponent(driver);
        order = new OrdersComponent(driver);
        rewardPoints = new RewardPointsComponent(driver);
    }
    public String getMyAccountSideBarItemPageTitle(String pageName){
        waitForElementVisible(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_ITEM_PAGE_TITLE,pageName);
        return getElementText(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_ITEM_PAGE_TITLE,pageName);
    }
    public boolean isMyAccountSideBarItemActive(String pageName){
        waitForElementVisible(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_ITEM_ACTIVE);
        String activePageName = getElementText(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_ITEM_ACTIVE);
        return activePageName.equalsIgnoreCase(pageName);
    }

    public BasePage navigateToMyAccountSideBarMenu (String pageName){
        waitForElementClickable(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_MENU_BY_TEXT,pageName);
        if (!isMyAccountSideBarItemActive(pageName)){
            clickElement(driver,MyAccountSideBarPageUI.MY_ACCOUNT_SIDEBAR_MENU_BY_TEXT,pageName);
        }
        switch (pageName.toLowerCase()){
            case "customer info":
                return PageGenerator.getCustomerInfoPage(driver);
            case "addresses":
                return PageGenerator.getAddressesPage(driver);
            case "orders":
                return PageGenerator.getOrdersPage(driver);
            case "downloadable products":
                return PageGenerator.getDownloadableProductsPage(driver);
            case "reward points":
                return PageGenerator.getRewardPointsPage(driver);
            case "change password":
                return PageGenerator.getChangePasswordPage(driver);
            case "my product reviews":
                return PageGenerator.getMyProductReviewPage(driver);
            default:
                throw new RuntimeException("Page name is not valid");
        }


    }

}
