package com.example.crudprac01.dto.response;

public class DeleteUserResponseDto {
    private Long id;

    public DeleteUserResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
