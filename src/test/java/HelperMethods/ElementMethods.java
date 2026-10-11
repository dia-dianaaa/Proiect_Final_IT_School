package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ElementMethods {

    WebDriver driver;

    public ElementMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void waitVisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickOnElement(WebElement element) {
        waitVisibility(element);
        element.click();
    }

    public void enterText(WebElement element, String text) {
        waitVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    public void hoverOverElement(WebElement element) {
        waitVisibility(element);
        new Actions(driver).moveToElement(element).perform();
    }

    public void waitInvisibility(WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(element));
    }
}
