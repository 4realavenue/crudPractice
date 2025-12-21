package com.example.crudprac01.dto.response;

public class MemberDeleteResponseDto {

    private Long id;

    public MemberDeleteResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
