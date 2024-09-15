package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomePage extends BasePage {

    private static final Logger log = LoggerFactory.getLogger(HomePage.class);
    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']");
    private final By accountMenuLink = By.xpath("//li[@id='menu-item-1237']");
    private By featuredProduct(String productName){
        return By.xpath("//h2[normalize-space()='"+ productName +"']");
    }
    private By addToCartBtn(String productName){
        return By.xpath("//a[@aria-label='Add “"+ productName +"” to your cart']");
    }
    private final By viewCartLink = By.xpath("//a[@title='View cart']");
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @Step
    public StorePage clickStoreMenuLink(){
        driver.findElement(storeMenuLink).click();
        log.info("Store menu link clicked");
        return new StorePage(driver);
    }
    @Step
    public ProductPage clickFeaturedProduct(String productName){
        By featuredProduct = featuredProduct(productName);
        driver.findElement(featuredProduct).click();
        return new ProductPage(driver);
    }
    @Step
    public AccountPage clickAccountMenuLink(){
        driver.findElement(accountMenuLink).click();
        return new AccountPage(driver);
    }
    @Step
    public void clickAddToCartBtn(String productName){
       By addToCartBtn = addToCartBtn(productName);
        driver.findElement(addToCartBtn).click();
    }
    @Step
    public CartPage clickViewCartLink(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        return new CartPage(driver);
    }
}
