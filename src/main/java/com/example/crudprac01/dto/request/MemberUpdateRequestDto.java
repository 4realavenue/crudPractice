package com.example.crudprac01.dto.request;

public class MemberUpdateRequestDto {

    private String name;

    public MemberUpdateRequestDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
