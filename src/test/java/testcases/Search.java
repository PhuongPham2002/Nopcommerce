package testcases;

import actions.components.FooterComponent;
import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import actions.pageObject.SearchPageObject;
import com.beust.ah.A;
import commons.BaseTest;
import commons.Common_01_Login;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Search extends BaseTest {
    private HomePageObject homePageObject;
    private FooterComponent footerComponent;
    private SearchPageObject searchPage;

    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);
        homePageObject = PageGenerator.getHomePage(driver);
        homePageObject.setCookies(driver,Common_01_Login.getNopCommerceCookie());
        footerComponent = PageGenerator.getFooterComponent(driver);
        searchPage = footerComponent.clickSearchLink("customer-service","Search");
        //có cần wait cho open link trong hàm click ko ta?
        Assert.assertEquals(searchPage.getPageTitleText(),"Search");
    }

    @Test
    public void Search01_SearchWithEmptyKeyword() {
        searchPage.clickSearchButton();
        searchPage.getEmptyKeywordErrorMessage();
        Assert.assertEquals(searchPage.getEmptyKeywordErrorMessage(),"Search term minimum length is 3 characters");
    }

    @Test
    public void Search02_SearchWithNonExistedKeyword() {
        searchPage.enterKeywordSearch("Macbook Pro 2025");
        searchPage.clickSearchButton();
        Assert.assertEquals(searchPage.getNonExistedKeywordMessage(),"No products were found that matched your criteria.");
        //Check đoạn này xem trong Page Object mà có Text vậy ổn ko?
    }

    @Test
    public void Search03_SearchWithPartialProductName() {
        searchPage.enterKeywordSearch("Lenovo");
        searchPage.clickSearchButton();
        //searchPage.waitUntilProductListSizeEqualTo(2);
        Assert.assertTrue(searchPage.isProductSizeEqualTo(2));
        Assert.assertTrue(searchPage.displayedProductsContain("Lenovo IdeaCentre"));
        Assert.assertTrue(searchPage.displayedProductsContain("Lenovo Thinkpad Carbon Laptop"));
        //Hoi co nen Assert tung cai ko?
    }
    @Test
    public void Search04_SearchWithExactMatchProductName() {
        searchPage.enterKeywordSearch("Lenovo Thinkpad Carbon Laptop");
        searchPage.clickSearchButton();
        //searchPage.waitUntilProductListSizeEqualTo(1);
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Lenovo Thinkpad Carbon Laptop"));
    }

    @Test
    public void Search05_AdvancedSearchWithParentCategory() {
        searchPage.enterKeywordSearch("Apple Macbook Pro");
        searchPage.checkAdvancedSearchCheckbox();
        searchPage.selectCategoryDropdown("Computers");
        searchPage.clickSearchButton();
        Assert.assertEquals(searchPage.getNonExistedKeywordMessage(),"No products were found that matched your criteria.");
    }
    @Test
    public void Search06_AdvancedSearchWithSubCategory() {
        searchPage.enterKeywordSearch("Apple Macbook Pro");
        searchPage.checkAdvancedSearchCheckbox();
        searchPage.selectCategoryDropdown("Computers");
        searchPage.checkSubCategorySearchCheckbox();
        searchPage.clickSearchButton();
        //searchPage.waitUntilProductListSizeEqualTo(1);
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Apple MacBook Pro"));
    }

    @Test
    public void Search07_AdvancedSearchWithIncorrectManufacturer() {
        searchPage.enterKeywordSearch("Apple Macbook Pro");
        searchPage.checkAdvancedSearchCheckbox();
        searchPage.selectCategoryDropdown("Computers");
        searchPage.checkSubCategorySearchCheckbox();
        searchPage.selectManufacturerDropdown("HP");
        searchPage.clickSearchButton();
        Assert.assertEquals(searchPage.getNonExistedKeywordMessage(),"No products were found that matched your criteria.");
    }

    @Test
    public void Search08_AdvancedSearchWithCorrectManufacturer() {
        searchPage.enterKeywordSearch("Apple Macbook Pro");
        searchPage.checkAdvancedSearchCheckbox();
        searchPage.selectCategoryDropdown("Computers");
        searchPage.checkSubCategorySearchCheckbox();
        searchPage.selectManufacturerDropdown("Apple");
        searchPage.clickSearchButton();
        //searchPage.waitUntilProductListSizeEqualTo(1);
        Assert.assertTrue(searchPage.isProductSizeEqualTo(1));
        Assert.assertTrue(searchPage.isDisplayedProductEqualTo("Apple MacBook Pro"));
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
