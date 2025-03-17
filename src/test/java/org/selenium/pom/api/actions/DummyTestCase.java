package org.selenium.pom.api.actions;

import org.selenium.pom.objects.User;
import org.selenium.pom.utils.FakerUtils;

public class DummyTestCase {
    public static void main(String[] args){
        String userName = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(userName)
                .setPassword("demousrpwd")
                .setEmail(userName + "@askomdch.com")
                ;

        SignUpAPI signupAPI = new SignUpAPI();
        signupAPI.register(user);
        System.out.println("REGISTER COOKIES : " + signupAPI.getCookies());
        CartAPI cartAPI = new CartAPI(signupAPI.getCookies());
        cartAPI.addToCart("1215", 1);
        System.out.println("CART COOKIES : " + cartAPI.getCookies());
    }
}
