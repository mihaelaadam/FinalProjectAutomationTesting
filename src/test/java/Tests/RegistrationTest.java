package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import PageObjects.MyCustomException;
import PageObjects.RegistrationPage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
//import org.openqa.selenium.WebDriver;

public class RegistrationTest extends BaseTest {
    LoginPage loginPage;
    RegistrationPage registrationPage;
    AccountPage accountPage;

//                                                    nu se inchid toate ferestrele deschise in browser - rezolvat
//                                                    nu merge assertul pentru inregistrare email pozitiv - rezolvat
//                                                    assert getAttribute pentru registration email positive si addToCart pentru confirmara cantitatii din cos
//                                                    cum identitific eroarea de la termeni si conditii?
//                                                    cum pot sa fac sa functioneze registerAccountNDp cu cele 2 dataprovidere? - o sintaxa pentru a bloca inchiderea browserului?
//                                                    drop-down list se blocheaza la clickul de deschidere a listei - rezolvat fara action. Nu merge cu action. Exp

    @DataProvider(name = "registerEmailNDp")
    public Object[][] registerEmailNegativeDp() {
        return new Object[][] {
                {"user1","chrome", "Adresa de e-mail este invalida"},       //      termina-ma de completat cu soare@yahoo.com
                {"","firefox", "Adresa de e-mail este invalida"},
                {"soare@yahoo.com","edge", "Exista deja un cont cu aceeasi adresa de e-mail. Introdu parola sau cere una noua."}
        };
    }
    @DataProvider (name = "registerEmailPDp")
    public Object[][] registerEmailPositiveDP() {
        return new Object[][] {
                {"parola@yahoo.com", "chrome"}
        };
    }
    //    folosesc acest dataprovider pentru a completa prima parte a inregistrarii  pentru creare cont
//    @DataProvider (name = "emailForRegisterAccount")
//    public Object[][] insertEmailForInfoAccount() {
//        return new Object[][] {
//                {"parola@yahoo.com"}
//        };
//    }
    @DataProvider (name = "registerAccountNDp")
    public Object[][] registerInfoNegativeDp() {
        return new Object[][] {
                {"Soare", "","Parola", "chrome", "", "nume_de_familie", ""},
                {"Soare", "Alexandra", "", "firefox", "", "", "passwd"},
                {"", "", "", "edge", "prenume", "nume_de_familie", "passwd"},
                {"Soare", "Alexandra", "Parola", "firefox", "", "", ""}
        };
    }
    @DataProvider (name = "registerAccountPDp")
    public Object[][] registerInfoPositiveDp() {
        return new Object[][]{
                {"Sherkan", "Maximilian", "Maximusprime", "chrome"}
        };
    }

    @Test (dataProvider = "registerEmailNDp")
    public void registerNegativeEmail(String emailRegister,String browser, String emailRegisterError) {
        System.out.println("Login with email: " + emailRegister + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail(emailRegister);
        registrationPage.clickCreateAccountButton();
        registrationPage.waitForEmailRegErr();
        System.out.println("Insert email finished, verify error message");
        Assert.assertEquals(emailRegisterError, registrationPage.getEmailRegisterError());
    }
    @Test (dataProvider = "registerEmailPDp")
    public void registerPositiveEmail(String emailRegister, String browser) {
        System.out.println("Login with email: " + emailRegister + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail(emailRegister);
        registrationPage.clickCreateAccountButton();
//        registrationPage.verifyNewWindow();
        System.out.println("Insert email finished, verify error message");
//        Assert.assertEquals(emailRegister, registrationPage.getEmailInput());
        System.out.println("Expected: " + emailRegister + ", Actual: " + registrationPage.getEmailInput());
    }

//    folosesc acest test pentru a completa prima parte a inregistrarii pentru creare cont
//    @Test(dataProvider = "emailForRegisterAccount")
//    public void insertEmailFromDp1(String email) {
//        System.out.println("Login with email: " + email);
//        loginPage = new LoginPage(driver);
//        loginPage.goToLoginPage();
//
//        registrationPage = new RegistrationPage(driver);
//        registrationPage.registerWithEmail(email);
//        registrationPage.clickCreateAccountButton();
//    }
//    testul negativ pentru informatii creare cont
//    @Test(dataProvider = "registerAccountNDp", dependsOnMethods = "insertEmailFromDp1")
//    public void insertAccountDetailsNegativeDp(String firstName, String lastName, String password, String browser, String firstNameErr, String lastNameErr, String passwdErr) throws MyCustomException {
//        System.out.println("Login with name: " + firstName + " " + lastName + " => on browser: " + browser);
//        setUpDriver(browser);
//        driver.get(baseUrl);
//
//        registrationPage = new RegistrationPage(driver);
//        registrationPage.register(firstName, lastName, password, browser, firstNameErr, lastNameErr, lastNameErr);
//        registrationPage.clickTermsCheckboxUsingActionsScroll();
//        registrationPage.submitRegistrationPage();
//
//        Assert.assertEquals(registrationPage.getFirstNameError(), firstNameErr);
//        Assert.assertEquals(registrationPage.getLastNameError(), lastNameErr);
//        Assert.assertEquals(registrationPage.getPasswordRegisterError(), passwdErr);
//    }

    @Test (dataProvider = "registerAccountNDp")
    public void insertAccountDetailsNegativeDp(String firstName, String lastName, String password, String browser, String firstNameErr, String lastNameErr, String passwdErr) {
        System.out.println("Login with name: " + firstName + " " + lastName + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail("maximus@yahoo.com");
        registrationPage.clickCreateAccountButton();
        registrationPage.register(firstName, lastName, password);
        registrationPage.clickTermsCheckbox();
        registrationPage.submitRegistrationPage();

        Assert.assertEquals(registrationPage.getFirstNameError(), firstNameErr);
        Assert.assertEquals(registrationPage.getLastNameError(), lastNameErr);
        Assert.assertEquals(registrationPage.getPasswordRegisterError(), passwdErr);
    }

    @Test (dataProvider = "registerAccountPDp")
    public void insertAccountDetailsPositiveDp(String firstName, String lastName, String password, String browser) {
        System.out.println("Login with name: " + firstName + " " + lastName + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail("sherkan.maximus@yahoo.com");
        registrationPage.clickCreateAccountButton();
        registrationPage.register(firstName, lastName, password);
        registrationPage.clickTermsCheckbox();
        registrationPage.submitRegistrationPage();

        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getName().contains(firstName));
    }

}
