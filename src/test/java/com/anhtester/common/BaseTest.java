package com.anhtester.common;

import com.anhtester.globals.TokenGlobal;
import com.anhtester.model.LoginPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

public class BaseTest {
    @BeforeTest
    public void loginUser() {

        //Khởi tạo giá trị cho các fields thông qua hàm xây dựng
        LoginPOJO loginPOJO = new LoginPOJO("anhtester", "Demo@123");

        //Dùng thư viện Gson để chuyển class POJO về dạng JSON
        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        //response.prettyPrint();

        response.then().statusCode(200);

        //Lưu giá trị token vào biến TOKEN nhé
        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token: " + TokenGlobal.TOKEN);
    }
}
