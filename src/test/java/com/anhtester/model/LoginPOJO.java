package com.anhtester.model;

public class LoginPOJO {
    private String username;
    private String password;

    public LoginPOJO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginPOJO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
