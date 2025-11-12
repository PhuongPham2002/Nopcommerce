package commons.base;

import commons.constants.GlobalConstants;
import interfaces.pageUI.BasePageUI;
import io.cucumber.java.mk_latn.No;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BasePage {

    protected final Logger log = LogManager.getLogger(getClass());
    protected WebDriver driver;

    public BasePage(WebDriver driver) {

        this.driver = driver;
    }

    //Locator:
    public String formatLocator(String dynamicLocatorTemplate, String... dynamicParts) {
        return String.format(dynamicLocatorTemplate, (Object[]) dynamicParts);
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
        } else if (rawLocator.toLowerCase().startsWith("linktext")) {  //bắt chính xác text của thẻ a
            return By.linkText(rawLocator.substring(9));
        } else if (rawLocator.toLowerCase().startsWith("partiallinktext")) {
            return By.linkText(rawLocator.substring(16));
        }
        throw new IllegalArgumentException("Raw Locator is not valid: " + rawLocator);
    }

    //Elements:
    public WebElement getElement(String rawLocator) {
        return driver.findElement(getByLocator(rawLocator));
    }

    public WebElement getElement(String dynamicLocatorTemplate, String... dynamicParts) {
        return driver.findElement(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts)));
    }

    public List<WebElement> getListElement(String rawLocator) {
        return driver.findElements(getByLocator(rawLocator));
    }

    public List<WebElement> getListElement(String dynamicLocatorTemplate, String... dynamicParts) {
        return driver.findElements(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts)));
    }

    //Actions
    public void clickElement(String rawLocator) {
        getElement(rawLocator).click();
    }

    public void clickElement(String dynamicLocatorTemplate, String... dynamicParts) {
        String locator = formatLocator(dynamicLocatorTemplate, dynamicParts);
        getElement(locator).click();
    }

    public void clickElement(WebElement element) {
        element.click();
    }

    public void sendKeyToElement(String rawLocator, String valueToSend) {
        clearKeyInElement(rawLocator);
        getElement(rawLocator).sendKeys(valueToSend);
        getElement(rawLocator).sendKeys(Keys.TAB);
    }

    public void sendKeyToElement(String dynamicLocatorTemplate, String valueToSend, String... dynamicParts) {
        clearKeyInElement(formatLocator(dynamicLocatorTemplate, dynamicParts));
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(valueToSend);
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.TAB);
    }

    public void sendKeyToElement(WebElement element, String valueToSend) {
        clearKeyInElement(element);
        element.sendKeys(valueToSend);
        element.sendKeys(Keys.TAB);
    }

    public void clearKeyInElement(String rawLocator) {
        getElement(rawLocator).sendKeys(Keys.CONTROL + "a");
        getElement(rawLocator).sendKeys(Keys.DELETE);
    }

    public void clearKeyInElement(String dynamicLocatorTemplate, String... dynamicParts) {
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.CONTROL + "a");
        getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).sendKeys(Keys.DELETE);

    }

    public void clearKeyInElement(WebElement element) {
        element.sendKeys(Keys.CONTROL + "a");
        element.sendKeys(Keys.DELETE);
    }

    public String getElementText(String rawLocator) {
        return getElement(rawLocator).getText();
    }

    public String getElementText(String dynamicLocatorTemplate, String... dynamicParts) {
        return getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).getText();

    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public List<String> getListElementText(String rawLocator) {
        List<WebElement> listElements = getListElement(rawLocator);
        List<String> listElementsText = new ArrayList<>();
        for (WebElement element : listElements) {
            listElementsText.add(element.getText().trim());
        }
        return listElementsText;
    }

    public List<String> getListElementText(String dynamicLocatorTemplate, String... dynamicParts) {
        List<WebElement> listElements = getListElement(formatLocator(dynamicLocatorTemplate, dynamicParts));
        List<String> listElementsText = new ArrayList<>();
        for (WebElement element : listElements) {
            listElementsText.add(element.getText().trim());
        }
        return listElementsText;
    }

    public List<String> getListElementText(List<WebElement> elements) {
        List<String> listElementsText = new ArrayList<>();
        for (WebElement element : elements) {
            listElementsText.add(element.getText().trim());
        }
        return listElementsText;
    }

    public String getDOMPropertyValue(String rawLocator, String attributeName) {
        return getElement(rawLocator).getDomProperty(attributeName);
    }

    public String getDOMPropertyValue(String dynamicLocatorTemplate, String attributeName, String... dynamicParts) {
        return getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).getDomProperty(attributeName);
    }

    public String getDOMPropertyValue(WebElement element, String attributeName) {
        return element.getDomProperty(attributeName);
    }

    public String getAttributeValue(String rawLocator, String attributeName) {
        return getElement(rawLocator).getDomAttribute(attributeName);
    }

    public String getAttributeValue(String templateLocator, String attributeName, String... dynamicParts) {
        return getElement(formatLocator(templateLocator, dynamicParts)).getDomAttribute(attributeName);
    }

    public String getAttributeValue(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public int getListElementsSize(String rawLocator) {
        return getListElement(rawLocator).size();
    }

    public int getListElementsSize(String dynamicLocatorTemplate, String... dynamicParts) {
        return getListElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).size();
    }

    public int getListElementsSize(List<WebElement> elements) {
        return elements.size();
    }

    public void selectDropdownOption(String rawLocator, String option) {
        new Select(getElement(rawLocator)).selectByVisibleText(option);
    }

    public void selectDropdownOption(String option, String dynamicLocatorTemplate, String... dynamicParts) {
        new Select(getElement(formatLocator(dynamicLocatorTemplate, dynamicParts))).selectByVisibleText(option);
    }

    public void selectDropdownOption(WebElement element, String option) {
        new Select(element).selectByVisibleText(option);
    }

    public void deselectDropdownOption(String rawLocator, String option) {
        new Select(getElement(rawLocator)).deselectByVisibleText(option);
    }

    public void deselectDropdownOption(String option, String dynamicLocatorTemplate, String... dynamicParts) {
        new Select(getElement(formatLocator(dynamicLocatorTemplate, dynamicParts))).deselectByVisibleText(option);
    }

    public void deselectDropdownOption(WebElement element, String option) {
        new Select(element).deselectByVisibleText(option);
    }

    public void selectMultipleDropdownOptions(String rawLocator, List<String> options) {
        Select dropdown = new Select(getElement(rawLocator));
        List<String> notFoundOption = new ArrayList<>();
        if (dropdown.isMultiple()) {
            for (String option : options) {
                try {
                    dropdown.selectByVisibleText(option);
                } catch (NoSuchElementException e) {
                    log.info("Option is not found:" + option);
                    notFoundOption.add(option);
                }
            }
        } else {
            log.info("Not allow to select multiple dropdown");
        }
        if (!notFoundOption.isEmpty()) {

            throw new AssertionError("Option is not found in dropdown: " + String.join(",", notFoundOption));
        }

    }

    public String getSelectedDropdownOption(String rawLocator) {
        return new Select(getElement(rawLocator)).getFirstSelectedOption().getText();
    }

    public String getSelectedDropdownOption(String dynamicLocatorTemplate, String... dynamicParts) {
        return new Select(getElement(formatLocator(dynamicLocatorTemplate, dynamicParts))).getFirstSelectedOption().getText();
    }

    public List<String> getAllSelectedDropdownOptions(String locator) {
        List<WebElement> allSelectedOptions = new Select(getElement(locator)).getAllSelectedOptions();
        List<String> selectedOptions = new ArrayList<String>();
        for (WebElement option : allSelectedOptions) {
            selectedOptions.add(option.getText());
        }
        return selectedOptions;
    }

    public List<String> getOptions(String locator) {
        List<WebElement> allDropdownOptions = new Select(getElement(locator)).getOptions();
        List<String> dropdownOptions = new ArrayList<String>();
        for (WebElement option : allDropdownOptions) {
            dropdownOptions.add(option.getText());
        }
        return dropdownOptions;
    }

    public boolean isDropdownMultiple(String locator) {
        return new Select(getElement(locator)).isMultiple();
    }

    public void selectCustomizedDropdownOptions(String DropdownIconLocator, String optionsLocator, String option) {
        //CLick vào drop down icon
        clickElement(DropdownIconLocator);
        //Lấy về list các options
        List<WebElement> allDropdownOptions = getListElement(optionsLocator);
        for (WebElement dropdownOption : allDropdownOptions) {
            if (dropdownOption.getText().trim().equals(option)) {
                dropdownOption.click();
                break;
            }
        }
    }

    public void checkNativeRadio(String rawLocator) {
        if (!getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
        }
    }

    public void checkNativeRadio(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }

    public void checkNativeRadio(String templateLocator, String... dynamicParts) {
        if (!getElement(templateLocator, dynamicParts).isSelected()) {
            getElement(templateLocator, dynamicParts).click();
        }
    }

    public void uncheckNativeRadio(String rawLocator) {
        if (getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
        }
    }

    public void uncheckNativeRadio(WebElement element) {
        if (element.isSelected()) {
           element.click();
        }
    }

    public void uncheckNativeRadio(String templateLocator, String... dynamicParts) {
        if (getElement(formatLocator(templateLocator, dynamicParts)).isSelected()) {
            getElement(formatLocator(templateLocator, dynamicParts)).click();
        }
    }


    public void checkNativeCheckbox(String rawLocator) {
        if (!getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
        }
    }

    public void checkNativeCheckbox(WebElement element) {
        if (!element.isSelected()) {
            element.click();
        }
    }


    public void checkNativeCheckbox(String templateLocator, String... dynamicParts) {
        if (!getElement(templateLocator, dynamicParts).isSelected()) {
            getElement(templateLocator, dynamicParts).click();
        }
    }

    public void checkCustomCheckbox(String rawLocator, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(rawLocator, attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(rawLocator).click();
        }
    }

    public void checkCustomRadio(String rawLocator, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(rawLocator, attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(rawLocator).click();
        }
    }

    public void checkCustomRadio(String templateDynamicLocator, String attributeName, String expectedAttributeValue, String... dynamicParts) {

        String attributeValue = getDOMPropertyValue(formatLocator(templateDynamicLocator, dynamicParts), attributeName);
        if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
            getElement(formatLocator(templateDynamicLocator, dynamicParts)).click();
        }

    }

    public void checkAllNativeCheckboxes(String rawLocator) {
        int numberOfCheckboxes = getListElementsSize(rawLocator);
        for (int i = 0; i < numberOfCheckboxes; i++) {
            List<WebElement> checkboxes = getListElement(rawLocator);
            WebElement checkbox = checkboxes.get(i);
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void checkAllCustomCheckboxes(String rawLocator, String attributeName, String expectedAttributeValue) {
        int numberOfCheckboxes = getListElementsSize(rawLocator);
        for (int i = 0; i < numberOfCheckboxes; i++) {
            List<WebElement> checkboxes = getListElement(rawLocator);
            WebElement checkbox = checkboxes.get(i);
            String attributeValue = checkbox.getDomProperty(attributeName);
            if (attributeValue == null || !attributeValue.contains(expectedAttributeValue)) {
                checkbox.click();
            }
        }
    }


    public void checkCheckboxOrRadio(String dynamicLocatorTemplate, String... dynamicParts) {
        if (!getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).isSelected()) {
            getElement(formatLocator(dynamicLocatorTemplate, dynamicParts)).click();
        }
    }


    public void uncheckNativeCheckbox(String rawLocator) {
        if (getElement(rawLocator).isSelected()) {
            getElement(rawLocator).click();
        }
    }

    public void uncheckCustomCheckbox(String rawLocator, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(rawLocator, attributeName);
        if (attributeValue != null && attributeValue.contains(expectedAttributeValue)) {
            getElement(rawLocator).click();
        }
    }

    public void uncheckCustomCheckbox(WebElement element, String attributeName, String expectedAttributeValue) {
        String attributeValue = getDOMPropertyValue(element, attributeName);
        if (attributeValue != null && attributeValue.contains(expectedAttributeValue)) {
            element.click();
        }
    }

    public boolean isElementDisplayed(String locator) {
        return getElement(locator).isDisplayed();
    }

    public boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    public boolean isElementEnable(String locator) {
        return getElement(locator).isEnabled();
    }
    public boolean isElementEnable(WebElement element) {
        return element.isEnabled();
    }

    public boolean isElementSelected(String locator) {
        return getElement(locator).isSelected();
    }
    public boolean isElementSelected(WebElement element) {
        return element.isSelected();
    }

    //Wait


    public void waitForExpectedConditionMet(Function<WebDriver, Boolean> innerText) {
    }

    public WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(GlobalConstants.TIMEOUT));
    }

    public WebElement waitForElementVisible(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(rawLocator)));
    }

    public WebElement waitForElementVisible(String dynamicLocatorTemplate, String... dynamicParts) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public List<WebElement> waitForListElementsVisible(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(rawLocator)));
    }

    public List<WebElement> waitForListElementsVisible(String dynamicLocatorTemplate, String... dynamicParts) {
        return getWebDriverWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public void waitForElementInvisible(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public Boolean waitForElementInvisible(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(rawLocator)));
    }

    public Boolean waitForListElementInvisible(String rawLocator) {
        List<WebElement> listElements = getListElement(rawLocator);
        return getWebDriverWait(driver).until(ExpectedConditions.invisibilityOfAllElements(listElements));
    }

    public WebElement waitForElementPresence(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(getByLocator(rawLocator)));
    }

    public WebElement waitForElementPresence(String templateLocator, String... dynamicParts) {

        return getWebDriverWait(driver).until(ExpectedConditions.presenceOfElementLocated(getByLocator(formatLocator(templateLocator, dynamicParts))));
    }

    public List<WebElement> waitForListElementsPresence(String locator) {
        return getWebDriverWait(driver).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(locator)));
    }

    public Boolean waitForElementSelected(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(getByLocator(rawLocator)));
    }

    public void waitForElementSelected(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeSelected(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public WebElement waitForElementClickable(String rawLocator) {
        return getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(rawLocator)));
    }

    public void waitForElementClickable(WebElement element) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForElementClickable(String dynamicLocatorTemplate, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.elementToBeClickable(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts))));
    }

    public Boolean waitForTextToBePresentInElement(String rawLocator, String text) {
        return getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(rawLocator), text));
    }


    public void waitForTextToBePresentInElement(WebElement element, String text) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElement(element, text));

    }

    public void waitForTextToBePresentInElement(String dynamicLocatorTemplate, String text, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.textToBePresentInElementLocated(getByLocator(formatLocator(dynamicLocatorTemplate, dynamicParts)), text));
    }

    public void waitForAttributeToBe(String rawLocator, String attributeName, String value) {
        getWebDriverWait(driver).until(ExpectedConditions.attributeToBe(getByLocator(rawLocator), attributeName, value));
    }

    public void enterTextboxByID(String dynamicLocatorTemplate, String valueToSend, String idTextboxValue) {
        waitForElementVisible(dynamicLocatorTemplate, idTextboxValue);
        sendKeyToElement(dynamicLocatorTemplate, valueToSend, idTextboxValue);
    }


    public void waitForUrlContains(String valueToContain) {
        getWebDriverWait(driver).until(ExpectedConditions.urlContains(valueToContain));
    }


    public String getAttributeValueByID(String dynamicLocatorTemplate, String attributeValue, String idTextboxValue) {
        waitForElementVisible(dynamicLocatorTemplate, idTextboxValue);
        return getDOMPropertyValue(dynamicLocatorTemplate, attributeValue, idTextboxValue);
    }


    public void hoverToElement(String locatorForLog) {
        new Actions(driver).moveToElement(getElement(locatorForLog)).perform();
    }

    public void hoverToElement(WebElement element) {
        new Actions(driver).moveToElement(element).perform();
    }

    public void hoverToElement(String templateLocator, String... dynamicParts) {
        String locatorForLog = formatLocator(templateLocator, dynamicParts);
        hoverToElement(locatorForLog);
    }

    public void waitForAttributeContains(String rawLocator, String attributeName, String valueToContain, String... dynamicParts) {
        getWebDriverWait(driver).until(ExpectedConditions.attributeContains(getByLocator(formatLocator(rawLocator, dynamicParts)), attributeName, valueToContain));
    }


    public void waitForNumberOfElementsTobe(String rawLocator, int number) {
        getWebDriverWait(driver).until(ExpectedConditions.numberOfElementsToBe(getByLocator(rawLocator), number));

    }

    public void waitForDomPropertyTobe(WebElement element, String value) {
        getWebDriverWait(driver).until(ExpectedConditions.domPropertyToBe(element, "innerText", value));
    }

    public void waitForTextVisible(String templateLocator, String... dynamics) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(templateLocator, "innerText", dynamics);
                return text != null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + formatLocator(templateLocator, dynamics);
            }
        });
    }

    public void waitForTextVisible(String templateLocator) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(500));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String text = getDOMPropertyValue(templateLocator, "innerText");
                return text != null && !text.trim().isEmpty();
            }

            @Override
            public String toString() {
                return "waiting for non-empty innerText of element: " + getByLocator(templateLocator);
            }
        });
    }

    //Trong trường hợp không chắc có spinner hay không (có thể có hoặc không)
    public void waitForSpinnerInvisibleOrSkipSpinner(WebDriver driver) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(10));
        wait.pollingEvery(Duration.ofMillis(300));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        try {
            wait.until(new Function<WebDriver, Boolean>() {

                @Override
                public Boolean apply(WebDriver driver) {
                    WebElement spinner = getElement(BasePageUI.LOADING_ICON);
                    if (spinner.isDisplayed()) {
                        log.info("Spinner display is still visible...");
                    }
                    return !spinner.isDisplayed();
                }
            });
            log.info("Spinner already disappeared");
        } catch (TimeoutException e) {
            System.out.println("Spinner did not appear or disappear too fast");
        }

    }


    // Trường hợp chắc chắn có spinner
    public void waitForLoadingIconInvisible() {
        waitForElementInvisible(BasePageUI.LOADING_ICON);
    }
    public void waitForLoadingScreenInvisible() {

        waitForElementInvisible(BasePageUI.LOADING_SCREEN);
    }


}






















