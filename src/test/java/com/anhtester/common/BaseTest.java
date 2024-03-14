package com.anhtester.common;

import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.globals.EndPointGlobal;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.helpers.PropertiesHelper;
import com.anhtester.keywords.ApiKeyword;
import com.anhtester.listeners.TestListener;
import com.anhtester.model.LoginPOJO;
import com.anhtester.model.data.LoginPOJO_Builder;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeTest
    public void loginUser() {
        LoginPOJO loginPOJO = LoginPOJO_Builder.getDataLogin();

        Gson gson = new Gson();

        Response response = ApiKeyword.postNotAuth(EndPointGlobal.EP_LOGIN, gson.toJson(loginPOJO));

        response.then().statusCode(200);

        TokenGlobal.TOKEN = response.getBody().path("token");
        LogUtils.info("Token Global: " + TokenGlobal.TOKEN);
        AllureManager.saveTextLog("Token Global: " + TokenGlobal.TOKEN);
    }
}