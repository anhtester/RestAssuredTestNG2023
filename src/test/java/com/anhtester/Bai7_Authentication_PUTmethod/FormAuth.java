package com.anhtester.Bai7_Authentication_PUTmethod;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FormAuth {

    @Test
    public void testFormAuth() {
        given()
                .auth()
                .form("value1", "value2")
                .get("your end point URL");
    }

}
