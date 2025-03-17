package org.selenium.pom.pages.components;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.StorePage;

public class ProductThumbnails extends BasePage {

    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnails(WebDriver driver) {
        super(driver);
    }

    @Step
    private By getAddToCartBtn(String productName){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']")));
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductThumbnails clickAddToCart(String productName){
        By addToCartBtn = getAddToCartBtn(productName);
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
        return this;
    }

    @Step
    public CartPage viewCart(){
        wait.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return new CartPage(driver);
    }
}
