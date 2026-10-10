package Pages;

import HelperMethods.ElementMethods;
import ObjectData.ProductsSearchData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;
import static org.testng.Reporter.log;

public class ProductsPage {

    WebDriver driver;
    ElementMethods elementMethods;

    @FindBy(id = "search_product")
    WebElement searchBar;
    @FindBy(id = "submit_search")
    WebElement searchButton;
    @FindBy(xpath = "//div[@class='features_items']//div[@class='productinfo text-center']/p")
    List<WebElement> productNames;
    @FindBy(xpath = "//div[@class='features_items']//a[contains(text(),'View Product')]")
    List<WebElement> viewProductLinks;


    public ProductsPage (WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void searchProduct(String term) {
        elementMethods.enterText(searchBar, term);
        elementMethods.clickOnElement(searchButton);
    }

    public void verifySearchResults(ProductsSearchData data) {
        if (data.getShouldFindResults()) {
            boolean found = false;
            for (WebElement name : productNames) {
                if (name.getText().toLowerCase().contains(data.getExpectedFragment().toLowerCase())) {
                    found = true;
                    break;
                }
            }

            Assert.assertTrue(found, "No result contains: " + data.getExpectedFragment());
        } else {
            Assert.assertEquals(productNames.size(), 0,
                    "Expected no results for: " + data.getProductSearch());
        }
    }

    public void openFirstProduct() {
        elementMethods.clickOnElement(viewProductLinks.get(0));
    }

}
