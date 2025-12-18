package com.example.crudprac01.dto.response;

public class UpdateUserResponse {
    private String name;
    private String email;

    public UpdateUserResponse(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
