package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.selenium.base.BasePage;
import org.selenium.constants.EndPoints;
import org.selenium.pojo.BillingAddress;
import org.selenium.pojo.Login;
import org.selenium.pojo.UnitedStatesStates;
import org.selenium.util.ConfigLoader;
import org.selenium.util.GeneralUtils;

public class CheckoutPage extends BasePage {

    private final By firstName = By.xpath("//input[@id='billing_first_name']");
    private final By lastName = By.xpath("//input[@id='billing_last_name']");
    private final By countryDropDown = By.id("billing_country");
    private final By streetAddress = By.xpath("//input[@id='billing_address_1']");
    private final By cityName = By.id("billing_city");
    private final By stateName = By.id("billing_state");
    private final By zipCode = By.xpath("//input[@id='billing_postcode']");
    private final By emailId = By.xpath("//input[@id='billing_email']");
    private final By clickHeretoLoginLink = By.xpath("//a[@class='showlogin']");
    private final By userNameTextField = By.id("username");
    private final By passwordTextField = By.id("password");
    private final By loginButton = By.xpath("//button[@name='login']");
    private final By productAddedToCart = By.xpath("//td[@class='product-name']");
    private  final By taxAmount = By.xpath("//*[@id='order_review']/table/tfoot/tr[3]");
    private final By placeOrderButton = By.xpath("//button[@id='place_order']");
    private final By confirmationMessage = By.xpath("//p[@class='woocommerce-notice woocommerce-notice--success woocommerce-thankyou-order-received']");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    private final By cashOnDeliveryRadioBtn = By.id("payment_method_cod");
    private final By loginFailedErrorMessage = By.xpath("//ul[@class='woocommerce-error']");
    private

    GeneralUtils generalUtils = new GeneralUtils(driver);
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public By getPlaceOrderButton() {
        return placeOrderButton;
    }

    @Step
    public CheckoutPage load(){
        driver.get(ConfigLoader.getInstance().getURL()+ EndPoints.CHECKOUTPAGE.getEndpoints());
        return this;
    }
    @Step
    public void enterFirstName(String firstName){
        WebElement e = generalUtils.waitForElementToBeVisible(this.firstName);
        e.clear();
        e.sendKeys(firstName);
    }
    @Step
    public void enterLastname(String lastName){
        WebElement e = generalUtils.waitForElementToBeVisible(this.lastName);
        e.clear();
        e.sendKeys(lastName);
    }
    @Step
    public void selectCountry(String countryName){
        WebElement e = generalUtils.waitForElementToBeVisible(countryDropDown);
        Select select = new Select(e);
        select.selectByValue(countryName);
    }
    @Step
    public void enterStreetAddress(String streetAddress){
        WebElement e = generalUtils.waitForElementToBeVisible(this.streetAddress);
        e.clear();
        e.sendKeys(streetAddress);
    }
    @Step
    public void enterCity(String city){
        WebElement e = generalUtils.waitForElementToBeVisible(cityName);
        e.clear();
        e.sendKeys(city);
    }
    @Step
    public void selectState(String stateName){
       WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(this.stateName));
       Select select = new Select(e);
       select.selectByValue(stateName);
    }
    @Step
    public void enterZipCode(String zipCode){
        WebElement e = generalUtils.waitForElementToBeVisible(this.zipCode);
        e.clear();
        e.sendKeys(zipCode);
    }
    @Step
    public void enterEmail(String email){
        WebElement e = generalUtils.waitForElementToBeVisible(emailId);
        e.clear();
        e.sendKeys(email);
    }
    @Step
    public void setBillingDetails(BillingAddress billingAddress) {
        enterFirstName(billingAddress.getFirstName());
        enterLastname(billingAddress.getLastName());
        selectCountry(billingAddress.getCountry());
        enterStreetAddress(billingAddress.getStreetAddress());
        enterCity(billingAddress.getCity());
        selectState(billingAddress.getState());
        enterZipCode(billingAddress.getZipCode());
        enterEmail(billingAddress.getEmail());
    }
    @Step
    public void setBillingDetails(UnitedStatesStates unitedStatesStates) {
        enterFirstName(unitedStatesStates.getFirstName());
        enterLastname(unitedStatesStates.getLastName());
        selectCountry(unitedStatesStates.getCountry());
        enterStreetAddress(unitedStatesStates.getStreetAddress());
        enterCity(unitedStatesStates.getCity());
        selectState(unitedStatesStates.getState());
        enterZipCode(unitedStatesStates.getZipCode());
        enterEmail(unitedStatesStates.getEmail());
    }
    @Step
    public String getTaxAmount(){
        return driver.findElement(taxAmount).getText();
    }
    @Step
    public void selectDirectBankTransferRadioBtn(){
        WebElement e = generalUtils.waitForElementToBeVisible(directBankTransferRadioBtn);
        if (!e.isSelected()){
            e.click();
        }
    }
    @Step
    public void selectCashOnDeliveryRadioBtn(){
        generalUtils.waitForOverlaysToDisappear(overlay);
        WebElement e = generalUtils.waitForElementToBeVisible(cashOnDeliveryRadioBtn);
        if (!e.isSelected()){
            e.click();
        }
    }
    @Step
    public void clickPlaceOrder(){
        generalUtils.waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderButton).click();
    }
    @Step
    public String getConfirmationMessage(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage)).getText();
    }
    @Step
    public void clickShowLogin(){
        driver.findElement(clickHeretoLoginLink).click();
    }
    @Step
    public void loginToApplication(Login login){
        WebElement e = generalUtils.waitForElementToBeVisible(userNameTextField);
        WebElement e1 = generalUtils.waitForElementToBeVisible(passwordTextField);
        e.sendKeys(login.getUsername());
        e1.sendKeys(login.getPassword());
        driver.findElement(loginButton).click();
    }
    @Step
    public String getProductNameAddedInCart(){
        return driver.findElement(productAddedToCart).getText();
    }
    @Step
    public String getLoginFailedErrorMessage(){
        return generalUtils.waitForElementToBeVisible(loginFailedErrorMessage).getText();
    }
}
