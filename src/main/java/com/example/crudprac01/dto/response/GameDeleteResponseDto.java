package com.example.crudprac01.dto.response;

// 도메인 + 행동 + 응답명시 + DTO명시
public class GameDeleteResponseDto {

    private Long id;
    private String name;

    public GameDeleteResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Spring이 사용하는 getter입니다.
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
