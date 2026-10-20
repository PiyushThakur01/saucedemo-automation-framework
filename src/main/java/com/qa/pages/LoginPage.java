package com.qa.pages;

import com.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /** Fills the form and clicks Login. Use for negative tests (stays on LoginPage). */
    public LoginPage login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
        return this;
    }

    /** Use when the login is expected to succeed. */
    public InventoryPage loginSuccessfully(String username, String password) {
        login(username, password);
        return new InventoryPage(driver);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isLoginPageDisplayed() {
        return isDisplayed(loginButton);
    }
}
