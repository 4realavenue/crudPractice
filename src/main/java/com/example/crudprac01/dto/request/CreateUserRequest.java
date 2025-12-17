package com.example.crudprac01.dto.request;

import java.time.LocalDateTime;

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;

    public CreateUserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
