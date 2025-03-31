package actions.pageObject;

import org.openqa.selenium.WebDriver;

public class PageGenerator {

    public static RegisterPageObject getRegisterPageObject (WebDriver driver) {
        return new RegisterPageObject(driver);
    }

    public static HomePageObject getHomePageObject (WebDriver driver){
        return new HomePageObject(driver);
    }
    public static LoginPageObject getLoginPageObject (WebDriver driver){
        return new LoginPageObject(driver);
    }

}
