package Tests;

import Logger.LoggerUtility;
import ObjectData.ProductsSearchData;
import Pages.HomepagePage;
import Pages.ProductsPage;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(ChainTestListener.class)
public class ProductsSearchTest extends TestBasePage {

    Map<String, ProductsSearchData> productsSearchDataMap;

    @Test
    public void searchProductTest() {

        LoggerUtility.infoTestCase("Load Product search dataset");
        productsSearchDataMap = XmlDataLoader.loadData("src/test/resources/ProductsSearchData.xml", ProductsSearchData.class);
        ProductsSearchData data = productsSearchDataMap.get("dataSet_2");

        LoggerUtility.infoTestCase("In the homepage handle the consent popup and enter on the products page");
        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickProducts();

        LoggerUtility.infoTestCase("Search for '" + data.getProductSearch() + "'");
        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.searchProduct(data.getProductSearch());

        LoggerUtility.infoTestCase("Verify the search results");
        productsPage.verifySearchResults(data);
    }
}
