package Pages;

import HelperMethods.ElementMethods;
import ObjectData.SignupData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupLoginPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//input[@data-qa='signup-name']")
    WebElement signupName;
    @FindBy(xpath = "//input[@data-qa='signup-email']")
    WebElement signupEmail;
    @FindBy(xpath = "//button[@data-qa='signup-button']")
    WebElement signupButton;

    public SignupLoginPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void signup(SignupData data) {
        String uniqueEmail = data.getEmail().replace("@", System.currentTimeMillis() + "@"); //e-mail unic
        elementMethods.enterText(signupName, data.getName());
        elementMethods.enterText(signupEmail, uniqueEmail);
        elementMethods.clickOnElement(signupButton);
    }
}
