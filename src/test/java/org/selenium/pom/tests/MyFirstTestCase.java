package org.selenium.pom.tests;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.BillingAddress;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.JacksonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class MyFirstTestCase extends BaseTest {

    //    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

        String searchString = "Blue";

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader().navigateToStoreUsingMenu().search(searchString);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchString + "”");

        storePage.getProductThumbnails().clickAddToCart(product.getName());
        CartPage cartPage = storePage.getProductThumbnails().viewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkOut()
                .setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }

    //    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws InterruptedException, IOException {

        String searchString = "Blue";

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);
//      User user = JacksonUtils.deserializeJson("users.json", User.class);
        User user = new User(ConfigLoader.getInstance().getUserName(), ConfigLoader.getInstance().getPassword(), ConfigLoader.getInstance().getEmail());

        StorePage storePage = new HomePage(getDriver())
                .load().getMyHeader()
                .navigateToStoreUsingMenu()
                .search(searchString);

        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchString + "”");
        storePage.getProductThumbnails().clickAddToCart(product.getName());
        CartPage cartPage = storePage.getProductThumbnails().viewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkOut();
        checkoutPage.clickLoginLink()
                .login(user);

        checkoutPage.setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
        ;
    }

    //    @Test(groups = {"grouptest"})
    public void guestCheckoutUsingDirectBankTransferGroup() throws InterruptedException, IOException {

        String searchString = "Blue";

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json", BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader()
                .navigateToStoreUsingMenu()
                .search(searchString);
        Assert.assertEquals(storePage.getTitle(), "Search results: “" + searchString + "”");
        storePage.getProductThumbnails().clickAddToCart(product.getName());
        CartPage cartPage = storePage.getProductThumbnails().viewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(), product.getName());
        CheckoutPage checkoutPage = cartPage.checkOut()
                .setBillingAddress(billingAddress);
        checkoutPage.selectDirectBankTransfer();
        checkoutPage.placeOrder();
        Assert.assertEquals(checkoutPage.getSuccessNotice(), "Thank you. Your order has been received.");
    }
}
