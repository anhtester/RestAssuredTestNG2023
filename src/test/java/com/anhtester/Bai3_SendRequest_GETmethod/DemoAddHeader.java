package com.anhtester.Bai3_SendRequest_GETmethod;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoAddHeader {

    @Test
    public void testAddHeader(){

        //Khai báo đối tượng request
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api");
        request.basePath("/users");
        //Add header theo yêu cầu, với cú pháp (key,value)
        request.header("accept", "application/json");
        //request.header("", "");
        //request.accept("application/json");

        Response response = request.when().get();

        response.prettyPrint();

        response.then().statusCode(200);

    }

}
