package org.selenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.api.ApiRequest;
import org.selenium.constants.EndPoints;
import org.selenium.pojo.Login;
import org.selenium.util.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {
    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    private Response getAccount(){
        Cookies cookies = new Cookies();
        Response response = ApiRequest.get(EndPoints.ACCOUNTAPI.getEndpoints(),cookies);

        if (response.statusCode() != 200){
            System.out.println("Failed to fetch the account..!!"+response.getStatusCode());
        }
        return response;
    }

    private String fetchRegistrationNonceValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }
    private String fetchRegistrationNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value");
    }
    private String fetchLoginNonceValueUsingJsoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-login-nonce");
        return element.attr("value");
    }

    public Response register(Login login){
        Cookies cookies = new Cookies();
        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,Object> userCred = new HashMap<>();
        userCred.put("username", login.getUsername());
        userCred.put("email",login.getEmail());
        userCred.put("password",login.getPassword());
        userCred.put("woocommerce-register-nonce",fetchRegistrationNonceValueUsingJsoup());
        userCred.put("register","Register");

        Response response = ApiRequest.post(EndPoints.ACCOUNTAPI.getEndpoints(), headers,userCred,cookies);

        if (response.statusCode() != 302){
            System.out.println("Failed to register the account..!!"+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }
    public Response loginApp(Login login){
        Cookies cookies = new Cookies();
        Header header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String,Object> userCred = new HashMap<>();
        userCred.put("username", login.getUsername());
        userCred.put("email",login.getEmail());
        userCred.put("password",login.getPassword());
        userCred.put("woocommerce-login-nonce",fetchLoginNonceValueUsingJsoup());
        userCred.put("login","Log in");

        Response response = ApiRequest.post(EndPoints.ACCOUNTAPI.getEndpoints(), headers,userCred,cookies);

        if (response.statusCode() != 302){
            System.out.println("Failed to login into the account..!!"+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }

}
