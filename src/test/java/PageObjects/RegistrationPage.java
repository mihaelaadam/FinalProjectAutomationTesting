package PageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    WebDriver driver;
    WebDriverWait wait;
    Actions actions;

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
    @FindBy(xpath = "//div[@class='alert alert-danger']//b[contains(text(),'prenume')]")       //      class = alert alert-danger  -   de ce da eroare cand e introdus intre ghinimele duble?
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
        wait.until(ExpectedConditions.visibilityOf(submitRegistrationBtn));
        submitRegistrationBtn.click();
    }



//    public void verifyNewWindow() {
//        WebElement accountCreationDiv = driver.findElement(By.className("account_creation"));
//        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("account_creation")));
//        if (accountCreationDiv != null) {
//            System.out.println("Elementul a fost găsit pe pagină.");
//        } else {
//            System.out.println("Elementul nu a fost găsit pe pagină.");
//        }
//    }

//    for (String windowHandle : driver.getWindowHandles()) {
//        driver.switchTo().window(windowHandle);
//        if (driver.findElement(By.className("account_creation"))) {
//            break;
//        }
//        System.out.println("I cant find the page");
//    }

    public void waitForEmailRegErr() {
        wait.until(ExpectedConditions.visibilityOf(errorListItem));
    }
    public void clickCreateAccountButton() {
        wait.until(ExpectedConditions.visibilityOf(createAccountBtn));
        createAccountBtn.click();
    }

    public void registerWithEmail(String emailReg) {
        wait.until(ExpectedConditions.elementToBeClickable(emailRegister));
        emailRegister.clear();
        emailRegister.sendKeys(emailReg);
    }

    public void clickTermsCheckbox() {
        wait.until(ExpectedConditions.visibilityOf(termsBtn));
        termsBtn.click();

    }

    public void register(String firstName, String lastName, String passwordReg) {
        wait.until(ExpectedConditions.elementToBeClickable(firstNameRegister));
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
    public String getEmailInput() {                                     //          !!!!        Assert
        wait.until(ExpectedConditions.visibilityOf(emailInput));
        return emailInput.getAttribute("value");
    }

}