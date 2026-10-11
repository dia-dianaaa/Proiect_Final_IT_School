package SharedData.Browser;

import Logger.LoggerUtility;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestBasePage {

    private WebDriver driver;
    public String testName;

    @BeforeMethod
    public void initialiseBrowser() {

        ThreadContext.put("testName", this.getClass().getSimpleName());
        testName = this.getClass().getSimpleName();
        driver = new BrowserFactory().getBrowserFactory();
        LoggerUtility.infoTestCase("===== The browser started successfully");
        LoggerUtility.startTestCase(testName);
        driver.get("https://automationexercise.com/");
        driver.manage().deleteAllCookies();   // start every test logged out
        driver.navigate().refresh();
    }

    @AfterMethod
    public void clearBrowser(ITestResult result)
    {
        driver.quit();
        LoggerUtility.infoTestCase("The browser was closed successfully");
        if(result.getStatus() == ITestResult.FAILURE)
        {
            LoggerUtility.errorLog(result.getThrowable().getMessage());
        }
        LoggerUtility.infoTestCase("===== The browser closed successfully");
        LoggerUtility.endTestCase(testName);
    }

    private void saveScreenshot(ITestResult result) {
        try {
            Path folder = Paths.get("target", "screenshots");
            Files.createDirectories(folder);
            byte[] image = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path file = folder.resolve(testName + "_" + result.getName() + "_" + System.currentTimeMillis() + ".png");
            Files.write(file, image);
            LoggerUtility.infoTestCase("Screenshot saved: " + file.toAbsolutePath());
        } catch (Exception e) {
            LoggerUtility.errorLog("Could not save screenshot: " + e.getMessage());
        }
    }

    @AfterSuite
    public void finishLogFiles(){
        LoggerUtility.mergeFiles();
    }

    public WebDriver getDriver() {
        return driver;
    }
}

