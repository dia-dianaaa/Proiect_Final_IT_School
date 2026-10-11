package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
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

    private String clean(String text) {
        return text.replaceAll("[\\s\\u00A0]+", " ").trim();
    }

    public void verifyProducts(List<String> expectedNames) {
        elementMethods.waitVisibility(proceedToCheckoutButton);

        List<String> cartNames = new ArrayList<>();
        for (WebElement cartName : cartProductNames) {
            cartNames.add(cartName.getText());
        }
        Assert.assertEquals(cartRows.size(), expectedNames.size(),
                "The cart should have one row per product. Cart shows: " + cartNames);

        for (String expected : expectedNames) {
            boolean found = false;
            for (String actual : cartNames) {
                if (clean(actual).equals(clean(expected))) {
                    found = true;
                    break;
                }
            }
            Assert.assertTrue(found,
                    "The cart is missing: [" + expected + "]. Cart shows: " + cartNames);
        }
    }

    public void clickProceedToCheckout() {
        elementMethods.clickOnElement(proceedToCheckoutButton);
    }

    public WebElement findRow(String productName) {
        return driver.findElement(By.xpath(
                "//table[@id='cart_info_table']/tbody/tr[.//td[@class='cart_description']//a[normalize-space()='"
                        + productName + "']]"));
    }

    public void removeProduct(String productName) {
        WebElement row = findRow(productName);
        WebElement deleteButton = row.findElement(By.xpath(".//a[contains(@class,'cart_quantity_delete')]"));
        elementMethods.clickOnElement(deleteButton);
        elementMethods.waitInvisibility(row);
    }

    public void verifyProductRemoved(String productName, int remainingRows) {
        Assert.assertEquals(cartRows.size(), remainingRows,
                "The cart should have " + remainingRows + " row(s) after removing " + productName);
        for (WebElement cartName : cartProductNames) {
            Assert.assertNotEquals(cartName.getText(), productName,
                    "The product is still in the cart: " + productName);
        }
    }


}

