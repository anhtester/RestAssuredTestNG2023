package com.anhtester.Bai8_PATCH_DELETE;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.model.LoginPOJO;
import com.anhtester.model.PatchUserPOJO;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoPATCH extends BaseTest {

    @Test
    public void testUpdateUser_PATCH() {

        //Chuẩn bị data cho edit user
        PatchUserPOJO patchUserPOJO = new PatchUserPOJO();
        patchUserPOJO.setFirstName("Thái");
        patchUserPOJO.setLastName("Uyên");
        patchUserPOJO.setEmail("boiboi2@email.com");
        patchUserPOJO.setPhone("0111123456");
        patchUserPOJO.setUserStatus(0);
        patchUserPOJO.setUsername("boiboi2");

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(patchUserPOJO));

        Response response = request.when().patch("/user/2");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }

    @Test
    public void testUpdateUser_PATCH2(){
        System.out.println(TokenGlobal.TOKEN);
        System.out.println("Test case thứ 2");
    }

    @Test
    public void testUpdateUser_PATCH3(){
        System.out.println(TokenGlobal.TOKEN);
        System.out.println("Test case thứ 3");
    }
}
