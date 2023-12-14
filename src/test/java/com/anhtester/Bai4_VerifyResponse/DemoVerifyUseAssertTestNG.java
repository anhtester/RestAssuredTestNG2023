package com.anhtester.Bai4_VerifyResponse;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoVerifyUseAssertTestNG {
    @Test
    public void testVerifyResponseUseAssertTestNG() {
        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json");

        int id = 1; //ID của book. Gắn vào sau path url luôn

        Response response = request.when().get("/book/" + id);
        response.prettyPrint();

        //Verify kết quả từ response với Assert trong TestNG
        //Dùng class Assert chấm gọi 2 hàm chính là assertEquals và assertTrue
        Assert.assertEquals(response.getStatusCode(), 200, "Status Code chưa đúng.");
        Assert.assertEquals(response.getContentType(), "application/json", "Content Type chưa đúng.");

        //Khi lấy kết quả trong body thì cần dùng đối tượng class ResponseBody để lấy hết về kiểu String
        //Khi đó chỉ có thể dùng hàm contains để so sánh chứa, vì không lấy được từng giá trị của từng key
        ResponseBody body = response.getBody();
        Assert.assertEquals(body.asString().contains("Success"), true, "Message Success không tồn tại.");

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì
        //phải dùng hàm then() hoặc thư viện JsonPath
    }
}
