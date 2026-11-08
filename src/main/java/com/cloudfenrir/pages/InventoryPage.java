package com.cloudfenrir.pages;

import org.openqa.selenium.By;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = byDataTest("inventory-container");
    private final By inventoryTitle = byDataTest("title");

    public String getPageTitle() {
        return getText(inventoryTitle);
    }

    @Override
    protected By pageIdentifier() {
        return inventoryContainer;
    }
}
