package com.example.crudprac01.dto.request;

public class MemberCreateRequestDto {

    private String name;

    public MemberCreateRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
