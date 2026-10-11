package Tests;

import Logger.LoggerUtility;
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
        LoggerUtility.infoTestCase("Load signup dataset");
        signupDataObjectMap = XmlDataLoader.loadData("src/test/resources/SignupData.xml", SignupData.class);
        SignupData data = signupDataObjectMap.get("dataSet_1");

        LoggerUtility.infoTestCase("In the homepage handle the consent popup and enter on the signup/login page");
        HomepagePage homePage = new HomepagePage(getDriver());
        homePage.handleConsentPopup();
        homePage.clickSignupLogin();

        LoggerUtility.infoTestCase("Enter name and email and press Signup");
        SignupLoginPage signupLoginPage = new SignupLoginPage(getDriver());
        signupLoginPage.signup(data);

        LoggerUtility.infoTestCase("Fill in the account information form");
        SignupPage signupPage = new SignupPage(getDriver());
        signupPage.createAccount(data);

        LoggerUtility.infoTestCase("Verify the account was created and continue");
        AccountCreatedPage accountCreatedPage = new AccountCreatedPage(getDriver());
        accountCreatedPage.verifyAccountCreated();
        accountCreatedPage.clickContinue();

        LoggerUtility.infoTestCase("Delete the account and verify it was deleted");
        homePage.clickDeleteAccount();
        DeleteAccountPage deleteAccountPage = new DeleteAccountPage(getDriver());
        deleteAccountPage.verifyAccountDeleted();
        deleteAccountPage.clickContinueDelete();
    }

}
