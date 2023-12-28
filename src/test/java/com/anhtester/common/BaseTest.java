package com.anhtester.common;

import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.model.LoginPOJO;
import com.anhtester.model.data.LoginPOJO_Builder;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeSuite
    public void setupSuite() {
        PropertiesHelper.loadAllFiles();
    }

    @BeforeTest
    public void loginUser() {
        //LoginPOJO loginPOJO = new LoginPOJO(ConfigsGlobal.USERNAME, ConfigsGlobal.PASSWORD);
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept("application/json")
                .contentType("application/json")
                .body(gson.toJson(loginPOJO));

        Response response = request.when().post("/login");
        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        System.out.println("Token Global: " + TokenGlobal.TOKEN);
    }
}