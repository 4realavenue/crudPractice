package com.example.crudprac01.dto.response;

public class GameDeleteResponseDto {

    private Long id;
    private String name;

    public GameDeleteResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
