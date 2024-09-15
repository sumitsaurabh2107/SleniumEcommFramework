package org.selenium.tests;

import org.selenium.api.actions.CartApi;
import org.selenium.base.BaseTest;
import org.selenium.constants.Coupon;
import org.selenium.pages.CartPage;
import org.selenium.pages.CheckoutPage;
import org.selenium.pages.StorePage;
import org.selenium.pojo.Product;
import org.selenium.util.GeneralUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class CouponTest extends BaseTest {
    StorePage storePage;
    Product product;
    @Test (description = "Validating different Coupon")
    public void validateCouponTest() throws IOException {
        storePage= new StorePage(getDriver());
        product = new Product(1215);
        storePage.load();
        storePage.clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.clickViewCartLink();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
        String totalPriceBeforeCoupon = cartPage.getTotalPrice();
        double priceBeforeCoupon = Double.parseDouble(totalPriceBeforeCoupon.replace("$", ""));
        cartPage.enterCouponCode(Coupon.OFFCART5.getCoupon());
        cartPage.clickApplyCoupon();
        Assert.assertEquals(cartPage.getCouponAppliedMessage(),"Coupon code applied successfully.");
        String totalPriceAfterCoupon = cartPage.getTotalPrice();
        double priceAfterCoupon = Double.parseDouble(totalPriceAfterCoupon.replace("$", ""));
        System.out.println(priceBeforeCoupon);
        System.out.println(priceAfterCoupon);
    }
}
