package com.anhtester.Bai6_POJO_JSON;

import com.anhtester.model.BookingBody;
import com.anhtester.model.BookingDates;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class DemoPOJO_MultiLevel {
    @Test
    public void testCreateBooking() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        //Khởi tạo 2 class POJO
        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        //Set giá trị cho các fields không chứa cấp bậc
        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester");
        bookingBody.setTotalprice(120);
        bookingBody.setDepositpaid(false);
        bookingBody.setAdditionalneeds("Automation");

        //Set giá trị cho 2 fields con từ class POJO phụ
        bookingDates.setCheckin("2023-12-15");
        bookingDates.setCheckout("2023-12-30");

        //Set giá trị cho field Cha với 2 thông số từ fields Con
        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        //Convert POJO to JSON
        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testLoginUser() {

        String filePath = "src/test/resources/login.json";

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .body(new File(filePath));

        //Thực hiện phương thức post() để gửi dữ liệu đi
        Response response = request.when().post("/login");
        response.prettyPrint();

        response.then().statusCode(200);

        String token = response.getBody().path("token").toString();
        System.out.println(token);
    }
}
