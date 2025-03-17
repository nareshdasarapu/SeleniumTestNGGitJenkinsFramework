package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.ChromeDriverManager;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.EdgeDriverManager;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(DriverType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case EDGE -> {
                return new EdgeDriverManagerAbstract();
            }
            default -> throw new RuntimeException("Invalid driver: " + driverType);
        }
    }
}
