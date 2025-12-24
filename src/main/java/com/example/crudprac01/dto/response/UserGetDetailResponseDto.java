package com.example.crudprac01.dto.response;

public class UserGetDetailResponseDto {

    private long id;
    private String name;

    public UserGetDetailResponseDto(long id, String name) {
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
