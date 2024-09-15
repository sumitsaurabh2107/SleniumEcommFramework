package org.selenium.util;

import com.github.javafaker.Faker;
import com.github.javafaker.Name;

public class FakerUtils {
    public static String generateRandomName(){
        Faker faker = new Faker();
        return faker.name().firstName();
    }
    public static String generateRandomProductName(){
        Faker faker = new Faker();
        return faker.name().lastName();
    }
}
