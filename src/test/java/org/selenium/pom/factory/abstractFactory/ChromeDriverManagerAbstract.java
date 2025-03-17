package org.selenium.pom.factory.abstractFactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.selenium.pom.factory.DriverManager;

public class ChromeDriverManagerAbstract extends DriverManagerAbstract {

    @Override
    public void startDriver() {
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }
}
