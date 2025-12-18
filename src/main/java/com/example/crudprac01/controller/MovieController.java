package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.CreateMovieRequest;
import com.example.crudprac01.dto.request.UpdateMovieRequest;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAllMovieResponse>>> getAllMovieApi() {
        List<GetAllMovieResponse> responseDtoList = movieService.getAllMovie();

        ApiResponse<List<GetAllMovieResponse>> apiResponse = new ApiResponse<>("success", 200, responseDtoList);

        ResponseEntity<ApiResponse<List<GetAllMovieResponse>>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    @PutMapping("/{movieId}")
    public ResponseEntity<ApiResponse<UpdateMovieResponse>> updateMovieApi(
            @PathVariable("movieId") long movieId,
            @RequestBody UpdateMovieRequest request
    ) {
        UpdateMovieResponse responseDto = movieService.updateMovie(movieId, request);

        ApiResponse<UpdateMovieResponse> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<UpdateMovieResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

}
