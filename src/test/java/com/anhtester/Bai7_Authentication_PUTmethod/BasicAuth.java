package com.anhtester.Bai7_Authentication_PUTmethod;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BasicAuth {
    @Test
    public void getData() {
        RequestSpecification httpRequest = given();
        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        response.then().statusCode(200);
    }

    @Test
    public void testBasicAuth() {
        RequestSpecification httpRequest = given().auth().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());

        response.then().statusCode(200);
    }

    @Test
    public void testPreemptiveBasicAuth() {
        RequestSpecification httpRequest = given().auth().preemptive().basic("postman", "password");

        Response response = httpRequest.get("https://postman-echo.com/basic-auth");

        System.out.println("Data from the GET API: ");
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody().asString());
    }
}
