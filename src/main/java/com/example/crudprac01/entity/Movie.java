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
    @Column(name = "id")
    private long id;

    @Column(name = "title", length = 100, unique = true)
    private String title;

    @Column(name = "director", length = 30)
    private String director;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }


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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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