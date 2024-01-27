package com.anhtester.Bai14_Annotation;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.helpers.JsonHelper;
import com.anhtester.model.LoginPOJO;
import com.anhtester.model.data.LoginPOJO_Builder;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoRunTestXML_01 extends BaseTest {

    @BeforeClass
    public void beforeClass1(){
        System.out.println("This is before class CON1");
    }

    public static int CATEGORY_ID;
    public static String CATEGORY_NAME;

    @Test(priority = 1)
    public void testAddNewCategory() {
        System.out.println("Create Category");
        String dataFile = "src/test/resources/testdata/CreateCategory.json";

        Faker faker = new Faker(new Locale("vi"));
        CATEGORY_NAME = faker.job().title();
        System.out.println("CATEGORY_NAME: " + CATEGORY_NAME);

        JsonHelper.updateValueJsonFile(dataFile, "name", CATEGORY_NAME);

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(new File(dataFile));

        Response response = request.post("/category");

        response.prettyPrint();
        response.then().statusCode(200);

        CATEGORY_ID = Integer.parseInt(response.path("response.id").toString());
        System.out.println("CATEGORY_ID: " + CATEGORY_ID);
    }

    @Test(priority = 2)
    public void testGetCategoryById() {
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
        Assert.assertEquals(jsonPath.get("response.name"), CATEGORY_NAME, "Category name not match.");
    }
}
