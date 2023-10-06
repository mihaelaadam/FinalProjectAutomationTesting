package Tests;

import PageObjects.DropDownSortPage;
import PageObjects.LoginPage;
import Utils.ConfigUtils;
import Utils.ConstantUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownSortTest extends BaseTest {
    LoginPage loginPage;
    DropDownSortPage dropDownSortPage;
    @Test
    public void sortByPrice() {
        String browserName = ConfigUtils.getGenericElement(ConstantUtils.CONFIG_FILE, "browser");
        setUpDriver(browserName);
        driver.get(baseUrl);
        loginPage = new LoginPage(driver);
        String descriereProdus = "pix";
        System.out.println("Search for: " + descriereProdus);
        loginPage = new LoginPage(driver);
        loginPage.search(descriereProdus);
        dropDownSortPage = new DropDownSortPage(driver);
        dropDownSortPage.openDropDownList(1);
        dropDownSortPage.selectedElementIsDisplayed();
        Assert.assertEquals(dropDownSortPage.getDropDownOption(1), dropDownSortPage.getSelectedDropDownOption(),"Incorrect drop down option selected");

    }
}
