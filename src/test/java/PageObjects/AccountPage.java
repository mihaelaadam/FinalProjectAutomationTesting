package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    WebDriver driver;
    WebDriverWait wait;
    @FindBy(xpath = "//a[@class='logout']")
    private WebElement logoutBtn;
    @FindBy(xpath = "//a[@class='account']/span")
    private WebElement name;

    public boolean logoutButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        return logoutBtn.isDisplayed();
    }

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public void submitLogoutPage() {
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        logoutBtn.click();
    }

    public String getName() {
        wait.until(ExpectedConditions.visibilityOf(name));
        return name.getText();
    }
    public String getLogoutBtn() {
        wait.until(ExpectedConditions.visibilityOf(logoutBtn));
        return logoutBtn.getText();
    }


}
