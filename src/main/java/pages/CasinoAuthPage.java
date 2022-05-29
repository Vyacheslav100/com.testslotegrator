package pages;

import core.gui.PageActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CasinoAuthPage {

    WebDriver driver;

    public CasinoAuthPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "UserLogin_username")
    public WebElement login;

    @FindBy(id = "UserLogin_password")
    public WebElement password;

    @FindBy(name = "yt0")
    public WebElement signInButton;

    public void fillUsername(String keys) {
        PageActions.fillTheField(login, keys);
    }

    public void fillPassword(String keys) {
        PageActions.fillTheField(password, keys);
    }

    public void clickSignIn() {
        PageActions.clickOnElement(signInButton);
    }
}
