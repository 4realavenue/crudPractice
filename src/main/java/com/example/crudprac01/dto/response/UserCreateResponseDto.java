package com.example.crudprac01.dto.response;

public class UserCreateResponseDto {

    private long id;
    private String name;

    public UserCreateResponseDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
