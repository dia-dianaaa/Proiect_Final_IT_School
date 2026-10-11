package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class DeleteAccountPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//h2[@data-qa='account-deleted']")
    WebElement accountDeleted;
    @FindBy(xpath = "//a[@data-qa='continue-button']")
    WebElement continueDeleteButton;

    public DeleteAccountPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyAccountDeleted() {
        elementMethods.waitVisibility(accountDeleted);
        Assert.assertEquals(accountDeleted.getText(), "ACCOUNT DELETED!", "Account was not deleted");
    }

    public void clickContinueDelete() {
        elementMethods.clickOnElement(continueDeleteButton);
    }
}



