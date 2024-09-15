package org.selenium.util;

import org.selenium.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

   private final Properties properties;
   private static ConfigLoader configLoader;

   private ConfigLoader(){
       String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
       switch (EnvType.valueOf(env)){
           case PROD:
               properties = PropertyUtils.propertyLoader("src/test/resources/prod.config.properties");
               break;
           case STAGE:
               properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");
               break;
           default:
               throw new IllegalStateException("Invalid environment type "+env);
       }

   }

   public static ConfigLoader getInstance(){
       if(configLoader == null){
           configLoader = new ConfigLoader();
       }
       return configLoader;
   }

   public String getURL(){
       String prop = properties.getProperty("url");
       if (prop !=null) return prop;
       else throw new RuntimeException("url is not specified in config.properties file");
   }
    public String getUsername(){
        String prop = properties.getProperty("username");
        if (prop !=null) return prop;
        else throw new RuntimeException("username is not specified in config.properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("password");
        if (prop !=null) return prop;
        else throw new RuntimeException("password is not specified in config.properties file");
    }
}
