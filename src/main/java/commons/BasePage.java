package commons;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class BasePage {

    public By getByLocator(String locator) {
        if (locator.toLowerCase().startsWith("xpath")) {
            return By.xpath(locator.substring(6));
        } else if (locator.toLowerCase().startsWith("css")) {
            return By.cssSelector(locator.substring(4));
        } else if (locator.toLowerCase().startsWith("id")) {
            return By.id(locator.substring(3));
        } else if (locator.toLowerCase().startsWith("name")) {
            return By.name(locator.substring(5));
        } else if (locator.toLowerCase().startsWith("tagname")) {
            return By.tagName(locator.substring(8));
        }
        throw new IllegalArgumentException("Locator is not valid: "+locator);
    }
    public WebElement getElement (WebDriver driver, String locator) {
        return driver.findElement(getByLocator(locator));
    }
    public List<WebElement> getListElement (WebDriver driver, String locator){
        return driver.findElements(getByLocator(locator));
    }
    public void clickElement(WebDriver driver, String locator){
        getElement(driver,locator).click();
    }
    public void sendKeyToElement(WebDriver driver, String locator, String valueToSend){
        getElement(driver,locator).clear();
        getElement(driver,locator).sendKeys(valueToSend);
    }
    public String getElementText(WebDriver driver, String locator){
        return getElement(driver,locator).getText();
    }

    public String getAttributeValue(WebDriver driver, String locator, String attributeName){
        return getElement(driver,locator).getDomProperty(attributeName);

    }
    public int getListElementsSize(WebDriver driver, String locator){
        return getListElement(driver,locator).size();
    }
    public void selectDropdownOption(WebDriver driver, String locator, String option){
        new Select(getElement(driver,locator)).selectByVisibleText(option);
    }
    //public void selectMultipleDropdownOptions (WebDriver driver, String locator, String option)  --> khi làm làm đến dynamic locator thì mình apply
    public void deselectDropdownOption(WebDriver driver, String locator,String option){
        new Select(getElement(driver,locator)).deselectByVisibleText(option);
    }
    public String getSelectedDropdownOption(WebDriver driver, String locator){
        return new Select(getElement(driver,locator)).getFirstSelectedOption().getText();
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
    public void checkCheckboxOrRadio(WebDriver driver, String locator) {
        if (!getElement(driver,locator).isSelected()){
        getElement(driver,locator).click();}
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
    public void waitForElementVisible(WebDriver driver, String locator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForListElementsVisible(WebDriver driver, String locator){
        getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));}
    public void waitForElementInvisible (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }
    public void waitForListElementInvisible (WebDriver driver, String locator){
        List<WebElement> listElements = getListElement(driver,locator);
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfAllElements(listElements));
    }
    public void waitForElementPresence (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(getByLocator(locator)));
    }
    public void waitForListElementsPresence (WebDriver driver, String locator) {
        getWebDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }
    public void waitForElementSelected (WebDriver driver, String locator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(getByLocator(locator)));
    }
    public void waitForElementClickable(WebDriver driver, String locator){
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(getElement(driver,locator)));
    }
}
