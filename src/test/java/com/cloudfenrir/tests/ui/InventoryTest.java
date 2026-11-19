package com.cloudfenrir.tests.ui;

import com.cloudfenrir.config.ConfigReader;
import com.cloudfenrir.pages.InventoryPage;
import com.cloudfenrir.pages.LoginPage;
import com.cloudfenrir.tests.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class InventoryTest extends BaseTest {

    @Test
    public void existingProductShouldBeFound() {
        InventoryPage inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.findProductByName("Sauce Labs Backpack").isPresent(),
                "Product not found: Sauce Labs Backpack");
    }

    @Test
    public void nonExistingProductShouldNotBeFound() {
        InventoryPage inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.findProductByName("Fake Product").isEmpty(),
                "A non-existing product was found");
    }

    private InventoryPage loginAsStandardUser() {
        InventoryPage page = new LoginPage().login(ConfigReader.get("appUsername"), ConfigReader.get("appPassword"));
        Assert.assertTrue(page.isLoaded(), "Inventory page did not load after login");
        return page;
    }

}
