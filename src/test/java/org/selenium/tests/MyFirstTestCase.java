package org.selenium.tests;

import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.selenium.base.BaseTest;
import org.selenium.pages.CartPage;
import org.selenium.pages.CheckoutPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.StorePage;
import org.selenium.pojo.BillingAddress;
import org.selenium.pojo.Login;
import org.selenium.pojo.Product;
import org.selenium.util.ConfigLoader;
import org.selenium.util.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;


public class MyFirstTestCase extends BaseTest {

    String textToSearch = "blue";
    String orderConfirmText = "Thank you. Your order has been received.";


   /* @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.searchProduct(textToSearch);
        storePage.clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.clickViewCartLink();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectDirectBankTransferRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
    }
    @Test
    public void registeredUserCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        Login login = new Login(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.clickStoreMenuLink();
        storePage.searchProduct(textToSearch);
        storePage.clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.clickViewCartLink();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        CheckoutPage checkoutPage = cartPage.clickCheckoutButton();
        checkoutPage.clickShowLogin();
        checkoutPage.loginToApplication(login);
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectDirectBankTransferRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
    }*/
}
