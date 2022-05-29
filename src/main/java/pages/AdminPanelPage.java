package pages;

import core.gui.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPanelPage {

    WebDriver driver;

    public AdminPanelPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@data-target='#s-menu-users']")
    public WebElement usersDropdown;

    @FindBy(xpath = "//*[text()='Players']")
    public WebElement playersDropdown;

    public void clickUsersDropdown() {
        PageActions.clickOnElement(usersDropdown);
    }

    public void clickPlayersLink() {
        PageActions.clickOnElement(playersDropdown);
    }
}
