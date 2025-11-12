package interfaces.pageUI;


import org.openqa.selenium.By;

public class LoginPageUI {
    public final static String LOGIN_BUTTON="CSS=button.login-button";
    public final static String UNREGISTERED_EMAIL_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String EMPTY_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String INVALID_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";
    public final static String LOGIN_FORM_TEXTBOX_ID ="ID=%s";
    public final static String LOGIN_ERROR_MESSAGE="id=Email-error";
    public static final String WRONG_PASSWORD_ERROR_MESSAGE ="css=div.validation-summary-errors";

    public static final String PASSWORD_EYE_ICON="css=span.password-eye";
    public static final String PASSWORD_CLOSE="xpath=//input[@type='password']";
    public static final String PASSWORD_OPEN="xpath=//input[@id='Password' and @type='text']";

    //Cucumber Implement:
    public static final String ERROR_MESSAGE ="css=div.validation-summary-errors";
    public static final String MY_ACCOUNT_LINK="css=a.ico-account";


    public final static By LOGIN = By.cssSelector("button.login-button");

}
