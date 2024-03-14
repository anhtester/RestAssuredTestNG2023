package com.anhtester.keywords;

import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiKeyword {

    @Step("GET: {0}")
    public static Response get(String path) {
        LogUtils.info("GET: " + path);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with headers {1}")
    public static Response get(String path, Map<String, String> headers) {
        LogUtils.info("GET: " + path);
        LogUtils.info("HEADERS: " + headers);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder().headers(headers)).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET: {0} with BEARER TOKEN {1}")
    public static Response get(String path, String authBearerToken) {
        LogUtils.info("GET: " + path);
        LogUtils.info("BEARER TOKEN: " + authBearerToken);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder().header("Authorization", "Bearer " + authBearerToken)).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("GET (not auth): {0}")
    public static Response getNotAuth(String path) {
        LogUtils.info("GET not authorization: " + path);
        Response response =
                given(SpecBuilder.getRequestNotAuthSpecBuilder()).
                        when().
                        get(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().
                        response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with body {1}")
    public static Response post(String path, Object payLoad) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        body(payLoad).
                        when().
                        post(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST (not auth): {0} with body {1}")
    public static Response postNotAuth(String path, Object payLoad) {
        LogUtils.info("POST not authorization: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response =
                given(SpecBuilder.getRequestNotAuthSpecBuilder()).
                        body(payLoad).
                        when().
                        post(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("POST: {0} with body by File {1}")
    public static Response post(String path, File fileBody) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + fileBody.getPath());
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        body(fileBody).
                        when().
                        post(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("PUT: {0} with body {1}")
    public static Response put(String path, Object payLoad) {
        LogUtils.info("PUT: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        body(payLoad).
                        when().
                        put(path).
                        then().
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("DELETE: {0} with body {1}")
    public static Response delete(String path, Object payLoad) {
        LogUtils.info("DELETE: " + path);
        LogUtils.info("Body: " + payLoad);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        body(payLoad).
                        when().
                        delete(path).
                        then().
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        AllureManager.saveTextLog("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

    @Step("Get Response value by key: {1}")
    public static String getResponseKeyValue(Response response, String responseKey) {
        JsonPath jsonPath = response.jsonPath();
        String key_value = jsonPath.get(responseKey).toString();
        LogUtils.info("Value by key (" + responseKey + "): " + key_value);
        AllureManager.saveTextLog("Value by key (" + responseKey + "): " + key_value);
        return key_value;
    }

    @Step("Get Response value by key: {1}")
    public static String getResponseKeyValue(String responseBody, String responseKey) {
        JsonPath jsonPath = new JsonPath(responseBody);
        String key_value = jsonPath.get(responseKey).toString();
        LogUtils.info("Value by key (" + responseKey + "): " + key_value);
        AllureManager.saveTextLog("Value by key (" + responseKey + "): " + key_value);
        return key_value;
    }

    @Step("Get Status Code")
    public static int getStatusCode(Response response) {
        int status_code = response.getStatusCode();
        LogUtils.info("Get Status Code: " + status_code);
        AllureManager.saveTextLog("The Status Code: " + status_code);
        return status_code;
    }

    @Step("Get Status Line")
    public static String getStatusLine(Response response) {
        String status_line = response.getStatusLine();
        LogUtils.info("Get Status Line: " + status_line);
        AllureManager.saveTextLog("The Status Line: " + status_line);
        return status_line;
    }

    @Step("Get Response Header by key: {1}")
    public static String getResponseHeader(Response response, String header_key) {
        String response_header = response.getHeader(header_key);
        LogUtils.info("Get Response Header by key (" + header_key + "): " + response_header);
        AllureManager.saveTextLog("The Header by key (" + header_key + "): " + response_header);
        return response_header;
    }

    @Step("Get Response Content Type")
    public static String getResponseContentType(Response response) {
        String content_type = response.getContentType();
        LogUtils.info("Get Content Type: " + content_type);
        AllureManager.saveTextLog("The Content Type: " + content_type);
        return content_type;
    }

    @Step("Get Response Cookie by name: {1}")
    public static String getResponseCookieName(Response response, String cookieName) {
        String cookie_value = response.getCookie(cookieName);
        LogUtils.info("Get Cookie by name (" + cookieName + "): " + cookie_value);
        AllureManager.saveTextLog("The Cookie by name (" + cookieName + "): " + cookie_value);
        return cookie_value;
    }

    @Step("Verify Status Code expected: {1}")
    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        LogUtils.info("Verify Status code: " + response.getStatusCode() + " == " + expectedStatusCode);
        AllureManager.saveTextLog("Verify Status code: " + response.getStatusCode() + " == " + expectedStatusCode);
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "FAIL. The status code not match.");
    }
}