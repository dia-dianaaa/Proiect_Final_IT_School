package Tests;

import Logger.LoggerUtility;
import ObjectData.CheckoutData;
import ObjectData.ProductsSearchData;
import ObjectData.SignupData;
import Pages.*;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CheckoutTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;
    Map<String, CheckoutData> checkoutDataOjectMap;

    @Test
    public void checkoutTest() {
        LoggerUtility.infoTestCase("Load signup & checkout datasets");
        checkoutDataOjectMap = XmlDataLoader.loadData("src/test/resources/CheckoutData.xml", CheckoutData.class);
        CheckoutData checkout = checkoutDataOjectMap.get("dataSet_3");

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

        LoggerUtility.infoTestCase("Add the 2 products in the cart");
        List<String> addedProducts = new ArrayList<>();
        addedProducts.add(productsPage.addProductFromSearch(checkout.getFirstProduct()));
        addedProducts.add(productsPage.addProductFromSearch(checkout.getSecondProduct()));

        LoggerUtility.infoTestCase("Open the cart and verify both products are there");
        homePage.clickCart();
        CartPage cartPage = new CartPage(getDriver());
        cartPage.verifyProducts(addedProducts);
        cartPage.clickProceedToCheckout();

        LoggerUtility.infoTestCase("Continue the checkout process and add the order comment and place the order");
        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.verifyOnCheckoutPage();
        checkoutPage.addComment(checkout.getComment());
        checkoutPage.clickPlaceOrder();

        LoggerUtility.infoTestCase("Fill in the payment details and confirm");
        PaymentPage paymentPage = new PaymentPage(getDriver());
        paymentPage.fillPaymentDetails(checkout);
        paymentPage.clickPayAndConfirm();

        LoggerUtility.infoTestCase("Verify the order was placed");
        PaymentDonePage paymentDonePage = new PaymentDonePage(getDriver());
        paymentDonePage.verifyOrderPlaced();

        LoggerUtility.infoTestCase("Delete the account and verify it was deleted");
        homePage.clickDeleteAccount();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
    }

}
