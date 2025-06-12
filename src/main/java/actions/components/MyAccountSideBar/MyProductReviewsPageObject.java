package actions.components.MyAccountSideBar;

import interfaces.componentUI.myAccountSideBar.MyProductReviewsPageUI;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

@Slf4j
public class MyProductReviewsPageObject extends MyAccountSideBarPageObject {
    WebDriver driver;

    public MyProductReviewsPageObject(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }

    public void assertReviewProductItemExisted() {
        //waitForListElementsVisible(driver,MyProductReviewsPageUI.PRODUCT_ITEM_REVIEW_TITLE_TEXT);
        int size = getListElementsSize(driver,MyProductReviewsPageUI.PRODUCT_ITEM_REVIEW_TITLE_TEXT);
        log.info("Số lượng element: " +size);
        Assert.assertTrue(size>=1);
    }
}
