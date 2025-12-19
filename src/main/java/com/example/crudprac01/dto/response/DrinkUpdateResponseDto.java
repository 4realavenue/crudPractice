package com.example.crudprac01.dto.response;

import java.time.LocalDate;

public class DrinkUpdateResponseDto {
    private long id;
    private String name;
    private Integer price;
    private String description;
    private LocalDate releaseDate;

    public DrinkUpdateResponseDto(long id, String name, Integer price, String description, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
