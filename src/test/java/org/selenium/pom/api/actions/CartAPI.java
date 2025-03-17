package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class CartAPI {

    private Cookies cookies;

    public CartAPI(){

    }

    public CartAPI(Cookies cookies){
        this.cookies = cookies;
    }

    public Cookies getCookies(){
        return cookies;
    }

    public Response addToCart(String productId, int quantity){
        Header header = new Header("Content-Type", "application/x-www-form-urlencoded");
        Headers headers = new Headers(header);
        HashMap<String, Object> formParams = new HashMap<String, Object>();

        formParams.put("product_sku", "");
        formParams.put("product_id", productId);
        formParams.put("quantity", quantity);

        if(cookies == null){
            cookies = new Cookies();
        }

        Response response =
                given()
                        .baseUri(ConfigLoader.getInstance().getBaseUrl())
                        .cookies(cookies)
                        .headers(headers)
                        .log().all()
                        .when()
                        .formParams(formParams)
                        //.param("wc-ajax", "add_to_cart")
                        .post("?wc-ajax=add_to_cart")
                        //.post()
                        .then()
                        .log().all()
                        .extract()
                        .response();

        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to add product " + productId +  " to the cart. Response code : "+ response.getStatusCode());
        }

        this.cookies = response.getDetailedCookies();
        return response;
    }
}
