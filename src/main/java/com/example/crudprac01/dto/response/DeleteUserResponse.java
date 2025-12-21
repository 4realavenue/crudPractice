package com.example.crudprac01.dto.response;

public class DeleteUserResponse {
    private Long id;

    public DeleteUserResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
