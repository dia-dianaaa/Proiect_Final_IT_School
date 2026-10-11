package Pages;

import HelperMethods.ElementMethods;
import ObjectData.CheckoutData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//input[@data-qa='name-on-card']")
    WebElement nameOnCard;
    @FindBy(xpath = "//input[@data-qa='card-number']")
    WebElement cardNumber;
    @FindBy(xpath = "//input[@data-qa='cvc']")
    WebElement cvc;
    @FindBy(xpath = "//input[@data-qa='expiry-month']")
    WebElement expiryMonth;
    @FindBy(xpath = "//input[@data-qa='expiry-year']")
    WebElement expiryYear;
    @FindBy(xpath = "//button[@data-qa='pay-button']")
    WebElement payButton;

    public PaymentPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void fillPaymentDetails(CheckoutData data) {
        elementMethods.enterText(nameOnCard, data.getNameOnCard());
        elementMethods.enterText(cardNumber, data.getCardNumber());
        elementMethods.enterText(cvc, data.getCvc());
        elementMethods.enterText(expiryMonth, data.getExpiryMonth());
        elementMethods.enterText(expiryYear, data.getExpiryYear());
    }

    public void clickPayAndConfirm() {
        elementMethods.clickOnElement(payButton);
    }
}

