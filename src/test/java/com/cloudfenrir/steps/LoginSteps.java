package com.cloudfenrir.steps;

import com.cloudfenrir.driver.DriverFactory;
import com.cloudfenrir.pages.InventoryPage;
import com.cloudfenrir.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class LoginSteps {

    LoginPage loginPage;
    InventoryPage inventoryPage;

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        Assert.assertEquals(DriverFactory.getDriver().getTitle(), "Swag Labs");
        loginPage = new LoginPage();
    }

    @When("the user logs in with {string} and {string}")
    public void theUserLogsInWith(String username, String password) {
        inventoryPage = loginPage.login(username, password);
    }

    @Then("the inventory page is displayed")
    public void theInventoryPageIsDisplayed() {
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page not loaded correctly");
    }

    @And("the page title is {string}")
    public void thePageTitleIs(String title) {
        Assert.assertEquals(inventoryPage.getPageTitle(), title);
    }

    @Then("the error message {string} is displayed")
    public void theErrorMessageIsDisplayed(String errorMessage) {
        Assert.assertTrue(loginPage.isErrorDisplayed(), "The login error message is not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), errorMessage);
    }

}
