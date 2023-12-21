package com.anhtester.Bai8_PATCH_DELETE;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.TokenGlobal;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoDELETE extends BaseTest {

    @Test
    public void testDeleteUser_DELETE() {

        //Chuẩn bị thông tin username để delete
        String username = "an01";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .queryParam("username", username);

        Response response = request.when().delete("/user");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
