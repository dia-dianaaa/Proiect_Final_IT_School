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
        productsSearchDataMap = XmlDataLoader.loadData("src/test/resources/ProductsSearchData.xml", ProductsSearchData.class);
        ProductsSearchData data = productsSearchDataMap.get("dataSet_2");
        LoggerUtility.infoTestCase("Loaded Product search dataset");

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickProducts();
        LoggerUtility.infoTestCase("In the homepage handled the consent popup and entered on the products page");

        ProductsPage productsPage = new ProductsPage(getDriver());
        productsPage.searchProduct(data.getProductSearch());
        LoggerUtility.infoTestCase("Searched for '" + data.getProductSearch() + "'");

        productsPage.verifySearchResults(data);
        LoggerUtility.infoTestCase("Verified the search results");
    }
}
