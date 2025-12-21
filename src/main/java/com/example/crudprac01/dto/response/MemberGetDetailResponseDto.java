package com.example.crudprac01.dto.response;

public class MemberGetDetailResponseDto {

    private Long id;
    private String name;

    public MemberGetDetailResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
