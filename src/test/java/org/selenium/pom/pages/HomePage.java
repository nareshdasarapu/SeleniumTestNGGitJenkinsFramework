package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.components.MyHeader;
import org.selenium.pom.pages.components.ProductThumbnails;

public class HomePage extends BasePage {

    private MyHeader myHeader;
    private ProductThumbnails productThumbnails;

    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnails getProductThumbnails() {
        return productThumbnails;
    }

    public HomePage(WebDriver driver) {

        super(driver);
        myHeader = new MyHeader(driver);
        productThumbnails = new ProductThumbnails(driver);
    }



    public HomePage load(){
        load("/");
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }
}
