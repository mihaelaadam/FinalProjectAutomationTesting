package Tests;

import PageObjects.AccountPage;
import PageObjects.LoginPage;
import Utils.ConfigUtils;
import Utils.ConstantUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class LogoutTest extends BaseTest {
    LoginPage loginPage;
    AccountPage accountPage;

    @DataProvider(name = "loginForTests")
    public Iterator<Object[]> jsonDpCollection() throws IOException {
        Collection<Object[]> dp = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("src\\test\\resources\\Data\\datafortests.json");

        ArrayList<HashMap<String, String>> jsonData = objectMapper.readValue(file, ArrayList.class);

        for (HashMap<String, String> data : jsonData) {
            dp.add(new Object[]{data.get("email"), data.get("password")});
        }

        return dp.iterator();
    }

    @Test(dataProvider = "loginForTests")
    private void logoutTest(String email, String password) {
        String browserName = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser");
        setUpDriver(browserName);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(email, password);

        System.out.println("Login Finished");

        accountPage = new AccountPage(driver);
        Assert.assertTrue(accountPage.logoutButtonIsDisplayed());

        accountPage.submitLogoutPage();
        Assert.assertTrue(loginPage.loginButtonIsDisplayed());
        System.out.println("Logout finished");
    }

}
