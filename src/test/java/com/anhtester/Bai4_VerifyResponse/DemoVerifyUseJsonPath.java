package com.anhtester.Bai4_VerifyResponse;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DemoVerifyUseJsonPath {
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

        //Muốn lấy giá trị từng key trong JSON body để compare chính xác thì dùng JsonPath
        JsonPath jsonPath = response.jsonPath(); //Lưu hết body vào đối tượng jsonPath

        //Truy xuất giá trị theo key hoặc đường dẫn xpath theo cấp bậc
        String name = jsonPath.get("response.name");
        System.out.println("Name: " + name);
        //Dùng Assert của TestNG để verify
        Assert.assertEquals(name.contains("Phương Nam"), true, "Name không tồn tại.");
        Assert.assertEquals(name, "Đất Rừng Phương Nam", "Name không tồn tại.");
        //Khi lấy trực tiếp giá trị từ jsonPath thì cần toString
        //và phải chuyển số về sạng chuỗi để so sánh
        Assert.assertEquals(jsonPath.get("response.price").toString(), "12000", "Price không đúng.");
        Assert.assertEquals(Integer.parseInt(jsonPath.get("response.price").toString()), 12000, "Price không đúng.");

        //Lấy đường dẫn path thứ 2 trong mảng của object "image"
        //Index bắt đầu tính từ 0
        String imagePath2 = jsonPath.get("response.image[1].path");
        System.out.println(imagePath2);
        Assert.assertTrue(imagePath2.contains("public/images/TwSX1W1"), "Không đúng hình ảnh thứ 2.");
    }
}
