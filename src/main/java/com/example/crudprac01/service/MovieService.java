package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.CreateMovieRequest;
import com.example.crudprac01.dto.request.UpdateMovieRequest;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.entity.Movie;
import com.example.crudprac01.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public GetOneMovieResponse getOneMovie(long movieId) {
        Movie findMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않는 영화 입니다."));

        if (findMovie.getDeleted() == true) {
            throw new IllegalArgumentException("등록이 제거 된 영화입니다.");
        }

        GetOneMovieResponse responseDto = new GetOneMovieResponse(
                findMovie.getTitle(),
                findMovie.getDirector(),
                findMovie.getReleaseDate()
        );

        return responseDto;
    }

    @Transactional(readOnly = true)
    public GetAllMovieResponse getAllMovie() {
        // 삭제되지 않은 영화 찾아와서 movieList에 넣어줌
        List<Movie> movieList = movieRepository.findByIsDeletedIsFalse();

        // movieList의 크기를 count라는 변수에 넣어줌 (몇 개인지)
        Integer count = movieList.size();

        List<GetAllMovieResponse.GetMovieListResponse> responseListDto = new ArrayList<>();

        for (Movie movie : movieList) {
            GetAllMovieResponse.GetMovieListResponse responseDto = new GetAllMovieResponse.GetMovieListResponse(
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getReleaseDate()
            );

            responseListDto.add(responseDto);
        }

        GetAllMovieResponse response = new GetAllMovieResponse(count, responseListDto);

        return response;
    }

    @Transactional
    public UpdateMovieResponse updateMovie(long movieId, UpdateMovieRequest request) {
        Movie findMovie = movieRepository.findById(movieId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 영화 입니다."));

        if (findMovie.getDeleted() == true) {
            throw new IllegalArgumentException("등록이 제거 된 영화입니다.");
        }

        findMovie.update(
                request.getTitle(),
                request.getDirector(),
                request.getReleaseDate()
        );

        UpdateMovieResponse responseDto = new UpdateMovieResponse(
                findMovie.getTitle(),
                findMovie.getDirector(),
                findMovie.getReleaseDate()
        );

        return responseDto;

    }

    @Transactional
    public DeleteMovieResponse deleteMovie(long movieId) {
         Movie findMovie = movieRepository.findById(movieId)
                 .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 영화 입니다."));

         if (findMovie.getDeleted() == true) {
             throw new IllegalArgumentException("이미 등록이 제거 된 영화입니다.");
         }

         findMovie.delete();

        DeleteMovieResponse response = new DeleteMovieResponse(findMovie.getId());

        return response;
    }
}
