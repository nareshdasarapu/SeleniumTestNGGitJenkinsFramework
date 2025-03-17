package org.selenium.pom.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    /*private final By productName = By.cssSelector("td[class = 'product-name'] > a");
    private final By checkoutBtn = By.cssSelector(".checkout-button");
    private final By cartPageTitle = By.cssSelector("h1.has-text-align-center");*/

    @FindBy(css = "td[class = 'product-name'] > a")
    private WebElement productName;

    @FindBy(how = How.CSS, using = ".checkout-button")
    @CacheLookup private WebElement checkoutBtn;

    @FindBy(how = How.CSS, using = "h1.has-text-align-center")
    private WebElement cartPageTitle;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isLoaded(){
        return wait.until(ExpectedConditions.textToBePresentInElement(cartPageTitle, "Cart"));
    }

    @Step
    public String getProductName(){
        return wait.until(ExpectedConditions.visibilityOf(productName)).getText();
    }

    public CheckoutPage checkOut(){
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
        return new CheckoutPage(driver);
    }
}
