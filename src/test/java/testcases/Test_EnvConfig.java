package testcases;

import actions.pageObject.PageGenerator;
import commons.base.BaseTest;
import commons.helpers.PropertiesConfig;
import org.apache.xmlbeans.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test_EnvConfig extends BaseTest {
    @BeforeClass
    @Parameters({"browser"})
    public void setupBeforeClassRun(String browser) {
        WebDriver driver;
        //driver = getBrowserDriverTest(browser);

    }

    @Test
    public void Test1(){

    }

    @Test
    public void Test2(){

    }

    @Test
    public void Test3(){

    }
    @AfterClass(alwaysRun = true)
    public void tearDown() {
        closeBrowserDriver();
    }

}
