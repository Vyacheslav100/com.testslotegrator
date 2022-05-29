package core.gui;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PageActions {
    public static void clickOnElement(WebElement element) {
        element.click();
    }

    public static void fillTheField(WebElement element, String keys) {
        element.sendKeys(keys);
    }

    public static void pageIsLoaded(WebDriver driver, String title) {
        assertThat(driver.getTitle().equals(title)).withFailMessage("The Page is not [" + title + "]").isTrue();
    }

    public static void elementIsVisible(WebElement element) {
        assertThat(element.isDisplayed()).withFailMessage("The element [" + element + "] is not visible").isTrue();
    }

    public static void elementIsInvisible(WebDriver driver, WebElement element) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException e) {
            throw new RuntimeException("Element is still visible! Please, check the case manually!");
        }
    }

    public static List<WebElement> getElements(List<WebElement> elements) {
        assertThat(!elements.isEmpty()).withFailMessage("The list of elements is empty!").isTrue();
        return elements;
    }
}
