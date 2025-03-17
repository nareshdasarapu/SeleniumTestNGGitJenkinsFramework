package org.selenium.pom.tests;

import io.qameta.allure.*;
import org.apache.commons.io.FileUtils;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.dataproviders.MyDataProvider;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.ITestClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.internal.annotations.ITest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Epic("ABC-123")
@Feature("Add to cart")
public class AddToCartTest extends BaseTest {

    @Story("ABC-1234")
    @Link("https://example.org")
    @Link(name = "allure", type = "mylink")
    @TmsLink("ABC-12345")
    @Issue("ABC-1234567")
    @Description("This is the detailed description of the test case")
    @Test(description = "User should be able to add to cart from store page")
    @Parameters("browser")
    public void addToCartFromStorePage(@Optional String browser) throws IOException {
        Product product = new Product(1215);

        CartPage cartPage = new StorePage(getDriver())
                .load()
                .getProductThumbnails()
                .clickAddToCart(product.getName())
                .viewCart()
                ;
        Assert.assertTrue(cartPage.waitForPageToLoadUsingURLContains("/cart/"));
        addAttachmentToAllureReport(browser, "CartPage");
        Allure.attachment("Validation", "Cart page loaded successfully");
        Assert.assertEquals(cartPage.getProductName(), product.getName() + "asaas");
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class, description = "User should be able to add featured products to cart")
    public void addToCardFeaturedProducts(Product product){
        HomePage homePage = new HomePage(getDriver())
                .load();
        StorePage storePage = new StorePage(getDriver());
        CartPage cartPage = storePage
                .getProductThumbnails()
                .clickAddToCart(product.getName())
                .viewCart()
                ;

        Assert.assertEquals(cartPage.getProductName(), product.getName());
    }
}
