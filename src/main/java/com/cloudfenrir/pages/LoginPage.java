package com.cloudfenrir.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By loginErrorMsg = byDataTest("error");

    public InventoryPage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return new InventoryPage();
    }

    public String getErrorMessage() {
        return getText(loginErrorMsg);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(loginErrorMsg);
    }

    @Override
    protected By pageIdentifier() {
        return loginButton;
    }
}
