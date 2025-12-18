package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.CreateMovieRequest;
import com.example.crudprac01.dto.response.CreateMovieResponse;
import com.example.crudprac01.dto.response.GetOneMovieResponse;
import com.example.crudprac01.entity.Movie;
import com.example.crudprac01.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public CreateMovieResponse createMovie(CreateMovieRequest request) {
        Movie movie = new Movie(
                request.getTitle(),
                request.getDirector(),
                request.getReleaseDate()
        );

        movieRepository.save(movie);

        CreateMovieResponse responseDto = new CreateMovieResponse(
                movie.getTitle(),
                movie.getDirector(),
                movie.getReleaseDate()
        );

        return responseDto;
    }

    @Transactional(readOnly = true)
    public GetOneMovieResponse getOneMovie(Long movieId) {
        Movie findMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않는 영화 입니다."));

        GetOneMovieResponse responseDto = new GetOneMovieResponse(
                findMovie.getTitle(),
                findMovie.getDirector(),
                findMovie.getReleaseDate()
        );

        return responseDto;
    }
}
