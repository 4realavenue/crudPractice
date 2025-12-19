package com.example.crudprac01.dto.response;

import java.time.LocalDate;
import java.util.List;

public class GetAllMovieResponse {
    private Integer count;
    private List<GetMovieListResponse> movieList;

    public GetAllMovieResponse(Integer count, List<GetMovieListResponse> movieList) {
        this.count = count;
        this.movieList = movieList;
    }

    public Integer getCount() {
        return count;
    }

    public List<GetMovieListResponse> getMovieList() {
        return movieList;
    }

    // movie 리스트
    public static class GetMovieListResponse {
        private String title;
        private String director;
        private LocalDate releaseDate;

        public GetMovieListResponse(String title, String director, LocalDate releaseDate) {
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
}


