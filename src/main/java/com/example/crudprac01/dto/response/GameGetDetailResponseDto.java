package com.example.crudprac01.dto.response;

import java.time.LocalDate;

public class GameGetDetailResponseDto {

    private Long id;
    private String name;
    private String company;
    private LocalDate releaseDate;

    public GameGetDetailResponseDto(Long id, String name, String company, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
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
