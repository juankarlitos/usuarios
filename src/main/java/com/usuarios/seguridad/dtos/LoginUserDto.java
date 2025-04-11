package com.usuarios.seguridad.dtos;

public class LoginUserDto {

    private String userName;
    private String password;

    public LoginUserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public LoginUserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public LoginUserDto setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginUserDto setPassword(String password) {
        this.password = password;
        return this;
    }
}