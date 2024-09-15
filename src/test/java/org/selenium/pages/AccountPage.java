package org.selenium.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.selenium.base.BasePage;
import org.selenium.constants.EndPoints;
import org.selenium.util.ConfigLoader;
import org.selenium.util.GeneralUtils;

import java.util.List;

public class AccountPage extends BasePage {
    GeneralUtils generalUtils;
    private final By ordersLink = By.xpath("//a[normalize-space()='Orders']");
    private final By orderTableHeading = By.xpath("//a[normalize-space()='Orders']");
    private final By viewButton = By.xpath("//tbody/tr[1]/td[5]/a[1]");
    private final By noticeHeading = By.xpath("(//div[@class='woocommerce-MyAccount-content']//p)[1]");
    private final By billingAddress = By.xpath("(//address)[1]");
    private final By accountPageTitle = By.xpath("//h1[@class='has-text-align-center']");
    public AccountPage(WebDriver driver) {
        super(driver);
    }
    @Step
    public void clickOrdersLink (){
        driver.findElement(ordersLink).click();
    }
    @Step
    public String getOrderTableHeading(){
       String tableHeadings = "";
        List <WebElement> tableHeading = driver.findElements(orderTableHeading);
        for (WebElement ele : tableHeading){
             tableHeadings = ele.getText();
        }
        return tableHeadings;
    }

    @Step
    public AccountPage load(){
        driver.get(ConfigLoader.getInstance().getURL()+ EndPoints.ACCOUNTAPI.getEndpoints());
        return this;
    }
    @Step
    public void clickViewButton(){
        generalUtils = new GeneralUtils(driver);
        generalUtils.waitForElementToBeVisible(viewButton).click();
    }
    @Step
    public String getNoticeHeading(){
        generalUtils = new GeneralUtils(driver);
        return generalUtils.waitForElementToBeVisible(noticeHeading).getText();
    }
    @Step
    public String getAddress (){
        generalUtils = new GeneralUtils(driver);
        return generalUtils.waitForElementToBeVisible(billingAddress).getText();
    }
    @Step
    public String getAccountPageTitle(){
        generalUtils = new GeneralUtils(driver);
        return generalUtils.waitForElementToBeVisible(accountPageTitle).getText();
    }
}
