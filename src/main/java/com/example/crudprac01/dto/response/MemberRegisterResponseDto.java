package com.example.crudprac01.dto.response;

public class MemberRegisterResponseDto {

    private String token;

    public MemberRegisterResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
