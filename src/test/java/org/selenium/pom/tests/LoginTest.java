package org.selenium.pom.tests;

import org.selenium.pom.api.actions.CartAPI;
import org.selenium.pom.api.actions.SignUpAPI;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test(description = "User should be able to login during checkout")
    public void loginDuringCheckout() throws IOException, InterruptedException {
        String userName = "demouser" + new FakerUtils().generateRandomNumber();

        User user =
                new User()
                        .setUserName(userName)
                        .setPassword("demouserpwd")
                        .setEmail(userName + "@askomdch.com")
                ;

        SignUpAPI signUpAPI = new SignUpAPI();
        signUpAPI.register(user);
        CartAPI cartAPI = new CartAPI();
        Product product = new Product(1215);
        cartAPI.addToCart(product.getId() + "", 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBroowser(cartAPI.getCookies());
        checkoutPage.load();
        Thread.sleep(5000);
        checkoutPage.clickLoginLink()
                .login(user);
        Thread.sleep(5000);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }
}
