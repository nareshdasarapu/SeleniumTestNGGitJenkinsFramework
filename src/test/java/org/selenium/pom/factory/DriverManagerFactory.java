package org.selenium.pom.factory;

import org.selenium.pom.constants.DriverType;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManager();
            }
            case EDGE -> {
                return new EdgeDriverManager();
            }
            default -> throw new RuntimeException("Invalid driver: " + driverType);
        }
    }
}
