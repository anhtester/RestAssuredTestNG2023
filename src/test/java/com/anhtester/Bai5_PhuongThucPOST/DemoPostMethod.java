package com.anhtester.Bai5_PhuongThucPOST;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DemoPostMethod {
    @Test
    public void testLoginUser() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \"anhtester4\",\n" +
                        "  \"password\": \"Demo@123\"\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        String token = response.getBody().path("token").toString();
        System.out.println(token);
    }

    @Test
    public void testRegisterUser() {

        String username = "anhtester12";
        String firstName = "Anh";
        String lastName = "Tester";
        String email = username + "@email.com";
        String password = "Demo@123";
        String phone = "0123456789";
        int userStatus = 1;

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body("{\n" +
                        "  \"username\": \" " + username + "\",\n" +
                        "  \"firstName\": \"" + firstName + "\",\n" +
                        "  \"lastName\": \"" + lastName + "\",\n" +
                        "  \"email\": \"" + username + "@email.com\",\n" +
                        "  \"password\": \"" + password + "\",\n" +
                        "  \"phone\": \"" + phone + "\",\n" +
                        "  \"userStatus\": " + userStatus + "\n" +
                        "}");

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/register");
        response.prettyPrint();
        response.then().statusCode(200);

        //Verify Response
        response.then().contentType(ContentType.JSON);

        //Cách 1
        ResponseBody responseBody = response.getBody();

        //Cách 2 (ưu tiên cách 2)
        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals(jsonPath.get("response.username"), username, "The username not match.");
        Assert.assertEquals(responseBody.path("response.firstName"), firstName, "The firstName not match.");
        Assert.assertEquals(responseBody.path("response.lastName"), lastName, "The lastName not match.");
        Assert.assertEquals(responseBody.path("response.email"), email, "The email not match.");
        //Assert.assertEquals(responseBody.path("response.password"), password, "The password not match.");
        Assert.assertEquals(responseBody.path("response.phone"), phone, "The phone not match.");
        Assert.assertEquals(Integer.parseInt(responseBody.path("response.userStatus").toString()), userStatus, "The userStatus not match.");
        Assert.assertTrue(responseBody.path("response.id").toString().length() > 0, "The ID not exsiting.");

    }
}
