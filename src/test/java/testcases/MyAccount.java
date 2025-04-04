//nên set cookie theo hướng nào

package testcases;
import actions.pageObject.AddressesPageObject;
import actions.pageObject.CustomerInfoPageObject;
import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import commons.BaseTest;
import commons.Common_01_Login;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import testdata.CustomerAddressesData;

public class MyAccount extends BaseTest {
    private HomePageObject homePage;
    private CustomerInfoPageObject customerInfoPage;
    private AddressesPageObject addressesPage;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        WebDriver driver;
        driver = getBrowserDriver(browser,url);

        homePage= PageGenerator.getHomePage(driver);
        homePage.setCookies(driver,Common_01_Login.getNopCommerceCookie());
//        customerInfoPage=homePage.clickMyAccountLink();
    }

    @Test
    public void MyAccount_01_UpdateCustomerInformation() {
        customerInfoPage.checkGenderRadio("Male");
        customerInfoPage.updateFirstNameTextbox();
        customerInfoPage.updateLastNameTextbox();
        customerInfoPage.updateEmailTextbox();
        customerInfoPage.updateCompanyNameTextbox();
        customerInfoPage.clickSaveButton();
    }

    @Test
    public void MyAccount_02_UpdateAddressInformation() {
        addressesPage=customerInfoPage.clickAddressLink();
        addressesPage.enterTextboxByID();



    }

    @Test
    public void MyAccount_03_ChangePassword() {
    }


    @Test
    public void MyAccount_04_AddProductReviews() {

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
