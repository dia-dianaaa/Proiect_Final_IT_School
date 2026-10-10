package Tests;

import ObjectData.ProductsSearchData;
import Pages.HomepagePage;
import Pages.ProductsPage;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import java.util.Map;

public class ProductsSearchTest extends TestBasePage {

    Map<String, ProductsSearchData> productsSearchDataMap;

    @Test
    public void searchProductTest() {

        productsSearchDataMap = XmlDataLoader.loadData("src/test/resources/ProductsSearchData.xml", ProductsSearchData.class);
        ProductsSearchData data = productsSearchDataMap.get("dataSet_1");

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickProducts();

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.searchProduct(data.getProductSearch());
    }
}
