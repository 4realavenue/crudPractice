package com.example.crudprac01.dto.response;

public class UserUpdateResponseDto {
    private long id;
    private String name;

    public UserUpdateResponseDto(long id, String name) {
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
