package com.example.crudprac01.dto.response;

public class DeleteMovieResponse {
    private long id;

    public DeleteMovieResponse(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
