package Tests;

import PageObjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SearchTest extends BaseTest {
    LoginPage loginPage;

    @Test
    public void searchProducts() {
        String descriereProdus = "pix alb";
        System.out.println("Search for: " + descriereProdus);
        loginPage = new LoginPage(driver);
        loginPage.search(descriereProdus);
        Assert.assertTrue(loginPage.getSuccesSearch().contains(descriereProdus));
    }

}
