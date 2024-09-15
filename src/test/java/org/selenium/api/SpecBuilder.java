package org.selenium.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.selenium.util.ConfigLoader;

public class SpecBuilder {

    public static RequestSpecification getRequestSpec(){
       RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
               .setBaseUri(ConfigLoader.getInstance().getURL()).log(LogDetail.ALL);
        return requestSpecBuilder.build();
    }
    public static ResponseSpecification getResponseSpec(){
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.PARAMS)
                .log(LogDetail.STATUS)
                .log(LogDetail.HEADERS)
                .log(LogDetail.COOKIES);
        return responseSpecBuilder.build();
    }
}
