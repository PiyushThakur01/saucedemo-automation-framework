package com.qa.tests;

import com.qa.base.BaseTest;
import com.qa.pages.InventoryPage;
import com.qa.utils.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(groups = {"smoke", "regression"}, description = "TC_LOGIN_01 - Valid user can log in")
    public void validLoginShowsProductsPage() {
        InventoryPage inventory = loginAsStandardUser();
        Assert.assertEquals(inventory.getPageTitle(), "Products", "Products page title mismatch");
        Assert.assertTrue(inventory.getCurrentUrl().contains("inventory"), "URL should contain 'inventory'");
    }

    @Test(groups = "regression", description = "TC_LOGIN_02 - Locked out user is blocked")
    public void lockedOutUserCannotLogin() {
        loginPage.login("locked_out_user", ConfigReader.get("password"));
        Assert.assertTrue(loginPage.getErrorMessage().contains("locked out"),
                "Expected 'locked out' error message");
    }

    @DataProvider(name = "invalidCredentials")
    public Object[][] invalidCredentials() {
        return new Object[][]{
                // username, password, expected error text
                {"standard_user", "wrong_password", "do not match"},
                {"wrong_user", "secret_sauce", "do not match"},
                {"", "secret_sauce", "Username is required"},
                {"standard_user", "", "Password is required"}
        };
    }

    @Test(dataProvider = "invalidCredentials", groups = "regression",
            description = "TC_LOGIN_03 - Invalid credentials show correct error")
    public void invalidCredentialsShowError(String username, String password, String expectedError) {
        loginPage.login(username, password);
        Assert.assertTrue(loginPage.getErrorMessage().contains(expectedError),
                "Wrong error message. Actual: " + loginPage.getErrorMessage());
    }

    @Test(groups = "regression", description = "TC_LOGIN_04 - User can log out")
    public void logoutReturnsToLoginPage() {
        InventoryPage inventory = loginAsStandardUser();
        Assert.assertTrue(inventory.logout().isLoginPageDisplayed(), "Login page should be displayed after logout");
    }
}
