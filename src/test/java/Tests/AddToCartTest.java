package Tests;

import PageObjects.AccountPage;
import PageObjects.AddToCartPage;
import PageObjects.LoginPage;
import Utils.ConfigUtils;
import Utils.ConstantUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
    LoginPage loginPage;
    AddToCartPage addToCartPage;
    AccountPage accountPage;

    @DataProvider(name = "loginForTests")
    public Object[][] loginForTestsDp() {
        return new Object[][]{
                {"soare@yahoo.com", "Parola"}
        };
    }

    @Test (dataProvider = "loginForTests")
    private void addToCartTest(String email, String password) {
        String browserName = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser");
        setUpDriver(browserName);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login(email, password);
        System.out.println("Login Finished");
        loginPage = new LoginPage(driver);
        loginPage.search("pix cristale blue");
        System.out.println("Search for pix albastru");

        addToCartPage = new AddToCartPage(driver);
        addToCartPage.addToCartItem();
        System.out.println("The item was been added to the shopping cart");
        Assert.assertTrue(addToCartPage.getItemAddedInShoppingCart().contains("Produsul a fost adaugat in cos cu succes!"));

        addToCartPage.openShoppingCart();
        addToCartPage.addQtyCart();
        System.out.println("1 piece of item has been added");
        Assert.assertTrue(addToCartPage.getOrderCompleted().contains("Sumar comanda"), "Cosul dvs de cumparaturi este gol.");

        accountPage = new AccountPage(driver);
        accountPage.submitLogoutPage();
        System.out.println("Logout finished");
        Assert.assertTrue(loginPage.loginButtonIsDisplayed());

    }

}