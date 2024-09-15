package org.selenium.util;

import io.restassured.http.Cookies;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.base.BasePage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class GeneralUtils extends BasePage {
    public GeneralUtils(WebDriver driver) {
        super(driver);
    }

    public void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("overlay size "+overlays.size());
        if (!overlays.isEmpty()){
                    wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("overlay are invisible ");
        } else{
            System.out.println("Overlay not found");
        }
    }
    public WebElement waitForElementToBeVisible(By element){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

   public List<Cookie> convertRestAssuredCookieToSelenium(Cookies cookies){
        List <io.restassured.http.Cookie> restAssuredCookie = new ArrayList<>();
        restAssuredCookie = cookies.asList();
        List <Cookie> seleniumCookie = new ArrayList<>();
        for (io.restassured.http.Cookie cookie : restAssuredCookie){
            seleniumCookie.add(new Cookie(cookie.getName(),cookie.getValue(),cookie.getDomain(),
                    cookie.getPath(),cookie.getExpiryDate(), cookie.isSecured(), cookie.isHttpOnly(),cookie.getSameSite()));
        }
        return seleniumCookie;
   }
}
