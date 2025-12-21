package com.example.crudprac01.dto.response;

import java.time.LocalDate;

// 도메인 + 행동 + 응답명시 + DTO명시
public class GameCreateResponseDto {

    private Long id;
    private String name;
    private String company;
    private LocalDate releaseDate;

    public GameCreateResponseDto(Long id, String name, String company, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
    }

    // Spring이 사용하는 getter입니다.
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
}
