package com.anhtester.Bai6_POJO_JSON;

import com.anhtester.model.LoginPOJO;
import com.anhtester.model.RegisterUserPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPOJO {
    @Test
    public void testLoginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("anhtester", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO)); //Tự chuyển từ POJO class sang dạng JSON data

        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        String token = response.getBody().path("token");
        System.out.println(token);
    }

    @Test
    public void testRegisterUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        RegisterUserPOJO registerUserPOJO = new RegisterUserPOJO();
        registerUserPOJO.setUsername("myduyen2");
        registerUserPOJO.setPassword("Demo@123");
        registerUserPOJO.setFirstName("Lê Thị");
        registerUserPOJO.setLastName("Mỹ Duyên");
        registerUserPOJO.setEmail("myduyen2@email.com");
        registerUserPOJO.setPhone("0123456789");
        registerUserPOJO.setUserStatus(1);

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(registerUserPOJO));

        Response response = request.when().post("/register");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

}
