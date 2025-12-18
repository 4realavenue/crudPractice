package com.example.crudprac01.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CreateMovieResponse {
    private String title;
    private String director;
    private LocalDate releaseAt;

    public CreateMovieResponse(String title, String director, LocalDate releaseAt) {
        this.title = title;
        this.director = director;
        this.releaseAt = releaseAt;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public LocalDate getReleaseAt() {
        return releaseAt;
    }
}
