package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.CartPage;
import com.qa.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";
    private static final String BIKE_LIGHT = "Sauce Labs Bike Light";

    @Test(groups = {"smoke", "regression"}, description = "TC_CART_01 - Added products appear in cart")
    public void addedProductsAppearInCart() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.addToCart(BACKPACK).addToCart(BIKE_LIGHT);
        CartPage cart = inventory.openCart();
        Assert.assertEquals(cart.getCartItemCount(), 2, "Cart should contain 2 items");
        Assert.assertTrue(cart.getCartItemNames().contains(BACKPACK), "Backpack missing from cart");
        Assert.assertTrue(cart.getCartItemNames().contains(BIKE_LIGHT), "Bike light missing from cart");
    }

    @Test(groups = "regression", description = "TC_CART_02 - Item can be removed from cart")
    public void removeItemFromCart() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.addToCart(BACKPACK).addToCart(BIKE_LIGHT);
        CartPage cart = inventory.openCart().removeItem(BACKPACK);
        Assert.assertEquals(cart.getCartItemCount(), 1, "Cart should contain 1 item after removal");
        Assert.assertFalse(cart.getCartItemNames().contains(BACKPACK), "Backpack should be removed");
    }

    @Test(groups = "regression", description = "TC_CART_03 - Continue shopping returns to products page")
    public void continueShoppingReturnsToProducts() {
        InventoryPage inventory = loginAsStandardUser().openCart().continueShopping();
        Assert.assertEquals(inventory.getPageTitle(), "Products", "Should be back on Products page");
    }
}
