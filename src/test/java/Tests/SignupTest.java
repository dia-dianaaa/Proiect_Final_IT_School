package Tests;

import ObjectData.SignupData;
import Pages.*;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import org.testng.annotations.Test;

import java.util.Map;

public class SignupTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;

    @Test
    public void signupTest(){

        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData data = signupDataObjectMap.get("dataSet_1");

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();

        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(data);

        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(data);

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();

        homePage.clickDeleteAccount();

        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
    }

}
