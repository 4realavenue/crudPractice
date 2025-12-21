package com.example.crudprac01.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

// 도메인 + 행동 + 요청명시 + DTO명시
public class GameCreateRequestDto {

    private String name;
    private String company;
    // Json으로 입력 받을 형식을 "년년년년/달달/일일" 로 정해줌
    @JsonFormat (pattern = "yyyy/MM/dd")
    private LocalDate releaseDate;

    @JsonCreator
    // Json 형식으로 들어온 데이터를 Java 객체로 변환할 때 명시해줌
    public GameCreateRequestDto(
            @JsonProperty("name") String name,
            @JsonProperty("company") String company,
            @JsonProperty("releaseDate") LocalDate releaseDate) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
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
