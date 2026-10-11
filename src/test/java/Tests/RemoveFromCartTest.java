package Tests;

import ObjectData.CheckoutData;
import ObjectData.SignupData;
import Pages.*;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RemoveFromCartTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;
    Map<String, CheckoutData> checkoutDataOjectMap;

    @Test
    public void removeFromCartTest() {
        checkoutDataOjectMap = XmlDataLoader.loadData("src/test/resources/CheckoutData.xml", CheckoutData.class);
        CheckoutData checkout = checkoutDataOjectMap.get("dataSet_1");

        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData signup = signupDataObjectMap.get(checkout.getSignupKey());

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();

        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(signup);

        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(signup);

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();

        homePage.clickProducts();
        ProductsPage productsPage = new ProductsPage(getDriver());

        List<String> addedProducts = new ArrayList<>();
        addedProducts.add(productsPage.addProductFromSearch(checkout.getFirstProduct()));
        addedProducts.add(productsPage.addProductFromSearch(checkout.getSecondProduct()));

        homePage.clickCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.verifyProducts(addedProducts);

        cartPage.removeProduct(addedProducts.get(0));
        cartPage.verifyProductRemoved(addedProducts.get(0), 1);
        cartPage.verifyProducts(List.of(addedProducts.get(1)));
    }
}
