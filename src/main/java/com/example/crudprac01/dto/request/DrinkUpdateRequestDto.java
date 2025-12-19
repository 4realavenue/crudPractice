package com.example.crudprac01.dto.request;

public class DrinkUpdateRequestDto {
    private String name;
    private Integer price;

    public DrinkUpdateRequestDto(String name, Integer price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }
}
