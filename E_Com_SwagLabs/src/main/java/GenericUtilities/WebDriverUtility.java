package GenericUtilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

    public void waitForElement(WebDriver driver, WebElement element) {

        new WebDriverWait(driver, Duration.ofSeconds(10)).until(d -> element.isDisplayed());
    }

    public void selectByVisibleText(WebElement element, String text) {

        new Select(element).selectByVisibleText(text);
    }

    public void selectByValue(WebElement element, String value) {

        new Select(element).selectByValue(value);
    }

    public List<WebElement> getAllOptions(WebElement element) {

        return new Select(element).getOptions();
    }
}