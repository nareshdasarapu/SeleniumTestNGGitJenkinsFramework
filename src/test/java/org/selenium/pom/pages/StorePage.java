package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.ProductThumbnails;

import java.time.Duration;

public class StorePage extends BasePage {

    private final By searchFld = By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By titleFld = By.cssSelector("#main > div > header > h1");

    @Step
    public ProductThumbnails getProductThumbnails() {
        return productThumbnails;
    }

    private ProductThumbnails productThumbnails;

    public StorePage(WebDriver driver) {

        super(driver);
        productThumbnails = new ProductThumbnails(driver);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.urlContains("/store"));
    }

    @Step
    public StorePage load(){
        load("/store");
        return this;
    }

    public StorePage search(String txt){
        inputSearchField(txt).clickSearchBtn();
        return this;
    }

    public StorePage inputSearchField(String txt){
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchFld)).sendKeys(txt);
        return this;
    }

    public StorePage clickSearchBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        return this;
    }

    public String getTitle(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(titleFld)).getText();
    }
}
