package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.CheckoutPage;
import com.qa.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CheckoutTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    private CheckoutPage goToCheckoutWith(String... products) {
        InventoryPage inventory = loginAsStandardUser();
        for (String product : products) {
            inventory.addToCart(product);
        }
        return inventory.openCart().clickCheckout();
    }

    @Test(groups = {"smoke", "regression"}, description = "TC_CHK_01 - End-to-end order placement")
    public void userCanCompleteOrder() {
        CheckoutPage checkout = goToCheckoutWith(BACKPACK);
        checkout.fillInformation("John", "Doe", "10001").clickContinue().finishOrder();
        Assert.assertEquals(checkout.getConfirmationMessage(), "Thank you for your order!",
                "Order confirmation message mismatch");
    }

    @Test(groups = "regression", description = "TC_CHK_02 - Total equals item total plus tax")
    public void totalEqualsSubtotalPlusTax() {
        CheckoutPage checkout = goToCheckoutWith(BACKPACK, BIKE_LIGHT);
        checkout.fillInformation("John", "Doe", "10001").clickContinue();
        double expected = checkout.getSubtotal() + checkout.getTax();
        Assert.assertEquals(checkout.getTotal(), expected, 0.01, "Total should equal subtotal + tax");
    }

    @DataProvider(name = "missingFields")
    public Object[][] missingFields() {
        return new Object[][]{
                // first name, last name, postal code, expected error text
                {"", "Doe", "10001", "First Name is required"},
                {"John", "", "10001", "Last Name is required"},
                {"John", "Doe", "", "Postal Code is required"}
        };
    }

    @Test(dataProvider = "missingFields", groups = "regression",
            description = "TC_CHK_03 - Mandatory field validation on checkout form")
    public void missingFieldShowsError(String first, String last, String zip, String expectedError) {
        CheckoutPage checkout = goToCheckoutWith(BACKPACK);
        checkout.fillInformation(first, last, zip).clickContinue();
        Assert.assertTrue(checkout.getErrorMessage().contains(expectedError),
                "Wrong error message. Actual: " + checkout.getErrorMessage());
    }
}
