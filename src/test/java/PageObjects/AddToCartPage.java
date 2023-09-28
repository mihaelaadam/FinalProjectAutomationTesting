package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddToCartPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//a[@class='button ajax_add_to_cart_button btn']")
    private WebElement addToCartBtn;
    @FindBy(xpath = "//a[@title='Finalizeaza comanda']/span")
    private WebElement shoppingCartBtn;
    @FindBy(xpath = "//div[@class='layer_cart_title col-xs-12']/h5")
    private WebElement itemAddedInShoppingCart;
    @FindBy(xpath = "//span[@class='step1opc']")
    private WebElement orderCompleted;

    @FindBy(xpath = "//div[@id='cart_quantity_button']/a")
    private WebElement addQuantity;
    @FindBy(xpath = "//input[@class='cart_quantity_input']")
    private WebElement qtyAdded;
    @FindBy(xpath = "//input[@name='quantity_3625_0_0_65195_hidden']")              //      !!!!!       Assert
    private WebElement confirmQty;
    @FindBy(xpath = "//a[@class='cart_quantity_delete']")
    private WebElement removeFromCart;
    @FindBy(xpath = "//span[@class='ajax_cart_no_product']")
    private WebElement cartQty;


    public void removeCart() {
        wait.until(ExpectedConditions.visibilityOf(removeFromCart));
        removeFromCart.click();
    }
    public String getConfirmQty() {                                                 //      !!!!!       Assert
        wait.until(ExpectedConditions.visibilityOf(confirmQty));
        return confirmQty.getAttribute("value");
    }
    public void addQtyCart() {
        wait.until(ExpectedConditions.visibilityOf(addQuantity));
        addQuantity.click();
    }

    public void openShoppingCart() {
        wait.until(ExpectedConditions.visibilityOf(shoppingCartBtn));
        shoppingCartBtn.click();
    }
    public void addToCartItem() {
        wait.until(ExpectedConditions.visibilityOf(addToCartBtn));
        addToCartBtn.click();
    }
    public AddToCartPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }


    public String getItemAddedInShoppingCart() {
        wait.until(ExpectedConditions.visibilityOf(itemAddedInShoppingCart));
        return itemAddedInShoppingCart.getText();
    }
    public String getOrderCompleted() {
        wait.until(ExpectedConditions.visibilityOf(orderCompleted));
        return orderCompleted.getText();
    }
    public String getQtyAdded() {
        wait.until(ExpectedConditions.visibilityOf(qtyAdded));
        return qtyAdded.getText();
    }
    public String getCartQty() {
        wait.until(ExpectedConditions.visibilityOf(cartQty));
        return cartQty.getText();
    }


}
