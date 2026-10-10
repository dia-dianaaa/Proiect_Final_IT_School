package Pages;

import HelperMethods.ElementMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import static java.sql.DriverManager.getDriver;
import static org.testng.Reporter.log;

public class ProductsPage {

    WebDriver driver;
    ElementMethods elementMethods;
    @FindBy(id = "search_product")
    WebElement searchBar;
    @FindBy(id = "submit_search")
    WebElement searchButton;

    public ProductsPage (WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String term) {
        elementMethods.enterText(searchBar, term);
        elementMethods.clickOnElement(searchButton);
    }

}
