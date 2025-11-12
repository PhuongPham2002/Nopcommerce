package actions.components;

import commons.base.BasePage;
import interfaces.componentUI.ValidationMessageComponentUI;
import org.openqa.selenium.WebDriver;

public class ValidationMessageComponent extends BasePage {

    public ValidationMessageComponent(WebDriver driver) {
        super(driver);
    }

    public String getErrorMessageForRequiredField(String fieldName){
        waitForTextVisible(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,fieldName);
       return getDOMPropertyValue(ValidationMessageComponentUI.DYNAMIC_ERROR_MESSAGE_ID,"innerText",fieldName).trim();
    }

}
