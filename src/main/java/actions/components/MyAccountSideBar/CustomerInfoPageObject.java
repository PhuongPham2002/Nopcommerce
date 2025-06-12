package actions.components.MyAccountSideBar;

import commons.base.BasePage;
import interfaces.componentUI.myAccountSideBar.CustomerInfoPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CustomerInfoPageObject extends BasePage {
    WebDriver driver;

    public CustomerInfoPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void updateCustomerInformation(String firstName, String lastName,String emailAddress,String companyName,String gender){

        checkGenderRadio(gender.toLowerCase());
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"FirstName",firstName);
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"LastName",lastName);
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"Email",emailAddress);
        enterTextboxByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"Company",companyName);

        clickSaveButton();
    }

    public void checkGenderRadio(String gender) {
        waitForElementVisible(driver, CustomerInfoPageUI.GENDER_RADIO,gender);
        checkCheckboxOrRadio(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
    }
    public String getGenderValue(String gender){
        waitForElementVisible(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
        return getElementText(driver,CustomerInfoPageUI.GENDER_RADIO,gender);
    }

    public void clickSaveButton() {
        waitForElementClickable(driver,CustomerInfoPageUI.SAVE_BUTTON);
        clickElement(driver,CustomerInfoPageUI.SAVE_BUTTON);
    }
    public void assertUpdatedCustomerInfo(String firstName,String lastName,String emailAddress,String companyName,String gender){
        Assert.assertEquals(getGenderValue("female"),gender);
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"FirstName","value"),firstName);
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"LastName","value"),lastName);
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"Email","value"),emailAddress);
        Assert.assertEquals(getAttributeValueByID(driver,CustomerInfoPageUI.FIELD_TEXTBOX_BY_ID,"Company","value"),companyName);

    }

}
