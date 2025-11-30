package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    // Page Factory locators
    @FindBy(xpath = "//input[@id='inputUsername']")
    private WebElement usernameField;

    @FindBy(xpath = "//input[@name='inputPassword']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[text()='Sign In']")
    private WebElement loginButton;

    // Constructor initializes elements
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Methods
    public void enterUsername(String username) throws InterruptedException {

        Thread.sleep(4000);
        usernameField.sendKeys(username);
    }

    public void enterPassword(String password) throws InterruptedException {
        Thread.sleep(4000);
        passwordField.sendKeys(password);
    }

    public void clickLogin() throws InterruptedException {
        Thread.sleep(4000);
        loginButton.click();
    }
}