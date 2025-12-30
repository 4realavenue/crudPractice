package com.example.crudprac01.dto.response;

public class MemberLoginResponseDto {

    private String token;

    public MemberLoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
