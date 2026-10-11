package Pages;

import HelperMethods.ElementMethods;
import HelperMethods.SignupFormMethods;
import ObjectData.SignupData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    WebDriver driver;
    ElementMethods elementMethods;
    SignupFormMethods signupFormMethods;

    @FindBy(xpath = "//label[@for='id_gender1']")
    WebElement titleMr;
    @FindBy(xpath = "//label[@for='id_gender2']")
    WebElement titleMrs;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(id = "days")
    WebElement days;
    @FindBy(id = "months")
    WebElement months;
    @FindBy(id = "years")
    WebElement years;
    @FindBy(id = "newsletter")
    WebElement newsletterCheckbox;
    @FindBy(id = "optin")
    WebElement partnerOffersCheckbox;
    @FindBy(id = "first_name")
    WebElement firstName;
    @FindBy(id = "last_name")
    WebElement lastName;
    @FindBy(id = "company")
    WebElement company;
    @FindBy(id = "address1")
    WebElement address;
    @FindBy(id = "address2")
    WebElement address2;
    @FindBy(id = "country")
    WebElement country;
    @FindBy(id = "state")
    WebElement state;
    @FindBy(id = "city")
    WebElement city;
    @FindBy(id = "zipcode")
    WebElement zipcode;
    @FindBy(id = "mobile_number")
    WebElement mobileNumber;
    @FindBy(xpath = "//button[@data-qa='create-account']")
    WebElement createAccountButton;

    public SignupPage(WebDriver driver) {
        this.driver = driver;
        this.elementMethods = new ElementMethods(driver);
        this.signupFormMethods = new SignupFormMethods(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectTitle(String title) {
        if (title.equals("Mr")) {
            elementMethods.clickOnElement(titleMr);
        } else if (title.equals("Mrs")) {
            elementMethods.clickOnElement(titleMrs);
        } else {
            throw new IllegalArgumentException("Title must be Mr or Mrs, got: " + title);
        }
    }

    public void createAccount(SignupData data) {
        selectTitle(data.getTitle());

        elementMethods.enterText(password, data.getPassword());

        signupFormMethods.selectDropdownByText(days, data.getDay());
        signupFormMethods.selectDropdownByText(months, data.getMonth());
        signupFormMethods.selectDropdownByText(years, data.getYear());
        signupFormMethods.setCheckbox(newsletterCheckbox, data.getNewsletter());
        signupFormMethods.setCheckbox(partnerOffersCheckbox, data.getPartnerOffers());

        elementMethods.enterText(firstName, data.getFirstName());
        elementMethods.enterText(lastName, data.getLastName());
        elementMethods.enterText(company, data.getCompany());
        elementMethods.enterText(address, data.getAddress());
        elementMethods.enterText(address2, data.getAddress2());

        signupFormMethods.selectDropdownByText(country, data.getCountry());
        elementMethods.enterText(state, data.getState());
        elementMethods.enterText(city, data.getCity());
        elementMethods.enterText(zipcode, data.getZipcode());
        elementMethods.enterText(mobileNumber, data.getMobileNumber());

        elementMethods.clickOnElement(createAccountButton);
    }

}
