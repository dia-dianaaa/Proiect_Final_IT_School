package Tests;

import Pages.HomepagePage;
import SharedData.Browser.TestBasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

public class ProductsSearchTest extends TestBasePage {

    @Test
    public void metodaTest() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickProducts();

    }

}
