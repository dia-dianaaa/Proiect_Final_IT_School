package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AccountCreatedPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//h2[@data-qa='account-created']")
    WebElement accountCreated;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueButton;

    public AccountCreatedPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyAccountCreated() {
        elementMethods.waitVisibility(accountCreated);
        Assert.assertEquals(accountCreated.getText(), "ACCOUNT CREATED!", "Account was not created");
    }

    public void clickContinue() {
        elementMethods.clickOnElement(continueButton);
    }
}
