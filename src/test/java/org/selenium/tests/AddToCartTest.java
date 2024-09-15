package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.dataproviders.MyDataProvider;
import org.selenium.pages.CartPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.ProductPage;
import org.selenium.pages.StorePage;
import org.selenium.pojo.Product;
import org.selenium.util.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCartTest extends BaseTest {
    StorePage storePage;
    Product product;
    String pageHeading = "Related products";

    @Test (description = "Adding to cart from store page")
    public void addToCartFromStorePage() throws IOException {
        storePage= new StorePage(getDriver());
        product = new Product(1215);
        storePage.load();
        storePage.clickAddToCartButton(product.getName());
        CartPage cartPage = storePage.clickViewCartLink();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }
    @Test (dataProvider = "getFeaturedProduct", dataProviderClass = MyDataProvider.class,
            description = "Adding Featured Product from home page to cart")
    public void addFeaturedProductToCart(Product product) throws IOException {
        HomePage homePage = new HomePage(getDriver());
        homePage.clickAddToCartBtn(product.getName());
        CartPage cartPage = homePage.clickViewCartLink();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }
    @Test (description = "Adding to cart from product page")
    public void addToCartFromProductPage() throws IOException {
        product = new Product(1215);
        ProductPage productPage = new ProductPage(getDriver()).load();
        productPage.addToCartProductPage();
        CartPage cartPage = productPage.clickViewCartBtn();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }

}
