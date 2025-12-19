package com.example.crudprac01.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "games")
public class Game {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    @Column(name = "update_at")
    private LocalDateTime updatedAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    // Spring이 사용하는 생성자 입니다.
    protected Game() {}

    public Game(String name, String company, LocalDate releaseDate) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    // -----------------[method]--------------------
    public void updateGame(String name, String company, LocalDate releaseDate, LocalDateTime updatedAt) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
        this.updatedAt = updatedAt;
    }

    public void deleteGame() {
        this.isDeleted = true;
    }
}
