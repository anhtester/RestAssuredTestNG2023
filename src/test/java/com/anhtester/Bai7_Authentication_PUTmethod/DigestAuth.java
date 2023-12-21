package com.anhtester.Bai7_Authentication_PUTmethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DigestAuth {
    @Test
    public void testDigestAuth() {
        RequestSpecification httpRequest = given().auth().digest("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        response.then().statusCode(200);
    }
}
