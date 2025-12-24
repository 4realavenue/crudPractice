package com.example.crudprac01.dto.request;

public class UserUpdateRequestDto {
    private String name;

    public UserUpdateRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
