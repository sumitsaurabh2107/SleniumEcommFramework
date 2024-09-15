package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.constants.EndPoints;
import org.selenium.util.ConfigLoader;

public class StorePage extends BasePage {

    private final By searchProductTextField = By.xpath("//input[@type='search']");
    private final By searchButton = By.xpath("//button[@value='Search']");
    private final By viewCartButton = By.xpath("//a[@title='View cart']");
    private final By storeTitleText = By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    private final By nonExistingProduct = By.xpath("//p[@class='woocommerce-info woocommerce-no-products-found']");
    private By addToCartButton (String productName){
        return By.xpath("//a[@aria-label='Add “"+ productName +"” to your cart']");
    }
    private By productImage (String productName){
        return By.xpath("//h2[normalize-space()='"+ productName +"']");
    }
    public StorePage(WebDriver driver) {
        super(driver);
    }

    @Step
    public StorePage load(){
        driver.get(ConfigLoader.getInstance().getURL()+ EndPoints.STOREPAGE.getEndpoints());
        return this;
    }
    @Step
    public void searchProduct(String productName){
        driver.findElement(searchProductTextField).sendKeys(productName);
        driver.findElement(searchButton).click();
    }
    @Step
    public void clickAddToCartButton(String productName){
        By addToCartButton = addToCartButton(productName);
        driver.findElement(addToCartButton).click();
    }
    @Step
    public String getStorePageTitle(){
        return driver.findElement(storeTitleText).getText();
    }
    public CartPage clickViewCartLink() {
        WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(viewCartButton));
        e.click();
        return new CartPage(driver);
    }
    @Step
    public ProductPage clickProductImage(String productName){
        By productImage = productImage(productName);
        driver.findElement(productImage).click();
        return new ProductPage(driver);
    }
    @Step
    public ProductPage clickSearchWithExactName(String productName){
        driver.findElement(searchProductTextField).sendKeys(productName);
        driver.findElement(searchButton).click();
        return new ProductPage(driver);
    }
    @Step
    public String nonExistingProductMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(nonExistingProduct)).getText();
    }
}
