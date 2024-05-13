package com.anhtester.Bai19_ParallelExecution;

import com.anhtester.listeners.TestListener;
import com.anhtester.model.BookingBody;
import com.anhtester.model.BookingDates;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

@Listeners(TestListener.class)
public class BookingTest_Parallel {
    @Test
    public void testCreateBooking1() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        Faker faker = new Faker(new Locale("vi"));

        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester " + faker.random().hex(6));
        bookingBody.setTotalprice(120);
        bookingBody.setDepositpaid(false);
        bookingBody.setAdditionalneeds("Automation");
        bookingDates.setCheckin("2024-03-26");
        bookingDates.setCheckout("2024-03-30");

        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testCreateBooking2() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        Faker faker = new Faker(new Locale("vi"));

        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester " + faker.random().hex(6));
        bookingBody.setTotalprice(120);
        bookingBody.setDepositpaid(false);
        bookingBody.setAdditionalneeds("Automation");
        bookingDates.setCheckin("2024-03-26");
        bookingDates.setCheckout("2024-03-30");

        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void testCreateBooking3() {

        String baseUri = "https://restful-booker.herokuapp.com";

        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        BookingBody bookingBody = new BookingBody();
        BookingDates bookingDates = new BookingDates();

        Faker faker = new Faker(new Locale("vi"));

        bookingBody.setFirstname("Anh");
        bookingBody.setLastname("Tester " + faker.random().hex(6));
        bookingBody.setTotalprice(120);
        bookingBody.setDepositpaid(false);
        bookingBody.setAdditionalneeds("Automation");
        bookingDates.setCheckin("2024-03-26");
        bookingDates.setCheckout("2024-03-30");

        bookingBody.setBookingdates(bookingDates);

        Gson gson = new Gson();

        request.body(gson.toJson(bookingBody));

        Response response = request.post("/booking");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}