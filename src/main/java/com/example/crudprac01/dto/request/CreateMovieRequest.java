package com.example.crudprac01.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateMovieRequest {
    private String title;
    private String director;
    @JsonFormat(pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

    public CreateMovieRequest(String title, String director, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }
}
