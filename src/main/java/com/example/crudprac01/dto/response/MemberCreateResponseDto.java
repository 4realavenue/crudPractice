package com.example.crudprac01.dto.response;

public class MemberCreateResponseDto {

    private Long id;

    public MemberCreateResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
