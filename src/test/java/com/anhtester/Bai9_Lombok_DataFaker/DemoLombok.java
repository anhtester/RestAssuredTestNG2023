package com.anhtester.Bai9_Lombok_DataFaker;

import com.anhtester.common.BaseTest;
import com.anhtester.globals.TokenGlobal;
import com.anhtester.model.PatchUserPOJO;
import com.anhtester.model.RegisterUserPOJO_Lombok;
import com.google.gson.Gson;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DemoLombok extends BaseTest {
    @Test
    public void testLombok(){

        RegisterUserPOJO_Lombok registerUserPOJO_lombok1 = new RegisterUserPOJO_Lombok();

        registerUserPOJO_lombok1.getFirstName();

        RegisterUserPOJO_Lombok registerUserPOJO_lombok2 = new RegisterUserPOJO_Lombok(
                "",
                "",
                "",
                "",
                "",
                "",
                1
        );

    }

    @Test
    public void testDataFaker(){
        Faker faker = new Faker(new Locale("en"));
        System.out.println(faker.color().name());
        System.out.println(faker.color().hex());

        String computer = faker.computer().windows();
        String name = faker.name().fullName();
        String fullAddress = faker.address().fullAddress();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();

        System.out.println(computer);
        System.out.println(name);
        System.out.println(fullAddress);
        System.out.println(email);
        System.out.println(password);

        System.out.println(faker.name().username());
    }

    @Test
    public void testUpdateUser_PATCH() {

        Faker faker = new Faker(new Locale("vi"));

        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");
        System.out.println(phoneNumber);

        //Chuẩn bị data cho edit user
        RegisterUserPOJO_Lombok registerUserPOJO_lombok = new RegisterUserPOJO_Lombok();
        registerUserPOJO_lombok.setFirstName(faker.name().firstName());
        registerUserPOJO_lombok.setLastName(faker.name().lastName());
        registerUserPOJO_lombok.setEmail(faker.internet().emailAddress());
        registerUserPOJO_lombok.setPhone(phoneNumber);
        registerUserPOJO_lombok.setUserStatus(0);

        Gson gson = new Gson();

        RequestSpecification request = given();
        request.baseUri("https://api.anhtester.com/api")
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer " + TokenGlobal.TOKEN)
                .body(gson.toJson(registerUserPOJO_lombok));

        Response response = request.when().patch("/user/2");
        response.prettyPrint();

        response.then().statusCode(200);

        String message = response.getBody().path("message");
        Assert.assertEquals(message, "Success", "The message not match.");
    }
}
