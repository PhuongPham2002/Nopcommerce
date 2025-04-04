package actions.pageObject;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

    public static RegisterPageObject getRegisterPage(WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePage (WebDriver driver){
        return new HomePageObject(driver);
    }
    public static LoginPageObject getLoginPage (WebDriver driver){
        return new LoginPageObject(driver);
    }
    public static AccountPageObject getAccountPage (WebDriver driver){
        return new AccountPageObject(driver);
    }


}
