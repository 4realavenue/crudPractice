package com.example.crudprac01.entity;

import com.example.crudprac01.dto.request.UpdateMovieRequest;
import com.example.crudprac01.dto.response.UpdateMovieResponse;
import jakarta.persistence.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, unique = true)
    private String title;

    @Column(length = 30)
    private String director;

    private LocalDate releaseDate;

    private Boolean isDeleted = false;

    // 스프링이 사용하는 생성자 입니다.
    protected Movie() {
    }

    public Movie(String title, String director, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return id;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void update(String title, String director, LocalDate releaseDate) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
    }

    public void delete() {
        this.isDeleted = true;
    }

}