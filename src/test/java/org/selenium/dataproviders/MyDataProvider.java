package org.selenium.dataproviders;

import org.selenium.pojo.Product;
import org.selenium.pojo.UnitedStatesStates;
import org.selenium.util.JacksonUtils;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class MyDataProvider {
    @DataProvider(name ="getFeaturedProduct", parallel = false)
    public Product[] getFeaturedProducts() throws IOException {
        return JacksonUtils.deserializeJson2("product.json",Product[].class);
    }
    @DataProvider(name ="getStateTax", parallel = false)
    public UnitedStatesStates[] getStateTax() throws IOException {
        return JacksonUtils.deserializeJson2("state.json",UnitedStatesStates[].class);
    }
}
