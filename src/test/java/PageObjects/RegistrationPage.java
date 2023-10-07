package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailRegister;
    @FindBy(xpath = "//button[@id='SubmitCreate']/span")
    private WebElement createAccountBtn;
    @FindBy(id = "customer_firstname")
    private WebElement firstNameRegister;
    @FindBy(id = "customer_lastname")
    private WebElement lastNameRegister;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "passwd")
    private WebElement passwordRegister;
    @FindBy(id = "uniform-optintwo")
    private WebElement termsBtn;
    @FindBy(id = "submitAccount")
    private WebElement submitRegistrationBtn;

    @FindBy(xpath = "//div[@id='create_account_error']/ol/li")
    private WebElement errorListItem;
    @FindBy(xpath = "//div[@class='alert alert-danger']//b[contains(text(),'prenume')]")
    private WebElement firstNameError;
    @FindBy(xpath = "//div[@class='alert alert-danger']//b[contains(text(),'nume_de_familie')]")
    private WebElement lastNameError;
    @FindBy(xpath = "//div[@class='alert alert-danger']//b[contains(text(),'passwd')]")
    private WebElement passwordRegisterError;


    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }


    public void submitRegistrationPage() {
        wait.until(ExpectedConditions.elementToBeClickable(submitRegistrationBtn));
        submitRegistrationBtn.click();
    }

    public void waitForEmailRegErr() {
        wait.until(ExpectedConditions.visibilityOf(errorListItem));
    }
    public void clickCreateAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(createAccountBtn));
        createAccountBtn.click();
    }

    public void registerWithEmail(String emailReg) {
        wait.until(ExpectedConditions.visibilityOf(emailRegister));
        emailRegister.clear();
        emailRegister.sendKeys(emailReg);
    }

    public void clickTermsCheckbox() {
        wait.until(ExpectedConditions.elementToBeClickable(termsBtn));
        termsBtn.click();

    }

    public void register(String firstName, String lastName, String passwordReg) {
        wait.until(ExpectedConditions.visibilityOf(firstNameRegister));
        firstNameRegister.clear();
        firstNameRegister.sendKeys(firstName);
        lastNameRegister.clear();
        lastNameRegister.sendKeys(lastName);
        passwordRegister.clear();
        passwordRegister.sendKeys(passwordReg);
    }


    public String getFirstNameError() {
        try {
            return firstNameError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
    public String getLastNameError() {
        try {
            return lastNameError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
    public String getPasswordRegisterError() {
        try {
            return passwordRegisterError.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
    public String getEmailRegisterError() {
        try {
            return errorListItem.getText();
        } catch (NoSuchElementException ex) {
            return "";
        }
    }
    public String getEmailInput() {
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        return emailInput.getAttribute("value");
    }

}