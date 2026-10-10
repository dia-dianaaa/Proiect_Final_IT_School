package ObjectData;

public class SignupData {

    private String title;
    private String name;
    private String email;
    private String password;
    private String day;
    private String month;
    private String year;
    private String firstName;
    private String lastName;
    private String address;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String mobileNumber;
    private String newsletter;
    private String partnerOffers;

    public String getTitle() {
        return title;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public boolean getNewsletter() {
        return Boolean.parseBoolean(newsletter);
    }

    public boolean getPartnerOffers() {
        return Boolean.parseBoolean(partnerOffers);
    }
}