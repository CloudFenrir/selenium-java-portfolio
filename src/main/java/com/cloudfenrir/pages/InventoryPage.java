package com.cloudfenrir.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class InventoryPage extends BasePage {

    private final By inventoryContainer = byDataTest("inventory-container");
    private final By inventoryTitle = byDataTest("title");
    private final By productsName = byDataTest("inventory-item-name");

    public String getPageTitle() {
        return getText(inventoryTitle);
    }

    @Override
    protected By pageIdentifier() {
        return inventoryContainer;
    }

    public Optional<String> findProductByName(String name) {
        return driver.findElements(productsName)
                .stream()
                .map(WebElement::getText)
                .filter(text -> text.equals(name))
                .findFirst();
    }
}
