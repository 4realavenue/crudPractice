package com.example.crudprac01.dto.response;

import java.time.LocalDateTime;

public class UpdateUserResponse {
    private String name;
    private String email;
    private LocalDateTime modifiedAt;

    public UpdateUserResponse(String name, String email, LocalDateTime modifiedAt) {
        this.name = name;
        this.email = email;
        this.modifiedAt = modifiedAt;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }
}
