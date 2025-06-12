
package testcases;
import actions.components.MyAccountSideBar.*;
import actions.pageObject.*;
import actions.pageObject.ComputerPageObject;
import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import commons.helpers.RegisterLoginHelper;
import commons.helpers.CommonHelper;
import data.helpers.RegisterDataHelper;
import dataObjects.RegisterTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import dataObjects.CustomerAddressesData;
import dataObjects.CustomerInfoData;

public class MyAccountTests extends BaseTest {
    WebDriver driver;
    private HomePageObject homePage;
    private CustomerInfoPageObject customerInfoPage;
    private AddressesPageObject addressesPage;
    private CustomerAddressesData customerAddressesData;
    private ChangePasswordPageObject changePasswordPage;
    private MyAccountSideBarPageObject myAccountSideBarPage;
    private LoginPageObject loginPageObject;
    private MyProductReviewsPageObject myProductReviewsPage;
    private ComputerPageObject computerPage;
    private CustomerInfoData customerInfoData;
    @BeforeClass
    @Parameters({"browser","url"})
    public void setupBeforeClassRun(String browser, String url) {
        driver = getBrowserDriver(browser,url);
        CommonHelper.setCookies(driver, RegisterLoginHelper.getNopCommerceCookie());


        //Customer Data
        customerInfoData = PageGenerator.getCustomerInfoData();
        customerInfoData.setFirstName("Automation");
        customerInfoData.setLastName("FC");
        customerInfoData.setCityName("Ha Noi");
        customerInfoData.setCompanyName("Automation FC");
        customerInfoData.setEmailAddress("automationfc"+generateRandomNumber()+"@gmail.com");
        customerInfoData.setGender("Female");
        customerInfoData.setNewPassword("3456789012");

        //Address Data
        customerAddressesData = PageGenerator.getCustomerAddressesData();
        customerAddressesData.setFirstName("Automation");
        customerAddressesData.setLastName("FC");
        customerAddressesData.setEmailAddress("AutomationFC@gmail.com");
        customerAddressesData.setCompanyName("AutomationFC");
        customerAddressesData.setCountryName("Vietnam");
        customerAddressesData.setState_province("Hà Nội");
        customerAddressesData.setCityName("Da Nang");
        customerAddressesData.setAddress1("123/40 Le Lai");
        customerAddressesData.setAddress2("234/05 Hai Phong");
        customerAddressesData.setPostalCode("550000");
        customerAddressesData.setPhoneNumber("0123456789");
        customerAddressesData.setFaxNumber("0987654321");
        myAccountSideBarPage = homePage.clickMyAccountLink();
        customerInfoPage = (CustomerInfoPageObject) myAccountSideBarPage.navigateToMyAccountSideBarMenu("Customer info");
    }

    //@Test
    public void MyAccount_01_UpdateCustomerInformation() {
        customerInfoPage.updateCustomerInformation(customerInfoData.getFirstName(),customerInfoData.getLastName(),customerInfoData.getEmailAddress(),
                customerInfoData.getCompanyName(),customerInfoData.getGender());
        customerInfoPage.assertUpdatedCustomerInfo(customerInfoData.getFirstName(),customerInfoData.getLastName(),customerInfoData.getEmailAddress(),
                customerInfoData.getCompanyName(),customerInfoData.getGender());}

   //@Test
    public void MyAccount_02_UpdateAddressInformation() {
        //addressesPage=(AddressesPageObject) customerInfoPage.navigateToMyAccountSideBarMenu("Addresses");
        addressesPage.clickAddNewButton();
        addressesPage.updateCustomerAddresses(customerAddressesData);
        Assert.assertEquals(addressesPage.getAddingInfoSuccessfulMessage(),"The new address has been added successfully.");
        addressesPage.assertAddressesInfoAfterAdding(customerAddressesData);
    }

    //@Test
    public void MyAccount_03_ChangePassword() {
        changePasswordPage= (ChangePasswordPageObject) myAccountSideBarPage.navigateToMyAccountSideBarMenu("Change password");
        changePasswordPage.changePassword(RegisterDataHelper.PASSWORD,customerInfoData.getNewPassword());
        Assert.assertEquals(changePasswordPage.getSuccessfulChangePasswordMessage(),"Password was changed");
        changePasswordPage.closeUpdateSuccessfulMessage();
        homePage=changePasswordPage.clickLogoutButton();
        loginPageObject = homePage.clickLoginLink();
        //Nhập với password cũ:
        loginPageObject.enterLoginForm(customerInfoData.getEmailAddress(), RegisterDataHelper.PASSWORD);
        loginPageObject.clickLoginButton();
        Assert.assertEquals(loginPageObject.getWrongPasswordErrorMessage(),"Login was unsuccessful. Please correct the errors and try again.\n" + "The credentials provided are incorrect");

        //Nhập với password mới:
        loginPageObject.enterLoginForm(customerInfoData.getEmailAddress(),customerInfoData.getNewPassword());
        homePage =loginPageObject.clickLoginButton();
    }


    //@Test
    public void MyAccount_04_AddProductReviews() {
        myProductReviewsPage = (MyProductReviewsPageObject) myAccountSideBarPage.navigateToMyAccountSideBarMenu("My product reviews");
        myProductReviewsPage.assertReviewProductItemExisted();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
