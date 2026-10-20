package com.qa.pages;

import com.qa.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/** Covers all three checkout screens: your information, overview, and complete. */
public class CheckoutPage extends BasePage {

    private final By firstName = By.id("first-name");
    private final By lastName = By.id("last-name");
    private final By postalCode = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By subtotalLabel = By.className("summary_subtotal_label");
    private final By taxLabel = By.className("summary_tax_label");
    private final By totalLabel = By.className("summary_total_label");
    private final By finishButton = By.id("finish");
    private final By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage fillInformation(String first, String last, String zip) {
        type(firstName, first);
        type(lastName, last);
        type(postalCode, zip);
        return this;
    }

    public CheckoutPage clickContinue() {
        click(continueButton);
        return this;
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public double getSubtotal() {
        return parseAmount(getText(subtotalLabel));
    }

    public double getTax() {
        return parseAmount(getText(taxLabel));
    }

    public double getTotal() {
        return parseAmount(getText(totalLabel));
    }

    public CheckoutPage finishOrder() {
        click(finishButton);
        return this;
    }

    public String getConfirmationMessage() {
        return getText(completeHeader);
    }

    /** "Item total: $29.99" -> 29.99 */
    private double parseAmount(String text) {
        return Double.parseDouble(text.replaceAll("[^0-9.]", ""));
    }
}
