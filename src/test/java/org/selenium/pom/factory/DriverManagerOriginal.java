package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManagerOriginal {

    public WebDriver initiializeDriver(){
        String browser = System.getProperty("browser", "EDGE");

        WebDriver driver = switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                yield new ChromeDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().cachePath("Drivers").setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Invalid browser name: " + browser);
        };

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }


    public WebDriver initializeDriver(String browser){
       WebDriver driver = switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                yield new ChromeDriver();
            }
            case EDGE -> {
                WebDriverManager.edgedriver().cachePath("Drivers").setup();
                yield new EdgeDriver();
            }
            default -> throw new IllegalArgumentException("Invalid browser name: " + browser);
        };

        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
