package com.cloudfenrir.tests.ui;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.pages.InventoryPage;
import com.cloudfenrir.pages.LoginPage;
import com.cloudfenrir.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @DataProvider(name = "invalidLogins")
    public Object[][] invalidLogins() {
        return new Object[][] {
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "wrongPassword", "Epic sadface: Username and password do not match any user in this service"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"}
        };
    }

    @Test
    public void validLoginShouldOpenInventory() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = loginPage.login(ConfigReader.get("username"), ConfigReader.get("password"));
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page did not load after login");
        Assert.assertEquals(inventoryPage.getPageTitle(), "Products");
    }

    @Test(dataProvider = "invalidLogins")
    public void invalidLoginShouldShowError(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage();
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.isErrorDisplayed(), "Error message was not displayed");
        Assert.assertEquals(loginPage.getErrorMessage(), expectedError);
    }

}
