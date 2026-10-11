package Tests;

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
        checkoutDataOjectMap = XmlDataLoader.loadData("src/test/resources/CheckoutData.xml", CheckoutData.class);
        CheckoutData checkout = checkoutDataOjectMap.get("dataSet_3");

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
        cartPage.clickProceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage(getDriver());
        checkoutPage.verifyOnCheckoutPage();
        checkoutPage.addComment(checkout.getComment());
        checkoutPage.clickPlaceOrder();

        PaymentPage paymentPage = new PaymentPage(getDriver());
        paymentPage.fillPaymentDetails(checkout);
        paymentPage.clickPayAndConfirm();

        PaymentDonePage paymentDonePage = new PaymentDonePage(getDriver());
        paymentDonePage.verifyOrderPlaced();

        homePage.clickDeleteAccount();

        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
    }

}
