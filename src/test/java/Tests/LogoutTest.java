package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {
    LoginPage loginPage;
    AccountPage accountPage;

    @Test
    private void logoutTest() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login("soare@yahoo.com", "Parola");
        System.out.println("Login Finished");

        accountPage = new AccountPage(driver);
        Assert.assertEquals(accountPage.getLogoutBtn(), "Logout");

        accountPage.submitLogoutPage();
        Assert.assertEquals(loginPage.getLoginPagebtn(), "Cont");
        System.out.println("Logout finished");
    }

}
