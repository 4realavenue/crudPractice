package com.example.crudprac01.dto.request;

public class UpdateUserRequest {
    private String name;

    public UpdateUserRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
