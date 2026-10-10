package HelperMethods;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class SignupFormMethods {

    WebDriver driver;
    ElementMethods elementMethods;

    public SignupFormMethods(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
    }

    public void selectDropdownByText(WebElement dropdown, String text) {
        elementMethods.waitVisibility(dropdown);
        new Select(dropdown).selectByVisibleText(text);
    }

    public void selectDropdownByValue(WebElement dropdown, String value) {
        elementMethods.waitVisibility(dropdown);
        new Select(dropdown).selectByValue(value);
    }

    public void setCheckbox(WebElement checkbox, boolean shouldBeChecked) {
        elementMethods.waitVisibility(checkbox);
        if (checkbox.isSelected() != shouldBeChecked) {
            elementMethods.clickOnElement(checkbox);
        }

    }
}