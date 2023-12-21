package com.anhtester.Bai7_Authentication_PUTmethod;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Oauth {
    @Test
    public void testOAuth1() {
        given()
                .auth()
                .oauth("consumerKey", "consumerSecret", "accessToken", "tokenSecret")
                .get("your end point URL");

    }

    @Test
    public void testOAuth2() {
        given()
                .auth()
                .oauth2("Access token")
                .get("your end point URL");

    }
}
