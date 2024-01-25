package com.anhtester.Bai13_ThucHanh;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.TokenGlobal;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CategoryTest extends BaseTest {

    int CATEGORY_ID;

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");

        response.prettyPrint();
        response.then().statusCode(200);

//        JsonPath jsonPath = response.jsonPath();
//
//        CATEGORY_ID = Integer.parseInt(jsonPath.get("response.id").toString());
//        System.out.println(CATEGORY_ID);

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
        System.out.println(CATEGORY_ID);

    }

    @Test(priority = 2)
    public void getCategoryById() {
        System.out.println("Get Category By Id");

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN);

        System.out.println("CATEGORY_ID: " + CATEGORY_ID);
        Response response = request.get("/category/" + CATEGORY_ID);

        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals(jsonPath.get("response.name"), "Testing Category 05", "");

    }

    @Test(priority = 3)
    public void testUpdateCategory() {
        System.out.println("Update Category");
        //String dataFile = "src/test/resources/testdata/CreateCategory.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body("{\n" +
                        "  \"name\": \"Category 06 updated\"\n" +
                        "}");

        Response response = request.put("/category/" + CATEGORY_ID);

        response.prettyPrint();
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        System.out.println("CATEGPRY NAME: " + jsonPath.get("response.name").toString());

        Assert.assertEquals(jsonPath.get("response.name"), "Category 06 updated");

    }
}
