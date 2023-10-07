package PageObjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "//a[@class='login']/strong")
    private WebElement loginPagebtn;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy (id = "passwd")
    private WebElement passwordInput;
    @FindBy(id = "SubmitLogin")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='alert alert-danger']//li")
    private WebElement generalErr;
    @FindBy(xpath = "//input[@id='search_query_top'][1]")
    private WebElement searchBox;
    @FindBy(xpath = "//form[contains(@action,'luxurygifts.ro/search')]//button/span")
    private WebElement searchBtn;
    @FindBy(xpath = "//div[@id='center_column']//span[@class='lighter']")
    private WebElement succesSearch;
    @FindBy(id = "selectProductSort")
    private WebElement clickDropDownOption;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public void goToLoginPage() {
        wait.until(ExpectedConditions.elementToBeClickable(loginPagebtn));
        loginPagebtn.click();
    }

    public void search(String searchPen) {
        wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.clear();
        searchBox.sendKeys(searchPen);
        wait.until(ExpectedConditions.visibilityOf(searchBtn));
        searchBtn.click();
    }
    public void login(String email, String pass) {
        wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.clear();
        passwordInput.sendKeys(pass);
        loginBtn.click();
    }
    public String getGeneralErr() {
        try {
            return generalErr.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
    public boolean loginButtonIsDisplayed() {
        wait.until(ExpectedConditions.visibilityOf(loginPagebtn));
        return loginPagebtn.isDisplayed();
    }

    public String getLoginPagebtn() {
        wait.until(ExpectedConditions.visibilityOf(loginPagebtn));
        return loginPagebtn.getText();
    }
    public String getSuccesSearch() {
        wait.until(ExpectedConditions.visibilityOf(succesSearch));
        return succesSearch.getText();
    }

}
