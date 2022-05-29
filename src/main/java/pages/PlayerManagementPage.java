package pages;

import core.gui.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerManagementPage {

    WebDriver driver;

    public PlayerManagementPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='payment-system-transaction-grid']/table")
    public WebElement playersTable;

    @FindBy(xpath = "//*[text()='Username']")
    public WebElement sortByUsername;

    @FindBy(xpath = "//tbody//td[2]")
    public List<WebElement> usernames;

    @FindBy(xpath = "//*[@class='grid-view grid-view-loading']")
    public WebElement sortProcessingSpinner;

    public void playersTableIsVisible() {
        PageActions.elementIsVisible(playersTable);
    }

    public void clickUsernameColumn() {
        PageActions.clickOnElement(sortByUsername);
    }

    public List<String> getUsernames() {
        return PageActions.getElements(usernames).stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void checkProcessingSpinnerIsInvisible() {
        PageActions.elementIsInvisible(driver, sortProcessingSpinner);
    }
}
