package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class CartPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//table[@id='cart_info_table']/tbody/tr")
    List<WebElement> cartRows;
    @FindBy(xpath = "//td[@class='cart_description']//a")
    List<WebElement> cartProductNames;
    @FindBy(xpath = "//a[contains(@class,'check_out')]")
    WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyProducts(List<String> expectedNames) {
        elementMethods.waitVisibility(proceedToCheckoutButton);
        Assert.assertEquals(cartRows.size(), expectedNames.size(),
                "The cart should have one row per product");
        for (String expected : expectedNames) {
            boolean found = false;
            for (WebElement cartName : cartProductNames) {
                if (cartName.getText().equals(expected)) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found, "The cart is missing: " + expected);
        }
    }

    public void clickProceedToCheckout() {
        elementMethods.clickOnElement(proceedToCheckoutButton);
    }
}

