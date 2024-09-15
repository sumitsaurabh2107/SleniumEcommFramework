package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.base.BasePage;
import org.selenium.constants.Coupon;
import org.selenium.constants.EndPoints;
import org.selenium.util.ConfigLoader;
import org.selenium.util.GeneralUtils;

public class CartPage extends BasePage {

    /*private final By productTitle = By.xpath("//td[@class='product-name']//a");
    private final By proceedToCheckoutButton = By.xpath("//a[@class='checkout-button button alt wc-forward']");*/

    @FindBy(xpath = "//td[@class='product-name']//a")
    private WebElement productTitle;

    @FindBy(xpath = "//a[@class='checkout-button button alt wc-forward']")
    private WebElement proceedToCheckoutButton;

    @FindBy (id = "coupon_code")
    private WebElement couponCodeField;

    @FindBy(xpath = "//button[@name='apply_coupon']")
    private WebElement applyCouponBtn;

    @FindBy(xpath = "//tr[@class='order-total']//bdi[1]")
    private WebElement totalPrice;
    @FindBy(className = "woocommerce-message")
    private WebElement couponAppliedMessage;

    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    GeneralUtils generalUtils = new GeneralUtils(driver);
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @Step
    public CartPage load(){
        driver.get(ConfigLoader.getInstance().getURL()+ EndPoints.CARTPAGE.getEndpoints());
        return this;
    }
    @Step
    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productTitle)).getText();
    }
    @Step
    public CheckoutPage clickCheckoutButton(){
        wait.until(ExpectedConditions.elementToBeClickable(proceedToCheckoutButton)).click();
        return new CheckoutPage(driver);
    }
    @Step
    public void enterCouponCode(String couponCode){
       switch (couponCode.toLowerCase()){
           case "freeship":
               couponCodeField.sendKeys(Coupon.FREESHIP.getCoupon());
               break;
           case "offcart5":
               couponCodeField.sendKeys(Coupon.OFFCART5.getCoupon());
               break;
           case "off25":
               couponCodeField.sendKeys(Coupon.OFF25.getCoupon());
               break;
           default: throw new RuntimeException("Please Enter valid coupon..!! "+couponCode);
       }
    }
    @Step
    public void clickApplyCoupon(){
        wait.until(ExpectedConditions.visibilityOf(applyCouponBtn)).click();
    }
    @Step
    public String getCouponAppliedMessage(){
        return wait.until(ExpectedConditions.visibilityOf(couponAppliedMessage)).getText();
    }
    @Step
    public String getTotalPrice(){
        generalUtils.waitForOverlaysToDisappear(overlay);
       return wait.until(ExpectedConditions.visibilityOf(totalPrice)).getText();
    }
}
