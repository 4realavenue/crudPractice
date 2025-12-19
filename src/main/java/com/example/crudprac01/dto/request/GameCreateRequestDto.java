package com.example.crudprac01.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class GameCreateRequestDto {

    private String name;
    private String company;
    @JsonFormat (pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

    @JsonCreator
    public GameCreateRequestDto(
            @JsonProperty("name") String name,
            @JsonProperty("company") String company,
            @JsonProperty("releaseDate") LocalDate releaseDate) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
