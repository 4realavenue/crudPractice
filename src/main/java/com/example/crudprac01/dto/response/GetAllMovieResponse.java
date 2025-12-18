package com.example.crudprac01.dto.response;

import java.time.LocalDate;

public class GetAllMovieResponse {
    private String title;
    private String director;
    private LocalDate releaseDate;

    public GetAllMovieResponse(String title, String director, LocalDate releaseDate) {
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
