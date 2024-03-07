package com.anhtester.Bai12_ValidateJsonSchema;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.helpers.SystemHelper;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;

import static io.restassured.RestAssured.given;

public class JsonSchemaValidationTest extends BaseTest {
    @Test
    public void validateJsonSchema_GetBookById() {
        InputStream GetBookIdSchema = getClass().getClassLoader()
                .getResourceAsStream("GetBookIdSchema.json");

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.BASE_URI)
                .when()
                .get("/book/10")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(GetBookIdSchema));
    }

    @Test
    public void validateJsonSchema_GetBookAll() {
        InputStream GetBookAllSchema = getClass().getClassLoader()
                .getResourceAsStream("testdata/GetBookAllSchema.json");

        String filePath = SystemHelper.getCurrentDir() + "src/test/resources/testdata/GetBookAllSchema.json";

        // Perform the API request and get the response
        Response response = given()
                .baseUri(ConfigsGlobal.BASE_URI)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .and()
                .extract()
                .response();

        response.prettyPrint();

        // Validate the response against the JSON schema
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchema(new File(filePath)));
    }
}
