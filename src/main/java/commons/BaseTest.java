package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.IOException;
import java.util.Random;


/*
BaseTest thường sẽ chứa:
@BeforeMethod / @BeforeClass để setup WebDriver.
@AfterMethod / @AfterClass để quit WebDriver.
Các method hỗ trợ như getDriver(), closeBrowser(), v.v.
 */
public class BaseTest {
    WebDriver driver;
    protected final Logger log;
    public BaseTest(){
        log = LogManager.getLogger(getClass());
    }
    public WebDriver getDriver(){
        return driver;
    }
    public WebDriver getBrowserDriver(String browserName, String url) {
        BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

        switch (browserList){
            case CHROME:
                driver = new ChromeDriver();
                break;
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case EDGE:
                driver = new EdgeDriver();
            case SAFARI:
                driver = new SafariDriver();
            default:
                throw new RuntimeException("Trình duyệt nhập vào không được hỗ trợ "+browserName);
        }
        driver.manage().window().maximize();
        driver.get(url);
        return driver;

    }

    public void closeBrowserDriver(){
        String cmd = null;

        try {
            if (driver != null){
                driver.manage().deleteAllCookies();
                driver.quit();
            }
           String osName = System.getProperty("os.name").toLowerCase();
           String driverInstanceName = driver.toString().toLowerCase();
           String browserDriverName;
           if (driverInstanceName.contains("chrome")){
               browserDriverName = "chromedriver";
           }
           else if (driverInstanceName.contains("firefox")){
               browserDriverName ="geckodriver";
           }
           else if (driverInstanceName.contains("edge")){
               browserDriverName ="edgedriver";
           } else {browserDriverName ="safaridriver";}

           if (osName.contains("window")){
               cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
           } else {
               cmd = "pkill " + browserDriverName;
           }
        }
        catch (Exception exp)
        {System.out.println(exp.getMessage());}//SAU NÀY NHỚ THÊM LOG4J VÀO ĐÂY NHÉ
        finally {
            try {
            Process process =Runtime.getRuntime().exec(cmd);
            process.waitFor();
            }
            catch (IOException e)
            {e.printStackTrace();}
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }



    public int generateRandomNumber() {
        Random rand = new Random();
        int upperLimit = 999;
        int lowerLimit = 9;
        return rand.nextInt(lowerLimit,upperLimit);
    }


    @BeforeSuite
    public void deleteFileInReport() {
        deleteAllFileInFolder("allure-results");
    }

    public void deleteAllFileInFolder(String folderName) {
        try {
            String pathFolderDownload = GlobalConstants.PROJECT_PATH +  File.separator+ folderName;
            File file = new File(pathFolderDownload);
            File[] listOfFiles = file.listFiles();
            if (listOfFiles.length != 0) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile() && !listOfFiles[i].getName().equals("environment.properties")) {
                        new File(listOfFiles[i].toString()).delete();
                    }
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

}
