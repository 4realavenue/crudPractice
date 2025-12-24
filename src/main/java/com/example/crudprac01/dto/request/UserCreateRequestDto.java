package com.example.crudprac01.dto.request;

public class UserCreateRequestDto {

    private final String name;

    public UserCreateRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
