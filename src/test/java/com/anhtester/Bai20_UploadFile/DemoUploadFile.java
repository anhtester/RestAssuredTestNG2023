package com.anhtester.Bai20_UploadFile;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.helpers.SystemHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasKey;

public class DemoUploadFile extends BaseTest {
    @Test
    public void testUploadFile() {
        File file = new File(SystemHelper.getCurrentDir() + "src/test/resources/testdata/image_testing_done.jpg");

        RestAssured.baseURI = "https://api.anhtester.com/api";
        String endpoint = "/image";

        given().header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .multiPart("image", file)
                .when().post(endpoint)
                .then().log().all()
                .statusCode(200)
                .body("response", hasKey("path"))
                .body("response", hasKey("id"));

    }
}
