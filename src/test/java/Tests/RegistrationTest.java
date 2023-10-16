package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import PageObjects.RegistrationPage;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class RegistrationTest extends BaseTest {

    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private AccountPage accountPage;

    @DataProvider(name = "registerEmailNDp")
    public Object[][] registerEmailNegativeDp() {
        return new Object[][] {
                {"user1","chrome", "Adresa de e-mail este invalida"},
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

    @DataProvider (name = "registerAccountNDp")
    public Object[][] registerInfoNegativeDp() {
        return new Object[][] {
                {"emil.panda@yahoo.com", "Emil", "","Parola", "chrome", "", "nume_de_familie", ""},
                {"emil.panda@yahoo.com", "Emil", "Panda", "", "firefox", "", "", "passwd"},
                {"emil.panda@yahoo.com", "", "Panda", "Parola", "firefox", "prenume", "", ""},
                {"emil.panda@yahoo.com", "", "", "", "edge", "prenume", "nume_de_familie", "passwd"}
        };
    }

    @DataProvider (name = "registerAccountPDp")
    public Object[][] registerInfoPositiveDp() {
        return new Object[][]{
                {"marinela.adrian@yahoo.com", "Marinela", "Adrian", "Marinela&Adrian", "chrome"},
                {"carturarescu.cecil@yahoo.com", "Cecil", "Carturarescu", "CartC88()$Ce^cil", "firefox"}
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
    public void registerPositiveEmail(String emailRegister, String browser) throws InterruptedException {
        System.out.println("Login with email: " + emailRegister + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail(emailRegister);
        registrationPage.clickCreateAccountButton();
        System.out.println("Insert email finished, verify error message");
        Thread.sleep(2000);
        Assert.assertEquals(emailRegister, registrationPage.getEmailInput());
        System.out.println("Expected: " + emailRegister + ", Actual: " + registrationPage.getEmailInput());
    }

    @Test (dataProvider = "registerAccountNDp")
    public void insertAccountDetailsNegativeDp(String emailReg, String firstName, String lastName, String password, String browser, String firstNameErr, String lastNameErr, String passwdErr) {
        System.out.println("Login with name: " + firstName + " " + lastName + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail(emailReg);
        registrationPage.clickCreateAccountButton();
        registrationPage.register(firstName, lastName, password);
        registrationPage.clickTermsCheckbox();
        registrationPage.submitRegistrationPage();

        Assert.assertEquals(registrationPage.getFirstNameError(), firstNameErr);
        Assert.assertEquals(registrationPage.getLastNameError(), lastNameErr);
        Assert.assertEquals(registrationPage.getPasswordRegisterError(), passwdErr);
    }

    @Test (dataProvider = "registerAccountPDp")
    public void insertAccountDetailsPositiveDp(String emailReg, String firstName, String lastName, String password, String browser) {
        System.out.println("Login with name: " + firstName + " " + lastName + " => on browser: " + browser);
        setUpDriver(browser);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();

        registrationPage = new RegistrationPage(driver);
        registrationPage.registerWithEmail(emailReg);
        registrationPage.clickCreateAccountButton();
        registrationPage.register(firstName, lastName, password);
        registrationPage.clickTermsCheckbox();
        registrationPage.submitRegistrationPage();

        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.getName().contains(firstName));
    }

}
