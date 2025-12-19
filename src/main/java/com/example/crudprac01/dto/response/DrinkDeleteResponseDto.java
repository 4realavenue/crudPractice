package com.example.crudprac01.dto.response;

public class DrinkDeleteResponseDto {
    private long id;
    private String name;

    public DrinkDeleteResponseDto(long id, String name) {
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
