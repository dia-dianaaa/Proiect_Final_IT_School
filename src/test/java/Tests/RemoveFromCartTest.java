package Tests;

import Logger.LoggerUtility;
import ObjectData.CheckoutData;
import ObjectData.SignupData;
import Pages.*;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Listeners(ChainTestListener.class)
public class RemoveFromCartTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;
    Map<String, CheckoutData> checkoutDataOjectMap;

    @Test
    public void removeFromCartTest() {
        LoggerUtility.infoTestCase("Load signup & checkout datasets");
        checkoutDataOjectMap = XmlDataLoader.loadData("src/test/resources/CheckoutData.xml", CheckoutData.class);
        CheckoutData checkout = checkoutDataOjectMap.get("dataSet_1");

        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData signup = signupDataObjectMap.get(checkout.getSignupKey());

        LoggerUtility.infoTestCase("In the homepage handle the consent popup and enter on the signup/login page");
        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();

        LoggerUtility.infoTestCase("Enter name and email and press Signup");
        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(signup);

        LoggerUtility.infoTestCase("Fill in the account information form");
        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(signup);

        LoggerUtility.infoTestCase("Verify the account was created and continue");
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();

        LoggerUtility.infoTestCase("In the homepage handle the consent popup and enter on the products page");
        homePage.clickProducts();
        ProductsPage productsPage = new ProductsPage(getDriver());

        LoggerUtility.infoTestCase("Add two products to the cart");
        List<String> addedProducts = new ArrayList<>();
        addedProducts.add(productsPage.addProductFromSearch(checkout.getFirstProduct()));
        addedProducts.add(productsPage.addProductFromSearch(checkout.getSecondProduct()));

        LoggerUtility.infoTestCase("Open the cart and verify both products are there");
        homePage.clickCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.verifyProducts(addedProducts);

        LoggerUtility.infoTestCase("Remove '" + addedProducts.get(0) + "' from the cart");
        cartPage.removeProduct(addedProducts.get(0));
        LoggerUtility.infoTestCase("Verify only '" + addedProducts.get(1) + "' is left");
        cartPage.verifyProductRemoved(addedProducts.get(0), 1);
        cartPage.verifyProducts(List.of(addedProducts.get(1)));
    }
}
