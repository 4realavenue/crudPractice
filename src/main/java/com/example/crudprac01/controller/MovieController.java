package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.CreateMovieRequest;
import com.example.crudprac01.dto.response.ApiResponse;
import com.example.crudprac01.dto.response.CreateMovieResponse;
import com.example.crudprac01.dto.response.GetOneMovieResponse;
import com.example.crudprac01.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CreateMovieResponse>> createMovieApi(@RequestBody CreateMovieRequest request) {

        CreateMovieResponse responseDto = movieService.createMovie(request);

        ApiResponse<CreateMovieResponse> apiResponse = new ApiResponse<>("success", 201, responseDto);

        ResponseEntity<ApiResponse<CreateMovieResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;

    }

    @GetMapping("/{movieId}")
    public ResponseEntity<ApiResponse<GetOneMovieResponse>> getOneMovieApi(@PathVariable ("movieId") long movieId) {

        GetOneMovieResponse responseDto = movieService.getOneMovie(movieId);

        ApiResponse<GetOneMovieResponse> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GetOneMovieResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

}
