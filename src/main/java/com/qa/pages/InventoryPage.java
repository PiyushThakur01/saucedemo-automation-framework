package com.qa.pages;

import com.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class InventoryPage extends BasePage {

    private final By pageTitle = By.className("title");
    private final By productItems = By.className("inventory_item");
    private final By productNames = By.className("inventory_item_name");
    private final By productPrices = By.className("inventory_item_price");
    private final By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private final By cartBadge = By.className("shopping_cart_badge");
    private final By cartLink = By.className("shopping_cart_link");
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public int getProductCount() {
        waitForVisible(productItems);
        return driver.findElements(productItems).size();
    }

    public List<String> getProductNames() {
        waitForVisible(productNames);
        return driver.findElements(productNames).stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        waitForVisible(productPrices);
        return driver.findElements(productPrices).stream()
                .map(e -> Double.parseDouble(e.getText().replace("$", "")))
                .collect(Collectors.toList());
    }

    /** Options: "Name (A to Z)", "Name (Z to A)", "Price (low to high)", "Price (high to low)" */
    public InventoryPage sortBy(String visibleText) {
        new Select(waitForVisible(sortDropdown)).selectByVisibleText(visibleText);
        return this;
    }

    public InventoryPage addToCart(String productName) {
        click(By.id("add-to-cart-" + toSlug(productName)));
        return this;
    }

    public InventoryPage removeFromCart(String productName) {
        click(By.id("remove-" + toSlug(productName)));
        return this;
    }

    public int getCartCount() {
        List<WebElement> badge = driver.findElements(cartBadge);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText());
    }

    public CartPage openCart() {
        click(cartLink);
        return new CartPage(driver);
    }

    public LoginPage logout() {
        click(menuButton);
        click(logoutLink);
        return new LoginPage(driver);
    }
}
