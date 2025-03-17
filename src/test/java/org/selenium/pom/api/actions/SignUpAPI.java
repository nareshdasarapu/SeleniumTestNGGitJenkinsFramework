package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpAPI {

    private Cookies cookies;

    public Cookies getCookies(){
        return cookies;
    }

    public String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        return response.htmlPath().getString("**.findAll { it.@name == 'woocommerce-register-nonce' }.@value");
    }

    public String fetchRegisterNonceValueUsingJSoup(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element = doc.selectFirst("#woocommerce-register-nonce");
        assert element != null;
        return element.attr("value");
    }

    public Response getAccount(){
        Cookies cookies = new Cookies();
        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .cookies(cookies)
                        .log().all()
                        .when()
                        .get("/account")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to fetch account, status code: " + response.getStatusCode());
        }
        return response;
    }

    public Response register(User user){
        Cookies cookies = new Cookies();
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, String> formParams = new HashMap<String, String>();

        formParams.put("username", user.getUserName());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce", fetchRegisterNonceValueUsingJSoup());
        formParams.put("register", "Register");

        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .cookies(cookies)
                        .headers(headers)
                        .log().all()
                        .when()
                        .formParams(formParams)
                        .post("/account")
                        .then()
                        .log().all()
                        .extract()
                        .response();

        //if(!(response.getStatusCode() == 200 || response.getStatusCode() == 302)){
        if(response.getStatusCode() != 302){
            throw new RuntimeException("Failed to register account, status code: " + response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }
}
