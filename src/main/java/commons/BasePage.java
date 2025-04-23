package commons;

import interfaces.componentUI.DisplayProductsComponentUI;
import interfaces.pageUI.BasePageUI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.WatchEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BasePage {
    protected final Logger log = LogManager.getLogger(getClass());;

    public String formatLocator(String dynamicLocatorTemplate, String... dynamicParts ){
        return String.format(dynamicLocatorTemplate,(Object[]) dynamicParts);
    }

    public By getByLocator(String rawLocator) {
        if (rawLocator.toLowerCase().startsWith("xpath")) {
            return By.xpath(rawLocator.substring(6));
        } else if (rawLocator.toLowerCase().startsWith("css")) {
            return By.cssSelector(rawLocator.substring(4));
        } else if (rawLocator.toLowerCase().startsWith("id")) {
            return By.id(rawLocator.substring(3));
        } else if (rawLocator.toLowerCase().startsWith("name")) {
            return By.name(rawLocator.substring(5));
        } else if (rawLocator.toLowerCase().startsWith("tagname")) {
            return By.tagName(rawLocator.substring(8));
        }
        throw new IllegalArgumentException("Raw Locator is not valid: "+ rawLocator);
    }
    public WebElement getElement (WebDriver driver, String rawLocator) {
        return driver.findElement(getByLocator(rawLocator));
    }

    public WebElement getElement (WebDriver driver, String dynamicLocatorTemplate,String...dynamicParts) {
        return driver.findElement(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts)));
    }

    public List<WebElement> getListElement (WebDriver driver, String rawLocator){
        return driver.findElements(getByLocator(rawLocator));
    }
    public List<WebElement> getListElement (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        return driver.findElements(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts)));
    }
    public void clickElementWithLogging(WebDriver driver, String locatorForLog){
        try{
            log.info("Attempting to click element with locator: "+locatorForLog);
            getElement(driver,locatorForLog).click();
            log.info("Element clicked successfully.");
        } catch (NoSuchElementException e){
            log.error("Element not found: "+ locatorForLog);
            throw new RuntimeException("Element not found at locator : "+locatorForLog,e);
        } catch (ElementClickInterceptedException e) {
            log.error("Element is being obscured or blocked :"+ locatorForLog);
            throw new RuntimeException("Unable to click element because other element obscured it");
        } catch (ElementNotInteractableException e){
            log.error("Element is not interactable (possibly hidden or disabled: "+ locatorForLog);
            throw new RuntimeException("Element is not interactable: "+ locatorForLog,e);
        } catch (StaleElementReferenceException e){
            log.error("Element became stale before clicking");
            throw new RuntimeException("Stale element during click: "+ locatorForLog,e);
        } catch (Exception e){
            log.error("Unexpected error occurred while trying to click: " + locatorForLog);
            log.error("Exception Message: "+e.getMessage());
            throw new RuntimeException("Unexpected error during click: "+locatorForLog,e);
        }

    }
    public void clickElement(WebDriver driver, String rawLocator){
        clickElementWithLogging(driver,rawLocator);
    }
    public void clickElement(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        String locator = formatLocator(dynamicLocatorTemplate,dynamicParts);
        clickElementWithLogging(driver,locator);
    }
    public void sendKeyToElement(WebDriver driver, String rawLocator, String valueToSend){
        getElement(driver,rawLocator).clear();
        getElement(driver,rawLocator).sendKeys(valueToSend);
        getElement(driver,rawLocator).sendKeys(Keys.TAB);
    }
    public void sendKeyToElement(WebDriver driver,String dynamicLocatorTemplate,String valueToSend,String...dynamicParts){
        getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).clear();
        getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).sendKeys(valueToSend);

    }

    public String getElementText(WebDriver driver, String rawLocator){
        return getElement(driver,rawLocator).getText();
    }
    public String getElementText(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).getText();
    }

    public String getAttributeValue(WebDriver driver, String rawLocator, String attributeName){
        return getElement(driver,rawLocator).getDomProperty(attributeName);
    }

    public String getAttributeValue(WebDriver driver,String dynamicLocatorTemplate,String attributeName,String... dynamicParts){
        return getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).getDomProperty(attributeName);
    }
    public int getListElementsSize(WebDriver driver, String rawLocator){
        return getListElement(driver,rawLocator).size();
    }
    public int getListElementsSize(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return getListElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).size();
    }

    public void selectDropdownOption(WebDriver driver, String rawLocator, String option){
            new Select(getElement(driver,rawLocator)).selectByVisibleText(option);

    }
    public void selectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
        new Select(getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts))).selectByVisibleText(option);
    }

    //public void selectMultipleDropdownOptions (WebDriver driver, String locator, String option)  --> khi làm làm đến dynamic locator thì mình apply
    public void deselectDropdownOption(WebDriver driver, String rawLocator,String option){
        new Select(getElement(driver,rawLocator)).deselectByVisibleText(option);
    }
    public void deselectDropdownOption(WebDriver driver,String option, String dynamicLocatorTemplate,String... dynamicParts){
        new Select(getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts))).deselectByVisibleText(option);
    }

    public String getSelectedDropdownOption(WebDriver driver, String rawLocator){
        return new Select(getElement(driver,rawLocator)).getFirstSelectedOption().getText();
    }

    public String getSelectedDropdownOption(WebDriver driver, String dynamicLocatorTemplate,String... dynamicParts){
        return new Select(getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts))).getFirstSelectedOption().getText();
    }
    public List<String> getAllSelectedDropdownOptions(WebDriver driver, String locator){
        List <WebElement> allSelectedOptions =  new Select(getElement(driver,locator)).getAllSelectedOptions();
        List <String> selectedOptions = new ArrayList<String>();
        for (WebElement option: allSelectedOptions){
            selectedOptions.add(option.getText());
        }
        return selectedOptions;
    }
    public List<String> getOptions(WebDriver driver, String locator){
        List<WebElement> allDropdownOptions = new Select(getElement(driver,locator)).getOptions();
        List<String> dropdownOptions = new ArrayList<String>();
        for (WebElement option: allDropdownOptions){
            dropdownOptions.add(option.getText());
        }
        return dropdownOptions;
    }
    public boolean isDropdownMultiple(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).isMultiple();
    }
    public void selectCustomizedDropdownOptions (WebDriver driver, String DropdownIconLocator, String optionsLocator, String option){
        //CLick vào drop down icon
        clickElement(driver,DropdownIconLocator);
        //Lấy về list các options
        List<WebElement> allDropdownOptions = getListElement(driver,optionsLocator);
        for (WebElement dropdownoption: allDropdownOptions){
            if (dropdownoption.getText().equals(option)){
                dropdownoption.click();
                break;
            }
        }
    }
    public void checkCheckboxOrRadio(WebDriver driver, String rawLocator) {
        if (!getElement(driver,rawLocator).isSelected()){
        getElement(driver,rawLocator).click();}
    }
    public void checkCheckboxOrRadio(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        if (!getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).isSelected()){
            getElement(driver,formatLocator(dynamicLocatorTemplate,dynamicParts)).click();}
    }
    public void uncheckCheckboxOrRadio(WebDriver driver, String locator) {
        if (getElement(driver,locator).isSelected()){
            getElement(driver,locator).click();}
    }
    public boolean isElementDisplayed(WebDriver driver, String locator){
       return getElement(driver,locator).isDisplayed();
    }
    public boolean isElementEnable(WebDriver driver, String locator){
        return getElement(driver,locator).isEnabled();
    }
    public boolean isElementSelected(WebDriver driver, String locator){
        return getElement(driver,locator).isSelected();
    }
    public WebDriverWait getWebDriverWait(WebDriver driver){
        return new WebDriverWait(driver,Duration.ofSeconds(GlobalConstants.TIMEOUT));
    }
    public void waitForElementVisible(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(rawLocator)));
    }
    public void waitForElementVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts))));
    }
    public void waitForListElementsVisible(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(rawLocator)));}
    public void waitForListElementsVisible(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts))));}

    public void waitForElementInvisible (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts))));
    }

    public void waitForListElementInvisible (WebDriver driver, String rawLocator){
        List<WebElement> listElements = getListElement(driver,rawLocator);
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfAllElements(listElements));}

    public void waitForElementPresence (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));}
    public void waitForListElementsPresence (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));}
    public void waitForElementSelected (WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(getByLocator(rawLocator)));}
    public void waitForElementSelected (WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts))));}
    public void waitForElementClickable(WebDriver driver, String rawLocator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(rawLocator)));}
    public void waitForElementClickable(WebDriver driver, String dynamicLocatorTemplate, String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts))));}
