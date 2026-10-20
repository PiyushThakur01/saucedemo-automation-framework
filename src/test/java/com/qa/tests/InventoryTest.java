package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InventoryTest extends BaseTest {

    private static final String BACKPACK = "Sauce Labs Backpack";

    @Test(groups = {"smoke", "regression"}, description = "TC_INV_01 - Six products are displayed")
    public void sixProductsAreDisplayed() {
        InventoryPage inventory = loginAsStandardUser();
        Assert.assertEquals(inventory.getProductCount(), 6, "Product count mismatch");
    }

    @Test(groups = "regression", description = "TC_INV_02 - Sort by price low to high")
    public void sortByPriceLowToHigh() {
        InventoryPage inventory = loginAsStandardUser().sortBy("Price (low to high)");
        List<Double> actual = inventory.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        Assert.assertEquals(actual, expected, "Prices are not sorted low to high");
    }

    @Test(groups = "regression", description = "TC_INV_03 - Sort by price high to low")
    public void sortByPriceHighToLow() {
        InventoryPage inventory = loginAsStandardUser().sortBy("Price (high to low)");
        List<Double> actual = inventory.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Prices are not sorted high to low");
    }

    @Test(groups = "regression", description = "TC_INV_04 - Sort by name Z to A")
    public void sortByNameZToA() {
        InventoryPage inventory = loginAsStandardUser().sortBy("Name (Z to A)");
        List<String> actual = inventory.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        Assert.assertEquals(actual, expected, "Names are not sorted Z to A");
    }

    @Test(groups = {"smoke", "regression"}, description = "TC_INV_05 - Cart badge updates when product is added")
    public void cartBadgeUpdatesOnAdd() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.addToCart(BACKPACK);
        Assert.assertEquals(inventory.getCartCount(), 1, "Cart badge should show 1");
    }

    @Test(groups = "regression", description = "TC_INV_06 - Cart badge clears when product is removed")
    public void cartBadgeClearsOnRemove() {
        InventoryPage inventory = loginAsStandardUser();
        inventory.addToCart(BACKPACK).removeFromCart(BACKPACK);
        Assert.assertEquals(inventory.getCartCount(), 0, "Cart badge should be empty");
    }
}
