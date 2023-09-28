package Tests;

import PageObjects.AccountPage;
import PageObjects.AddToCartPage;
import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {
    LoginPage loginPage;
    AddToCartPage addToCartPage;
    AccountPage accountPage;
    @Test
    private void addToCartTest() {
        loginPage = new LoginPage(driver);
        loginPage.goToLoginPage();
        loginPage.login("soare@yahoo.com", "Parola");
        System.out.println("Login Finished");
        loginPage = new LoginPage(driver);
        loginPage.search("pix albastru");
        System.out.println("Search for pix albastru");

        addToCartPage = new AddToCartPage(driver);
        addToCartPage.addToCartItem();
        Assert.assertTrue(addToCartPage.getItemAddedInShoppingCart().contains("Produsul a fost adaugat in cos cu succes!"));
        System.out.println("The item was been added to the shopping cart");
        addToCartPage.openShoppingCart();
        Assert.assertTrue(addToCartPage.getOrderCompleted().contains("Sumar comanda"), "Cosul dvs de cumparaturi este gol.");
        addToCartPage.addQtyCart();
//        Assert.assertEquals(addToCartPage.getQtyAdded(), addToCartPage.getConfirmQty());
        System.out.println("1 piece of item has been added");
        addToCartPage.removeCart();
        Assert.assertEquals(addToCartPage.getCartQty(), "0");
        System.out.println("Item removed from cart");

        accountPage = new AccountPage(driver);
        accountPage.submitLogoutPage();
        System.out.println("Logout finished");
        Assert.assertEquals(loginPage.getLoginPagebtn(), "Cont");

    }

}