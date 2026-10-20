package com.qa.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    private static final String FOLDER = "reports/screenshots";

    private ScreenshotUtil() {
    }

    /** Saves a screenshot and returns just the file name. */
    public static String capture(WebDriver driver, String testName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS").format(new Date());
        String fileName = testName + "_" + timestamp + ".png";
        try {
            Files.createDirectories(Paths.get(FOLDER));
            Path source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE).toPath();
            Files.copy(source, Paths.get(FOLDER, fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Could not save screenshot: " + e.getMessage());
        }
        return fileName;
    }
}