//    public void enterTextboxByID(WebDriver driver,String idTextboxValue, String valueToSend ){
//        waitForElementVisible(driver,BasePageUI.CUSTOMER_INFO_TEXTBOX_ID,idTextboxValue);
//        sendKeyToElement(driver,BasePageUI.CUSTOMER_INFO_TEXTBOX_ID,valueToSend,idTextboxValue);
//    }

    public void waitForTextToBePresentInElement(WebDriver driver,String rawLocator,String text){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(rawLocator),text));}

    public void waitForTextToBePresentInElement(WebDriver driver, WebElement element,String text){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(element,text));

    }

    public void waitForTextToBePresentInElement(WebDriver driver,String dynamicLocatorTemplate,String text,String...dynamicParts){
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate,dynamicParts)),text));}
    public void waitForAttributeToBe(WebDriver driver,String rawLocator,String attributeName, String value){
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(getByLocator(rawLocator),attributeName,value));
    }

    public void enterTextboxByID(WebDriver driver,String dynamicLocatorTemplate,String idTextboxValue, String valueToSend ){
        waitForElementVisible(driver,dynamicLocatorTemplate,idTextboxValue);
        sendKeyToElement(driver,dynamicLocatorTemplate,valueToSend,idTextboxValue);}
    public String getAttributeValueByID(WebDriver driver,String dynamicLocatorTemplate,String idTextboxValue,String attributeValue){
        waitForElementVisible(driver,dynamicLocatorTemplate,idTextboxValue);
        return getAttributeValue(driver,dynamicLocatorTemplate,attributeValue,idTextboxValue);}

    public void setCookies(WebDriver driver, Set<Cookie> cookies){
        for (Cookie cookie:cookies){
            driver.manage().addCookie(cookie);}
        driver.navigate().refresh();}
    public void sleepInSecond(int second){
        try {
            Thread.sleep(1000*second);}
        catch (InterruptedException e){throw new RuntimeException(e);}
    }

    //Method về action:
    public void hoverToElement(WebDriver driver,String rawLocator){
        new Actions(driver).moveToElement(getElement(driver,rawLocator)).perform();
    }

    public void hoverToElement(WebDriver driver,String templateLocator,String...dynamicParts){
        new Actions(driver).moveToElement(getElement(driver,formatLocator(templateLocator,dynamicParts))).perform();
    }
    public void waitForLoadingScreenInvisible(WebDriver driver){
        waitForElementInvisible(driver, BasePageUI.LOADING_SCREEN);
    }
    public void waitForNumberOfElementsTobe(WebDriver driver, String rawLocator, int number){
        getWebDriverWait(driver).until(ExpectedConditions.numberOfElementsToBe(getByLocator(rawLocator),number));

    }


}
