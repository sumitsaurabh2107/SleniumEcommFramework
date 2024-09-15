package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.util.ConfigLoader;

public class ProductPage extends BasePage {

    private final By productPageHeading = By.xpath("//h2[normalize-space()='Related products']");
    private final By productNameAdded = By.xpath("//h1[@class='product_title entry-title']");
    private final By addToCartBtn = By.xpath("//button[@name='add-to-cart']");
    private final By viewCartButton = By.xpath("//div[@role='alert']//a[@class='button wc-forward'][normalize-space()='View cart']");
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @Step
    public ProductPage load(){
        driver.get(ConfigLoader.getInstance().getURL()+"/product/blue-shoes/");
        return this;
    }
    @Step
    public String getProductPageHeading(){
        return driver.findElement(productPageHeading).getText();
    }
    @Step
    public String getProductName(){
        return driver.findElement(productNameAdded).getText();
    }
    @Step
    public void addToCartProductPage(){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBtn));
        e.click();
    }
    @Step
    public CartPage clickViewCartBtn(){
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
        e.click();
        return new CartPage(driver);
    }
}
