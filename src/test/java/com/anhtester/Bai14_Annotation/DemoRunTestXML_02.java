package com.anhtester.Bai14_Annotation;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.ConfigsGlobal;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.model.BookPOJO;
import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.joda.time.LocalDateTime;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoRunTestXML_02 extends BaseTest {

    @BeforeClass
    public void beforeClass2(){
        System.out.println("This is before class CON2");
    }

    @Test
    public void testAddNewBook() {

        Faker faker = new Faker(new Locale("vi"));
        String BOOK_NAME = faker.book().title() + "_" + LocalDateTime.now();

        BookPOJO bookPOJO = new BookPOJO();

        bookPOJO.setName(BOOK_NAME);
        bookPOJO.setCategory_id(DemoRunTestXML_01.CATEGORY_ID);
        bookPOJO.setPrice(120000);
        bookPOJO.setRelease_date("2024-01-27");
        bookPOJO.setStatus(true);

        bookPOJO.setImage_ids(new ArrayList<>(Arrays.asList(2, 18, 19)));

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri(ConfigsGlobal.URI)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(bookPOJO));

        Response response = request.post("book");
        response.prettyPrint();

        response.then().statusCode(200);

    }
}
