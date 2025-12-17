package com.example.crudprac01.dto.response;

import java.time.LocalDateTime;

public class CreateUserResponse {
    private long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;

    public CreateUserResponse(long id, String name, String email, String password, LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
