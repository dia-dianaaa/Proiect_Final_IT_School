package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class CheckoutPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//textarea[@name='message']")
    WebElement commentBox;
    @FindBy(xpath = "//a[@href='/payment']")
    WebElement placeOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyOnCheckoutPage() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/checkout"), "Not on the checkout page");
        elementMethods.waitVisibility(placeOrderButton);
    }

    public void addComment(String comment) {
        elementMethods.enterText(commentBox, comment);
    }

    public void clickPlaceOrder() {
        elementMethods.clickOnElement(placeOrderButton);
    }
}

