package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.pages.AccountPage;
import org.selenium.pages.HomePage;
import org.selenium.pages.ProductPage;
import org.selenium.pages.StorePage;
import org.selenium.pojo.Product;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class NavigationTest extends BaseTest {

public String pageHeading = "Related products";

    @Test
    public void navigateToStoreFromHomePage(){
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.clickStoreMenuLink();
        Assert.assertEquals(storePage.getStorePageTitle(),"Store");
    }

    @Test
    public void navigateFromStoreToProductPage() throws IOException {
        Product product = new Product(1215);
        HomePage homePage = new HomePage(getDriver());
        StorePage storePage = homePage.clickStoreMenuLink();
        ProductPage productPage = storePage.clickProductImage(product.getName());
        Assert.assertEquals(productPage.getProductPageHeading(),pageHeading);
    }
    @Test
    public void navigateFromHomeToFeaturedProductPage() throws IOException, InterruptedException {
        Product product = new Product(1215);
        HomePage homePage = new HomePage(getDriver());
        ProductPage productPage = homePage.clickFeaturedProduct(product.getName());
        Assert.assertEquals(productPage.getProductPageHeading(),pageHeading);
    }
    @Test
    public void navigateToAccountFromHomePage(){
        HomePage homePage = new HomePage(getDriver());
        AccountPage accountPage = homePage.clickAccountMenuLink();
        Assert.assertEquals(accountPage.getAccountPageTitle(),"Account");
    }
}
