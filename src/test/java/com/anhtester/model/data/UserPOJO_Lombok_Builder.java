package com.anhtester.model.data;

import com.anhtester.model.RegisterUserPOJO_Lombok;
import net.datafaker.Faker;

import java.util.Locale;

public class UserPOJO_Lombok_Builder {

    public static RegisterUserPOJO_Lombok getUserData() {

        Faker faker = new Faker(new Locale("vi"));
        String phoneNumber = faker.phoneNumber().cellPhone();
        phoneNumber = phoneNumber.replace(" ", "");

        return RegisterUserPOJO_Lombok.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(phoneNumber)
                .email(faker.internet().emailAddress())
                .userStatus(1)
                .build();
    }

}