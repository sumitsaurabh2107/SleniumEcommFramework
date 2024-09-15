package org.selenium.base;

import io.restassured.http.Cookies;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.util.ConfigLoader;
import org.selenium.util.FakerUtils;
import org.selenium.util.GeneralUtils;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class BaseTest {
    private final ThreadLocal <WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void setup(@Optional String browser){

        browser = System.getProperty("browser",browser);
       // if (browser == null) browser = "chrome";
        //localBrowser = browser;
        switch(browser.toLowerCase()){
            case "chrome":
                setDriver(new ChromeDriver());
                getDriver().get(ConfigLoader.getInstance().getURL());
                getDriver().manage().window().maximize();
                System.out.println("CURRENT THREAD: "+ Thread.currentThread().getName()+","+" DRIVER = "+getDriver());
                break;
            case "firefox":
                setDriver(new FirefoxDriver());
                getDriver().get(ConfigLoader.getInstance().getURL());
                getDriver().manage().window().maximize();
                System.out.println("CURRENT THREAD: "+ Thread.currentThread().getName()+","+" DRIVER = "+getDriver());
                break;
            default:
                throw new IllegalStateException("Invalid browser name: "+ browser);
        }
    }
    @Parameters("browser")
    @AfterMethod
    public void tearDown(@Optional String browser,ITestResult result) throws IOException {
        System.out.println("CURRENT THREAD: "+ Thread.currentThread().getName()+","+" DRIVER = "+getDriver());
        if (result.getStatus() == ITestResult.FAILURE){
            File destFile = new File("screenshot"+File.separator + browser +File.separator
            + result.getTestClass().getRealClass().getSimpleName()+ "_"+
                    result.getMethod().getMethodName()+ ".png");
            takeScreenShot(destFile);
        }
        getDriver().quit();
    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> seleniumCookies = new GeneralUtils(getDriver()).convertRestAssuredCookieToSelenium(cookies);
        for (Cookie cookie : seleniumCookies){
            getDriver().manage().addCookie(cookie);
        }
    }

    private void takeScreenShot (File destFile) throws IOException {
        TakesScreenshot takesScreenshot = (TakesScreenshot) getDriver();
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(srcFile,destFile);
    }
}
