package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.SIT));

        switch (EnvType.valueOf(env)) {
            case SIT:
                properties = PropertyUtils.propertyLoader("src/test/Resources/sit_config.properties");
                break;
            case UAT:
                properties = PropertyUtils.propertyLoader("src/test/Resources/uat_config.properties");
                break;
            default:
                throw new RuntimeException("Invalid env type : " + env);
        }

    }

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null){
            return prop;
        }else{
            throw new RuntimeException("Property baseUrl is not specified in the config.properties file");
        }
    }

    public String getUserName(){
        String prop = properties.getProperty("userName");
        if(prop != null){
            return prop;
        }else{
            throw new RuntimeException("Property userName is not specified in the config.properties file");
        }
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null){
            return prop;
        }else{
            throw new RuntimeException("Property password is not specified in the config.properties file");
        }
    }

    public String getEmail(){
        String prop = properties.getProperty("email");
        if(prop != null){
            return prop;
        }else{
            throw new RuntimeException("Property email is not specified in the config.properties file");
        }
    }
}
