package org.selenium.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.base.BaseTest;
import org.selenium.dataproviders.MyDataProvider;
import org.selenium.pages.CheckoutPage;
import org.selenium.pojo.BillingAddress;
import org.selenium.pojo.Login;
import org.selenium.pojo.Product;
import org.selenium.pojo.UnitedStatesStates;
import org.selenium.util.FakerUtils;
import org.selenium.util.GeneralUtils;
import org.selenium.util.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutTest extends BaseTest {
    String orderConfirmText = "Thank you. Your order has been received.";

    @Test (description = "Guest User Checkout Using Direct Bank Transfer")
    public void guestCheckoutUsingDirectBankTransfer() throws IOException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectDirectBankTransferRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
    }

    @Test (description = "Logging in User and Checkout Using Direct Bank Transfer")
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        String username = FakerUtils.generateRandomName();
        Login login = new Login().setUsername(username)
                .setPassword("demopwd")
                .setEmail(username + "@testuser.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(login);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectDirectBankTransferRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
        System.out.println(username);
    }
    @Test (description = "Guest User Checkout Using COD")
    public void guestCheckoutUsingCashOnDelivery() throws IOException {
        GeneralUtils generalUtils = new GeneralUtils(getDriver());
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectCashOnDeliveryRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
    }
    @Test (description = "Logging in User and Checkout Using COD")
    public void loginAndCheckoutUsingCashOnDelivery() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson2("myBillingAddress.json",BillingAddress.class);
        String username = FakerUtils.generateRandomName();
        Login login = new Login().setUsername(username)
                .setPassword("demopwd")
                .setEmail(username + "@testuser.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(login);

        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(signUpApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingDetails(billingAddress);
        checkoutPage.selectCashOnDeliveryRadioBtn();
        checkoutPage.clickPlaceOrder();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(),orderConfirmText);
        System.out.println(username);
    }
    @Test (dataProvider = "getStateTax", dataProviderClass = MyDataProvider.class,
        description = "Guest User Tax Validation for States")
    public void guestUserTaxValidation(UnitedStatesStates unitedStatesStates) {
        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        checkoutPage.setBillingDetails(unitedStatesStates);
        System.out.println(checkoutPage.getTaxAmount());
    }

}
