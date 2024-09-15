package org.selenium.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.api.actions.SignUpApi;
import org.selenium.base.BaseTest;
import org.selenium.pages.CheckoutPage;
import org.selenium.pojo.Login;
import org.selenium.pojo.Product;
import org.selenium.util.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    @Test(description = "User Login during Checkout")
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String username = FakerUtils.generateRandomName();
        Login login = new Login().setUsername(username)
                .setPassword("demopwd")
                .setEmail(username + "@testuser.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(login);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.clickShowLogin();
        checkoutPage.loginToApplication(login);
        Thread.sleep(5000);
        Assert.assertTrue(checkoutPage.getProductNameAddedInCart().contains(product.getName()));
    }
    @Test (description = "User Login fails during Checkout : Negative Scenario")
    public void loginFailsDuringCheckout() throws IOException, InterruptedException {
        String username = FakerUtils.generateRandomName();
        String errMessage = "Error: The username " +username+ " is not registered on this site. If you are unsure of your username, try your email address instead.";
        Login login = new Login().setUsername(username)
                .setPassword("demopwd")
                .setEmail(username);
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(login);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.clickShowLogin();
        checkoutPage.loginToApplication(login);
        Assert.assertEquals(checkoutPage.getLoginFailedErrorMessage(),errMessage);
    }
}
