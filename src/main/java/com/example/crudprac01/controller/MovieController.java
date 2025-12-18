package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.CreateMovieRequest;
import com.example.crudprac01.dto.response.CreateMovieResponse;
import com.example.crudprac01.service.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public CreateMovieResponse createMovieApi(@RequestBody CreateMovieRequest request) {

        CreateMovieResponse response = movieService.createMovie(request);

        return response;

    }

}
