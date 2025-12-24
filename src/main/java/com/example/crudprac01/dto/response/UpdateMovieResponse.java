package com.example.crudprac01.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UpdateMovieResponse {
    private String title;
    private String director;
    private LocalDate releaseDate;
    private LocalDateTime updatedAt;

    public UpdateMovieResponse(String title, String director, LocalDate releaseDate, LocalDateTime updatedAt) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.updatedAt = updatedAt;
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
