package Tests;

import Logger.LoggerUtility;
import ObjectData.CheckoutData;
import ObjectData.ProductsSearchData;
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
public class CheckoutTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;
    Map<String, CheckoutData> checkoutDataOjectMap;

    @Test
    public void checkoutTest() {
        checkoutDataOjectMap = XmlDataLoader.loadData("src/test/resources/CheckoutData.xml", CheckoutData.class);
        CheckoutData checkout = checkoutDataOjectMap.get("dataSet_3");

        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData signup = signupDataObjectMap.get(checkout.getSignupKey());
        LoggerUtility.infoTestCase("Load signup & checkout datasets");

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();
        LoggerUtility.infoTestCase("In the homepage handled the consent popup and entered on the signup/login page");

        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(signup);
        LoggerUtility.infoTestCase("Entered name and email and pressed Signup");

        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(signup);
        LoggerUtility.infoTestCase("Filled in the account information form");

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();
        LoggerUtility.infoTestCase("Verified the account was created and continue");

        homePage.clickProducts();
        ProductsPage productsPage = new ProductsPage(getDriver());
        LoggerUtility.infoTestCase("In the homepage handled the consent popup and entered on the products page");

        List<String> addedProducts = new ArrayList<>();
        addedProducts.add(productsPage.addProductFromSearch(checkout.getFirstProduct()));
        addedProducts.add(productsPage.addProductFromSearch(checkout.getSecondProduct()));
        LoggerUtility.infoTestCase("Added the 2 products in the cart");

        homePage.clickCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.verifyProducts(addedProducts);
        cartPage.clickProceedToCheckout();
        LoggerUtility.infoTestCase("Opened the cart and verified both products are there");

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.verifyOnCheckoutPage();
        checkoutPage.addComment(checkout.getComment());
        checkoutPage.clickPlaceOrder();
        LoggerUtility.infoTestCase("Continued the checkout process and added the order comment and placed the order");

        PaymentPage paymentPage = new PaymentPage(getDriver());
        paymentPage.fillPaymentDetails(checkout);
        paymentPage.clickPayAndConfirm();
        LoggerUtility.infoTestCase("Filled in the payment details and confirm");

        PaymentDonePage paymentDonePage = new PaymentDonePage(getDriver());
        paymentDonePage.verifyOrderPlaced();
        LoggerUtility.infoTestCase("Verified the order was placed");

        homePage.clickDeleteAccount();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
        LoggerUtility.infoTestCase("Deleted the account and verified it was deleted");
    }

}
