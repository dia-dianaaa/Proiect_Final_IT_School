package Tests;

import Logger.LoggerUtility;
import ObjectData.SignupData;
import Pages.*;
import SharedData.Browser.TestBasePage;
import XmlReader.XmlDataLoader;
import com.aventstack.chaintest.plugins.ChainTestListener;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

@Listeners(ChainTestListener.class)
public class SignupTest extends TestBasePage {

    Map<String, SignupData> signupDataObjectMap;

    @Test
    public void signupTest(){
        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData data = signupDataObjectMap.get("dataSet_1");
        LoggerUtility.infoTestCase("Loaded signup dataset");

        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();
        LoggerUtility.infoTestCase("In the homepage handled the consent popup and entered on the signup/login page");

        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(data);
        LoggerUtility.infoTestCase("Entered name and email and pressed Signup");

        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(data);
        LoggerUtility.infoTestCase("Filled in the account information form");

        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();
        LoggerUtility.infoTestCase("Verified the account was created and continue");

        homePage.clickDeleteAccount();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
        LoggerUtility.infoTestCase("Deleted the account and verified it was deleted");
    }

}
