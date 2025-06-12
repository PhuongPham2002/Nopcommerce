package actions.components.MyAccountSideBar;
import interfaces.componentUI.myAccountSideBar.AddressesPageUI;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import dataObjects.CustomerAddressesData;

import java.util.ArrayList;
import java.util.List;

public class AddressesPageObject extends MyAccountSideBarPageObject {
    WebDriver driver;
    public AddressesPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

    public void updateCustomerAddresses(CustomerAddressesData customerAddressesdata){
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"FirstName",customerAddressesdata.getFirstName());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"LastName",customerAddressesdata.getLastName());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"Email",customerAddressesdata.getEmailAddress());
        enterTextboxByID(driver,AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"Company",customerAddressesdata.getCompanyName());;
        selectDropdownOptionByID("CountryId",customerAddressesdata.getCountryName());
        selectDropdownOptionByID("StateProvinceId",customerAddressesdata.getState_province());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"City",customerAddressesdata.getCityName());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"Address1",customerAddressesdata.getAddress1());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"Address2",customerAddressesdata.getAddress2());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"ZipPostalCode",customerAddressesdata.getPostalCode());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"PhoneNumber",customerAddressesdata.getPhoneNumber());
        enterTextboxByID(driver, AddressesPageUI.ADDRESSES_TEXTBOX_BY_ID,"FaxNumber",customerAddressesdata.getFaxNumber());
        clickSaveButton();
    }

    public void assertAddressesInfoAfterAdding(CustomerAddressesData customerAddressesData){
       List<WebElement> allAddressesInfo= getListElement(driver,AddressesPageUI.LIST_ADDRESSES_INFO_TEXT);
        List<String> allActualValue = new ArrayList<>();

        for (WebElement addressesInfo:allAddressesInfo){
           String actualValue = addressesInfo.getText().trim();
           allActualValue.add(actualValue);
       }
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getFirstName()+" "+customerAddressesData.getLastName()));
        Assert.assertTrue(allActualValue.contains("Phone number: " + customerAddressesData.getPhoneNumber()));
        Assert.assertTrue(allActualValue.contains("Fax number: "+customerAddressesData.getFaxNumber()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getCompanyName()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getAddress2()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getAddress1()));
        Assert.assertTrue(allActualValue.contains("Email: "+ customerAddressesData.getEmailAddress()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getCityName()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getPostalCode()));
        Assert.assertTrue(allActualValue.contains(customerAddressesData.getState_province()));
    }


    public String getAddingInfoSuccessfulMessage() {
        waitForElementVisible(driver,AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
        return getElementText(driver,AddressesPageUI.ADDRESSES_INFO_SUCCESSFUL_UPDATE);
    }

    public void clickAddNewButton() {
        waitForElementClickable(driver,AddressesPageUI.ADD_NEW_BUTTON);
        clickElement(driver,AddressesPageUI.ADD_NEW_BUTTON);
    }
    public void selectDropdownOptionByID(String idDropdownValue, String option){
        waitForElementClickable(driver, AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
        selectDropdownOption(driver,option, AddressesPageUI.ADDRESSES_DROPDOWN_BY_ID, idDropdownValue);
    }
    public void clickSaveButton(){
        waitForElementClickable(driver,AddressesPageUI.SAVE_BUTTON);
        clickElement(driver,AddressesPageUI.SAVE_BUTTON);
    }
}
