package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DropDownSortPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @FindBy(id = "selectProductSort")
    private WebElement openDropDownBtn;
    @FindBy(xpath = "//select[@id='selectProductSort']/option")
    private List<WebElement> dropDownElements;
    @FindBy(xpath = "//select[@id='selectProductSort']/option[@selected='selected']")
    private WebElement selectedDropDownOptionElement;

    public void selectedElementIsDisplayed() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(selectedDropDownOptionElement));
        System.out.println("Selected drop-down option: " + selectedDropDownOptionElement.getText());
    }

    public DropDownSortPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);
    }

    public void openDropDownList(int dropDownOptionIndex) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        openDropDownBtn.click();
        System.out.println("The drop-down list is displayed: " + dropDownElements.get(dropDownOptionIndex).isDisplayed());
        dropDownElements.get(dropDownOptionIndex).click();
    }

    public String getDropDownOption(int dropDownOptionIndex) {
        return dropDownElements.get(dropDownOptionIndex).getText();
    }

    public String getSelectedDropDownOption() {
        return selectedDropDownOptionElement.getText();
    }

}
