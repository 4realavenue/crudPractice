package com.example.crudprac01.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class DrinkCreateRequestDto {
    private String name;
    private String description;
    private Integer price;
    @JsonFormat (pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

    public DrinkCreateRequestDto(String name, String description, Integer price, LocalDate releaseDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
