package com.example.crudprac01.dto.response;

public class CreateSongResponseDto {
    private Long id;
    private String title;
    private String singer;

    public CreateSongResponseDto(Long id, String title, String singer) {
        this.id = id;
        this.title = title;
        this.singer = singer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }
}
