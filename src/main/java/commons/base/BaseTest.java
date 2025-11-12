package commons.base;

import commons.helpers.CommonHelper;
import interfaces.enums.BrowserType;
import interfaces.enums.EnvironmentType;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    protected final Logger log;
    public BaseTest() {
        log = LogManager.getLogger(getClass());
    }
    public final static String ENV_NAME = SystemProperties.getProperty("testEnv","dev");
    public final static String BROWSER_NAME = SystemProperties.getProperty("browser","Chrome");
    private final static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getBrowserDriver() {
        String url = getEnvironmentTest();
        BrowserType browser = BrowserType.valueOf(BROWSER_NAME.toUpperCase());
        switch (browser) {
            case CHROME:
                driver.set(new ChromeDriver());
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver());
                break;
            case EDGE:
                driver.set(new EdgeDriver());
                break;
            case SAFARI:
                driver.set(new SafariDriver());
                break;
            default:
                throw new RuntimeException("Invalid browser: " + BROWSER_NAME);
        }
        driver.get().manage().window().maximize();
        driver.get().get(url);
        return driver.get();
    }

    public WebDriver getDriver(){
        return driver.get();
    }

    private String getEnvironmentTest() {
        String url;
        EnvironmentType environment = EnvironmentType.valueOf(ENV_NAME.toUpperCase());
        switch (environment) {
            case DEV:
                url = "https://localhost:59579/";
                break;
            case STAGING:
                url = "https://staging.localhost:59579/";
                break;
            case PRODUCTION:
                url = "https://demo.nopcommerce.com/";
                break;
            default:
                throw new RuntimeException("Invalid Environment :" + ENV_NAME);
        }
        return url;

    }

    public void closeBrowserDriver() {
        String cmd = null;

        try {
            if (driver.get() != null) {
                log.info("Thread: " + Thread.currentThread().getId() + " Closed driver: " + driver.get().hashCode());
                driver.get().manage().deleteAllCookies();
                driver.get().quit();
            }
            String osName = System.getProperty("os.name").toLowerCase();
            String driverInstanceName = driver.get().toString().toLowerCase();
            String browserDriverName;
            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            } else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else if (driverInstanceName.contains("edge")) {
                browserDriverName = "edgedriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("window")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }//SAU NÀY NHỚ THÊM LOG4J VÀO ĐÂY NHÉ
        finally {
            try {
                if (cmd != null && !cmd.isEmpty()) {
                    Process process = Runtime.getRuntime().exec(cmd);
                    process.waitFor();
                } else {
                    log.warn("Skip killing process because command is null");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    @BeforeSuite
    public void deleteFileInReport() {
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        CommonHelper.deleteAllFileInFolder(folderName);
    }

    private final static ThreadLocal<List<Throwable>> errorCollector = ThreadLocal.withInitial(ArrayList::new);

    @Step("VERIFY TRUE  -> {message}")
    public void verifyTrue(boolean condition, String message) {
        try {
            Assert.assertTrue(condition, message);
            log.info("PASSED: " + message);

        } catch (AssertionError e) {
            log.info("FAILED: " + message);
            errorCollector.get().add(e);

        }
    }

    @Step("ASSERT ALL VERIFICATIONS")
    public void assertAll() {
        List<Throwable> errors = errorCollector.get();
        if (!errors.isEmpty()) {
            String combinedMessage = "Errors List:\n";
            for (int i = 0; i < errors.size(); i++) {
                Throwable e = errors.get(i);
                combinedMessage += "- " + e.getMessage() + "\n";
            }
            errors.clear(); // xóa dánh sách lỗi để không ảnh hưởng test case sau
            Assert.fail(combinedMessage);
        } else {
            log.info("No errors found – assertAll PASSED.");
        }
    }

    @Step("VERIFY FALSE  -> {message}")
    public void verifyFalse(boolean condition, String message) {
        try {
            Assert.assertFalse(condition, message);
            log.info("PASSED: " + message);

        } catch (AssertionError e) {
            log.info("FAILED: " + message);
            errorCollector.get().add(e);

        }
    }

    @Step("VERIFY EQUALS  -> {message}")
    public void verifyEquals(String actual, String expected, String message) {
        try {
            Assert.assertEquals(actual, expected, message);
            log.info("[PASSED] " + message + " | Expected: " + expected + " | Actual: " + actual);

        } catch (AssertionError e) {
            log.error("[FAILED] " + message + " | Expected: " + expected + " | Actual: " + actual);
            errorCollector.get().add(e);

        }
    }

}
