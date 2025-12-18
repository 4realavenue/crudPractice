package com.example.crudprac01.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;

public class CreateUserRequest {

    private String name;
    private String email;
    private String password;

    @JsonCreator
    public CreateUserRequest(@JsonProperty("name") String name,
                             @JsonProperty("email") String email,
                             @JsonProperty("password") String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
}
