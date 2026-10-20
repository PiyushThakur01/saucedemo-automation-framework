package com.qa.base;

import com.qa.pages.InventoryPage;
import com.qa.pages.LoginPage;
import com.qa.utils.ConfigReader;
import com.qa.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.get("baseUrl"));
        loginPage = new LoginPage(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    /** Shortcut used by tests that need a logged-in user. */
    protected InventoryPage loginAsStandardUser() {
        return loginPage.loginSuccessfully(ConfigReader.get("username"), ConfigReader.get("password"));
    }
}
