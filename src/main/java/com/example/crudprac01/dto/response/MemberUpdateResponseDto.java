package com.example.crudprac01.dto.response;

public class MemberUpdateResponseDto {

    private Long id;

    public MemberUpdateResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
