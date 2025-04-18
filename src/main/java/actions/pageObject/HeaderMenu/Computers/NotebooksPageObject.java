package actions.pageObject.HeaderMenu.Computers;

import actions.components.DisplayProductComponent;
import actions.components.SortProductComponent;
import commons.BasePage;
import interfaces.pageUI.NotebooksPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotebooksPageObject extends BasePage {
    WebDriver driver;
    public SortProductComponent sort;
    public DisplayProductComponent display;
    public NotebooksPageObject(WebDriver driver) {
        this.driver = driver;
        this.sort = new SortProductComponent(driver);
        this.display = new DisplayProductComponent(driver);

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
}

