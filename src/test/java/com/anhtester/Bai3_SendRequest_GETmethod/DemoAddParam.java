package com.anhtester.Bai3_SendRequest_GETmethod;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DemoAddParam {

    @Test
    public void testUserByUserName() {
        //Khai báo đối tượng request để thiết lập điều kiện đầu vào
        //Dùng given() chỉ thị sự thiết lập sẵn điều kiện
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .basePath("/user")
                .accept("application/json");

        //Khai báo tham số đầu vào với hàm queryParam
        request.queryParam("username", "anhtester2");
//        request.pathParam("", "");
//        request.formParam("", "");

        Response response = request.when().get();
        response.prettyPrint();

        response.then().statusCode(200);
        response.then().statusLine("HTTP/1.1 200 OK");
        response.then().contentType(ContentType.JSON);

        response.then().body("response.username", equalTo("anhtester2"));
        response.then().body("response.email", containsString("an2"));
    }

}
