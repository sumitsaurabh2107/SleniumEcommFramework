package org.selenium.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pojo.BillingAddress;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JacksonUtils {

    //---------*********Deserialization for pojo class ****************************
    public static BillingAddress deserializeJson(InputStream is, BillingAddress billingAddress) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, billingAddress.getClass());
    }

    //---------*********Deserialization for record class ****************************

    /*public static BillingAddressRe deserializeJson1(InputStream is) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, BillingAddressRe.class);
    }*/

    //---------*********Deserialization generic methods record class ****************************
    public static <T> T deserializeJson2(String fileName, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, T);
    }

    // For deserializing lists of objects
    public static <T> List<T> deserializeJsonArray(String fileName, Class<T> T) throws IOException {
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is, objectMapper.getTypeFactory().constructCollectionType(List.class, T));
    }

}

