package org.selenium.pom.base;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void load(String endPoint) {
        driver.get(ConfigLoader.getInstance().getBaseUrl() + endPoint);
    }

    public void waitForOverlaysToDisappear(By overlay) {
        List<WebElement> overlays = driver.findElements(overlay);
        if (!overlays.isEmpty()) {
            System.out.println("Number of overlays: " + overlays.size());
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
        } else {
            System.out.println("Overlays are not present");
        }
    }

    @Step
    public Boolean waitForPageToLoadUsingURLContains(String urlPartialMatch) {
        return wait.until(ExpectedConditions.urlContains(urlPartialMatch));
    }
}
