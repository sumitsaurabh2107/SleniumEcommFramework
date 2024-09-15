package org.selenium.constants;

public enum EndPoints {
    ACCOUNTAPI("/account/"),
    CARTAPI("/?wc-ajax=add_to_cart"),
    CARTPAGE("/cart/"),
    STOREPAGE("/store"),
    CHECKOUTPAGE("/checkout/");


    private final String endpoints;

    EndPoints(String endpoints) {
        this.endpoints = endpoints;
    }
    public String getEndpoints() {
        return endpoints;
    }
}
