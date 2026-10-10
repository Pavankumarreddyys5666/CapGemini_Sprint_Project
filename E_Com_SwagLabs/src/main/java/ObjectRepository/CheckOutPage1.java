
package ObjectRepository;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckOutPage1 {

    private WebDriver driver;
    private WebDriverWait wait;

    private By pageTitle = By.cssSelector("[data-test='title']");
    private By firstNameField = By.id("first-name");
    private By lastNameField = By.id("last-name");
    private By postalCodeField = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public CheckOutPage1(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTitle() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(pageTitle))
                .getText();
    }

    public CheckOutPage1 enterFirstName(String firstName) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstNameField));
        element.clear();
        element.sendKeys(firstName);
        return this;
    }

    public CheckOutPage1 enterLastName(String lastName) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(lastNameField));
        element.clear();
        element.sendKeys(lastName);
        return this;
    }

    public CheckOutPage1 enterPostalCode(String postalCode) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(postalCodeField));
        element.clear();
        element.sendKeys(postalCode);
        return this;
    }

    public void clickContinue() {
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton))
                .click();
    }

    public String getError() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(errorMessage))
                .getText();
    }


public void openCart() {
    driver.findElement(By.className("shopping_cart_link")).click();
}
}
