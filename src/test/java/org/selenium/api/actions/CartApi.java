package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.api.ApiRequest;
import org.selenium.constants.EndPoints;
import org.selenium.pojo.Product;
import org.selenium.util.ConfigLoader;
import org.selenium.util.JacksonUtils;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartApi {
    private Cookies cookies;
    public CartApi(){};
    public CartApi(Cookies cookies){
        this.cookies =cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }
    public Response addToCart(int productId, int Quantity){
        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,Object> productDetails = new HashMap<>();
        productDetails.put("product_sku", "");
        productDetails.put("product_id",productId);
        productDetails.put("quantity",Quantity);

        if (cookies == null){
            cookies = new Cookies();
        }

        Response response = ApiRequest.post(EndPoints.CARTAPI.getEndpoints(),headers,productDetails,cookies);

        if (response.statusCode() != 200){
            System.out.println("Failed to add product to the cart..!!"+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
}
