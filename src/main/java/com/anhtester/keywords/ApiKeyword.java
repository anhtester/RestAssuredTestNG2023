package com.anhtester.keywords;

import com.anhtester.utils.LogUtils;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import java.io.File;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiKeyword {

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
        return response;
    }

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
        return response;
    }

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
        return response;
    }

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
        return response;
    }

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
        return response;
    }

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
        return response;
    }

    public static Response post(String path, String filePathBody) {
        LogUtils.info("POST: " + path);
        LogUtils.info("Body: " + filePathBody);
        Response response =
                given(SpecBuilder.getRequestSpecBuilder()).
                        body(new File(filePathBody)).
                        when().
                        post(path).
                        then().
                        spec(SpecBuilder.getResponseSpecBuilder()).
                        extract().response();

        LogUtils.info("RESPONSE: \n" + response.prettyPrint());
        return response;
    }

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
        return response;
    }

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
        return response;
    }

    public static String getResponseKeyValue(Response response, String responseKey) {
        JsonPath jsonPath = response.jsonPath();
        String key_value = jsonPath.get(responseKey).toString();
        LogUtils.info("Get body by key (" + responseKey + "): " + key_value);
        return key_value;
    }

    public static String getResponseKeyValue(String responseBody, String responseKey) {
        JsonPath jsonPath = new JsonPath(responseBody);
        String key_value = jsonPath.get(responseKey).toString();
        LogUtils.info("Get body by key (" + responseKey + "): " + key_value);
        return key_value;
    }

    public static int getStatusCode(Response response) {
        int status_code = response.getStatusCode();
        LogUtils.info("Get Status Code: " + status_code);
        return status_code;
    }

    public static String getStatusLine(Response response) {
        String status_line = response.getStatusLine();
        LogUtils.info("Get Status Line: " + status_line);
        return status_line;
    }

    public static String getResponseHeader(Response response, String header_key) {
        String response_header = response.getHeader(header_key);
        LogUtils.info("Get Header by key (" + header_key + "): " + response_header);
        return response_header;
    }

    public static String getResponseContentType(Response response) {
        String content_type = response.getContentType();
        LogUtils.info("Get Content Type: " + content_type);
        return content_type;
    }

    public static String getResponseCookieName(Response response, String cookieName) {
        String cookie_value = response.getCookie(cookieName);
        LogUtils.info("Get Cookie by name (" + cookieName + "): " + cookie_value);
        return cookie_value;
    }

    public static void verifyStatusCode(Response response, int expectedStatusCode) {
        LogUtils.info("Verify Status code: " + response.getStatusCode() + " == " + expectedStatusCode);
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode, "FAIL. The status code not match.");
    }
}