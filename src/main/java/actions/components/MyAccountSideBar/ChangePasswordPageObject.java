package actions.components.MyAccountSideBar;

import actions.pageObject.HomePageObject;
import actions.pageObject.PageGenerator;
import interfaces.componentUI.myAccountSideBar.ChangePasswordPageUI;
import org.openqa.selenium.WebDriver;

public class ChangePasswordPageObject extends MyAccountSideBarPageObject {
    WebDriver driver;
    public ChangePasswordPageObject (WebDriver driver){
        super(driver);
        this.driver = driver;
    }
    public void clickChangePasswordButton() {
        waitForElementClickable(driver,ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
        clickElement(driver,ChangePasswordPageUI.CHANGE_PASSWORD_BUTTON);
    }
    
    public void changePassword(String oldPassword, String password) {
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,"OldPassword",oldPassword);
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,"NewPassword",password);
        enterTextboxByID(driver,ChangePasswordPageUI.PASSWORD_TEXTBOX_BY_ID,"ConfirmNewPassword",password);
        clickChangePasswordButton();

    }
    
    public String getSuccessfulChangePasswordMessage() {
        waitForElementVisible(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
        return getElementText(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE);
    }

    public HomePageObject clickLogoutButton() {
        waitForElementClickable(driver,ChangePasswordPageUI.LOGOUT_LINK);
        clickElement(driver,ChangePasswordPageUI.LOGOUT_LINK);
        return PageGenerator.getHomePage(driver);

    }

    public void closeUpdateSuccessfulMessage() {
        waitForElementVisible(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
        clickElement(driver,ChangePasswordPageUI.CHANGE_PASSWORD_SUCCESSFUL_MESSAGE_CLOSE_BUTTON);
    }
}
