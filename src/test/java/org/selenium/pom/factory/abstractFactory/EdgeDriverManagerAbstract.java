package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.selenium.pom.factory.DriverManager;

public class EdgeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    public void startDriver() {
        WebDriverManager.edgedriver().cachePath("Drivers").setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
    }
}
