package com.anhtester.Bai4_VerifyResponse;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class DemoVerifyUseThenMethod {

    @Test
    public void testVerifyResponseUseThenMethod() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json");

        int id = 1; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với hàm then()
        response.then().statusCode(200);
        response.then().contentType("application/json");
        //Đối với body thì cần điền cấu trúc theo xpath từ json
        //Hàm equalTo thuộc thư viện org.hamcrest.Matchers
        response.then().body("response.name", equalTo("Đất Rừng Phương Nam"));
        response.then().body("response.price", equalTo(1200));
        //Dùng vị trí index để lấy thứ tự phần tử trong JSON body. Tính từ 0
        response.then().body("response.image[0].path", containsString("public/images/1avh0VncWc"));
    }

}
