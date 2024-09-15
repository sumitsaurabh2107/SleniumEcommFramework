package org.selenium.tests;

import org.selenium.base.BaseTest;
import org.selenium.pages.ProductPage;
import org.selenium.pages.StorePage;
import org.selenium.pojo.Product;
import org.selenium.util.FakerUtils;
import org.selenium.util.GeneralUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class SearchTest extends BaseTest {
    String textToSearch = "blue";
    String urlMatch = "product";
    String nonExistingProductMessage = "No products were found matching your selection.";

    @Test (description = "Searching the product with partial match")
    public void searchWithPartialMatch() throws InterruptedException {
        StorePage storePage = new StorePage(getDriver()).load();
        storePage.searchProduct(textToSearch);
        if (!getDriver().getCurrentUrl().contains(urlMatch)){
            getDriver().wait(2000);
            Assert.assertEquals(storePage.getStorePageTitle(),"Search results: “blue”");
        } else{
            Assert.assertEquals(storePage.getStorePageTitle(),"Search results: “blue”");
        }

    }
    @Test (description = "Searching the product with exact match")
    public void searchWithExactMatch() throws IOException {
        Product product = new Product(1215);
        StorePage storePage = new StorePage(getDriver()).load();
        ProductPage productPage = storePage.clickSearchWithExactName(product.getName());
        Assert.assertEquals(productPage.getProductName(),product.getName());
    }
    @Test (description = "Searching non-existing product ; negative scenario")
    public void searchWithNonExistingProduct() throws InterruptedException {
        StorePage storePage = new StorePage(getDriver()).load();
        storePage.searchProduct(FakerUtils.generateRandomProductName());
        Assert.assertEquals(storePage.nonExistingProductMessage(),nonExistingProductMessage);
        Thread.sleep(5000);
    }
}
