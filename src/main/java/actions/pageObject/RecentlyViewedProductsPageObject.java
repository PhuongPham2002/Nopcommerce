package actions.pageObject;

import commons.base.BasePage;
import interfaces.pageUI.RecentlyViewedProductsPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecentlyViewedProductsPageObject extends BasePage {
    WebDriver driver;

    public RecentlyViewedProductsPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isRecentlyViewedProductsMatched(Set<String> expectedProductNames) {
        waitForLoadingIconInvisible(driver);
        waitForNumberOfElementsTobe(driver, RecentlyViewedProductsPageUI.PRODUCT_NAME,3);
        waitForListElementsVisible(driver,RecentlyViewedProductsPageUI.PRODUCT_NAME);
        List<WebElement> listElement = getListElement(driver,RecentlyViewedProductsPageUI.PRODUCT_NAME);
        Set<String> productNames = new HashSet<>();
        for (WebElement element:listElement){
            productNames.add(element.getText().trim());
        }
        return productNames.equals(expectedProductNames);
    }
}
