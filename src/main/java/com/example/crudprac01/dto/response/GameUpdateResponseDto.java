package com.example.crudprac01.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class GameUpdateResponseDto {

    private Long id;
    private String name;
    private String company;
    private LocalDate releaseDate;
    private LocalDateTime updatedAt;

    public GameUpdateResponseDto(Long id, String name, String company, LocalDate releaseDate, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
