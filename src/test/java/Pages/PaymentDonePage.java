package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class PaymentDonePage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//*[@data-qa='order-placed']")
    WebElement orderPlaced;

    public PaymentDonePage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyOrderPlaced() {
        Assert.assertTrue(driver.getCurrentUrl().contains("/payment_done/"),
                "Not on the payment done page");
        elementMethods.waitVisibility(orderPlaced);
        Assert.assertTrue(orderPlaced.getText().contains("ORDER PLACED"),
                "The order was not placed");
    }
}
