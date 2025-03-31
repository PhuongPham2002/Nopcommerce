package testcases;

import commons.BaseTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login extends BaseTest {
    @BeforeClass
    public void setupBeforeClassRun() {

    }

    @Test
    public void TC01_Login_EmptyData() {
    }

    @Test
    public void TC02_Login_InvalidEmail() {
    }

    @Test
    public void TC03_Login_NonRegisteredEmail() {
    }

    @Test
    public void TC04_Login_EmptyPassword() {
    }

    @Test
    public void TC05_Login_InvalidPassword() {
    }

    @Test
    public void TC06_Login_ValidInfo() {
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }
}
