package gui.steps;

import core.ManageDriver;
import core.gui.PageActions;
import data.Endpoints;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pages.AdminPanelPage;
import pages.CasinoAuthPage;
import pages.PlayerManagementPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class GuiSteps {

    WebDriver driver;
    ManageDriver manageDriver = new ManageDriver();
    Scenario scenario;
    CasinoAuthPage casinoAuthPage;
    AdminPanelPage adminPanelPage;
    PlayerManagementPage playerManagementPage;

    @Before
    public void setUp(Scenario scenario) {
        driver = manageDriver.getDriver();
        driver.manage().window().maximize();
        scenario.log("Scenario [" + scenario.getName() + "] is starting!");
        this.scenario = scenario;
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) manageDriver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        manageDriver.closeDriver();
        scenario.log("Scenario [" + scenario.getName() + "] was completed!");
    }

    @When("^page \"([^\"]*)\" is opened$")
    public void openPage(String urn) {
        driver.get(Endpoints.baseUriGui + urn);
        scenario.log("The Page [" + Endpoints.baseUriGui + urn + "] " + "was opened");
    }

    @Then("^fill Username field with \"([^\"]*)\"$")
    public void fillField(String username) {
        casinoAuthPage = new CasinoAuthPage(driver);
        casinoAuthPage.fillUsername(username);
    }

    @Then("^fill Password field with \"([^\"]*)\"$")
    public void fillPasswordFieldWith(String password) {
        casinoAuthPage.fillPassword(password);
    }

    @Then("^click Sign In button$")
    public void clickSignInButton() {
        casinoAuthPage.clickSignIn();
    }

    @And("^page \"([^\"]*)\" is loaded$")
    public void pageIsLoaded(String title) {
        PageActions.pageIsLoaded(driver, title);
    }

    @Then("^click Users dropdown$")
    public void clickUsersDropdown() {
        adminPanelPage = new AdminPanelPage(driver);
        adminPanelPage.clickUsersDropdown();
    }

    @Then("^click Players link$")
    public void clickPlayersLink() {
        adminPanelPage.clickPlayersLink();
    }

    @And("^players table is presented$")
    public void playersTableIsPresented() {
        playerManagementPage = new PlayerManagementPage(driver);
        playerManagementPage.playersTableIsVisible();
    }

    @Then("^click sort by Username$")
    public void clickSortByUsername() throws InterruptedException {
        playerManagementPage.clickUsernameColumn();
        Thread.sleep(3000);
    }

    @And("^make sure that sort is correct$")
    public void makeSureThatSortIsCorrect() {
        List<String> sortedList = playerManagementPage.getUsernames().stream().sorted().collect(Collectors.toList());
        playerManagementPage.getUsernames();
        scenario.log("Expected result: " + sortedList);
        scenario.log("Actual result: " + playerManagementPage.getUsernames().toString());
        playerManagementPage.checkProcessingSpinnerIsInvisible();
        assertThat(sortedList.toString()).withFailMessage("Sort isn't correct!").isEqualTo(playerManagementPage.getUsernames().toString());
    }
}
