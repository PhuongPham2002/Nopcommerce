package actions.pageObject;

import actions.components.DisplayProductComponent;
import actions.components.Header.HeaderComponent;
import actions.components.NotificationBarComponent;
import actions.components.SortProductComponent;
import commons.base.BasePage;
import commons.helpers.WaitHelper;
import interfaces.pageUI.NotebooksPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotebooksPageObject extends BasePage {
    WebDriver driver;
    public SortProductComponent sort;
    public DisplayProductComponent display;
    public HeaderComponent header;
    public NotificationBarComponent notificationBar;

    public final static String SUCCESSFUL_MESSAGE_ADD_TO_COMPARE ="The product has been added to your product comparison";
    public NotebooksPageObject(WebDriver driver) {
        this.driver = driver;
        this.sort = new SortProductComponent(driver);
        this.display = new DisplayProductComponent(driver);
        this.notificationBar= new NotificationBarComponent(driver);
        this.header = new HeaderComponent(driver);

    }
    public void sortProductNameByAscending(String sortOption) {
        sort.selectSortOption(sortOption);
    }
    public boolean isProductNameSortedByAscending() {
        waitForListElementsVisible(driver, interfaces.pageUI.NotebooksPageUI.PRODUCT_TITLE_TEXT);
        List<WebElement> allNotebooksProduct = getListElement(driver, interfaces.pageUI.NotebooksPageUI.PRODUCT_TITLE_TEXT);
        List<String> allNotebooksProductName = new ArrayList<>();
        for (WebElement notebookProduct:allNotebooksProduct){
            allNotebooksProductName.add(notebookProduct.getText());}
        List<String> tobeSortAllNotebooksProductName = new ArrayList<>(allNotebooksProductName);
        tobeSortAllNotebooksProductName.sort(null);
        return tobeSortAllNotebooksProductName.equals(allNotebooksProductName);
    }


    public boolean isProductNameSortedByDescending() {
        waitForListElementsVisible(driver, interfaces.pageUI.NotebooksPageUI.PRODUCT_TITLE_TEXT);
        List<WebElement> allNotebooksProduct = getListElement(driver, interfaces.pageUI.NotebooksPageUI.PRODUCT_TITLE_TEXT);
        List<String> allNotebooksProductName = new ArrayList<>();
        for (WebElement notebookProduct:allNotebooksProduct){
            allNotebooksProductName.add(notebookProduct.getText());}
        List<String> tobeSortAllNotebooksProductName = new ArrayList<>(allNotebooksProductName);
        Collections.sort(tobeSortAllNotebooksProductName);
        Collections.reverse(tobeSortAllNotebooksProductName);
        return tobeSortAllNotebooksProductName.equals(allNotebooksProductName);
    }

    public boolean isProductPriceSortedByAscending() {
        waitForListElementsVisible(driver, NotebooksPageUI.DYNAMIC_PRODUCT_PRICE_TEXT);
        List<WebElement> allNotebooksProduct = getListElement(driver, NotebooksPageUI.DYNAMIC_PRODUCT_PRICE_TEXT);
        List<String> allNotebooksProductPrice = new ArrayList<>();
        for (WebElement notebookProduct:allNotebooksProduct){
            allNotebooksProductPrice.add(notebookProduct.getText().substring(1));}
        log.info("Danh sách chưa sort: " +allNotebooksProductPrice);
        List<String> tobeSortAllNotebooksProductName = new ArrayList<>(allNotebooksProductPrice);
        Collections.sort(tobeSortAllNotebooksProductName);
        log.info("Danh sách sau khi sort: "+ tobeSortAllNotebooksProductName);
        return tobeSortAllNotebooksProductName.equals(allNotebooksProductPrice);



    }

    public boolean isProductPriceSortedByDescending() {
        waitForListElementsVisible(driver, NotebooksPageUI.DYNAMIC_PRODUCT_PRICE_TEXT);
        List<WebElement> allNotebooksProduct = getListElement(driver, NotebooksPageUI.DYNAMIC_PRODUCT_PRICE_TEXT);
        List<String> allNotebooksProductPrice = new ArrayList<>();
        for (WebElement notebookProduct:allNotebooksProduct){
            allNotebooksProductPrice.add(notebookProduct.getText().substring(1));}
        log.info("Danh sách chưa sort: " +allNotebooksProductPrice);
        List<String> tobeSortAllNotebooksProductName = new ArrayList<>(allNotebooksProductPrice);
        Collections.sort(tobeSortAllNotebooksProductName);
        log.info("Danh sách sau khi sort: "+ tobeSortAllNotebooksProductName);
        Collections.reverse(tobeSortAllNotebooksProductName);
        log.info("Danh sách sort theo chiều ngược lại: "+tobeSortAllNotebooksProductName);
        return tobeSortAllNotebooksProductName.equals(allNotebooksProductPrice);
    }

    public boolean isProductListSizeEqualTo (int numberOfDisplayedProducts) {
        //waitForListElementsVisible(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT);
        //SAU NÀY IMPLEMENT THÊM FLUENT WAIT SAU CHO LAYOUT (GRID CO LẠI HOÀN TOÀN)
        waitForNumberOfElementsTobe(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT,numberOfDisplayedProducts);
        log.info("Số lượng sản phẩm hiển thị: "+ getListElementsSize(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT));
        return getListElementsSize(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT) == numberOfDisplayedProducts;
    }

    public boolean isProductListSizeAtMost (int expectedSize){
        waitForListElementsVisible(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT);
        log.info("Số lượng sản phẩm hiển thị: "+ getListElementsSize(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT));
        return getListElementsSize(driver,NotebooksPageUI.PRODUCT_TITLE_TEXT) <= expectedSize;
    }

    public boolean isPaginationNextIconDisplayed() {
        return getListElementsSize(driver,NotebooksPageUI.PAGINATION_NEXT_ICON)>0 && isElementDisplayed(driver,NotebooksPageUI.PAGINATION_NEXT_ICON);
    }

    public boolean isPaginationAtPageOne(int pageNumber) {
        getElementText(driver,NotebooksPageUI.CURRENT_PAGE,String.valueOf(pageNumber));
        return getElementText(driver,NotebooksPageUI.CURRENT_PAGE,String.valueOf(pageNumber)).equals(String.valueOf(pageNumber));

    }

    public void clickPageNumber(int pageNumber) {
        waitForElementClickable(driver,NotebooksPageUI.DYNAMIC_PAGINATION_PAGE_BY_NUMBER,String.valueOf(pageNumber));
        clickElement(driver,NotebooksPageUI.DYNAMIC_PAGINATION_PAGE_BY_NUMBER,String.valueOf(pageNumber));
        waitForLoadingScreenInvisible(driver);
    }


    public boolean isPaginationPreviousIconDisplayed() {
        waitForElementVisible(driver,NotebooksPageUI.PAGINATION_PREVIOUS_ICON);
        return isElementDisplayed(driver,NotebooksPageUI.PAGINATION_PREVIOUS_ICON);
    }


    public void selectProductPerPageDropdown(int displayOption) {
        display.selectProductsDisplayOption(displayOption);
        waitForLoadingScreenInvisible(driver);

    }

    public ProductDetailPageObject clickProduct(String productName) {
        waitForLoadingIconInvisible(driver);
        waitForElementClickable(driver,NotebooksPageUI.DYNAMIC_PRODUCT_TITLE,productName);
        clickElement(driver,NotebooksPageUI.DYNAMIC_PRODUCT_TITLE,productName);
        return PageGenerator.getProductDetailPage(driver);
    }

    public void clickAddProductToCompareListButton(String productName) {
        waitForLoadingIconInvisible(driver);
        waitForElementClickable(driver,NotebooksPageUI.DYNAMIC_ADD_TO_COMPARE_BUTTON,productName);
        clickElement(driver,NotebooksPageUI.DYNAMIC_ADD_TO_COMPARE_BUTTON,productName);
    }

    public String getSuccessfulMessageForAddingProductsToComparisonList() {
        waitForLoadingIconInvisible(driver);
        waitForTextToBePresentInElement(driver,NotebooksPageUI.SUCCESSFUL_MESSAGE_ADD_TO_COMPARE,SUCCESSFUL_MESSAGE_ADD_TO_COMPARE);
        return getElementText(driver,NotebooksPageUI.SUCCESSFUL_MESSAGE_ADD_TO_COMPARE);
    }

    public void clickAddToCartButton(String productName) {
        waitForElementClickable(driver,NotebooksPageUI.DYNAMIC_ADD_TO_CART_BUTTON,productName);
        clickElement(driver,NotebooksPageUI.DYNAMIC_ADD_TO_CART_BUTTON,productName);
    }

    public ShoppingCartPageObject clickShoppingCartLinkFromHeader() {
        WaitHelper.waitForLoadingIconInvisible(driver);
        notificationBar.closeNotification();
        header.account.clickShoppingCartLink();
        return PageGenerator.getShoppingCartPage(driver);
    }
}

