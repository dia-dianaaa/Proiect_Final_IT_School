package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomepagePage {
    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(xpath = "//a[@href='/']")
    WebElement homeHeading;
    @FindBy(xpath = "//a[@href='/login']")
    WebElement signupLogin;
    @FindBy(xpath = "//a[@href='/products']")
    WebElement products;
    @FindBy(xpath = "//a[@href='/view_cart']")
    WebElement cart;
    @FindBy(xpath = "//a[@href='/contact_us']")
    WebElement contactUs;

    public HomepagePage (WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void clickHomeHeading() {
        elementMethods.clickOnElement(homeHeading);
    }

    public void clickSignupLogin() {
        elementMethods.clickOnElement(signupLogin);
    }

    public void clickProducts() {
        elementMethods.clickOnElement(products);
    }

    public void handleConsentPopup() {
        try {
            By consentButton = By.xpath(
                    "//*[self::button or self::a][normalize-space()='Consent']"
            );

            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.elementToBeClickable(consentButton));
            driver.findElement(consentButton).click();
        } catch (TimeoutException ignored) {
            // Consent popup was not displayed
        }
    }

}
