package com.anhtester.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data //Bao gồm Getter và Setter
//@Getter
//@Setter
public class RegisterUserPOJO_Lombok {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;

    public RegisterUserPOJO_Lombok(String username, String firstName, String lastName) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
