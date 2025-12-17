package com.example.crudprac01.dto.request;

public class UpdateSongRequestDto {
    private String title;
    private String singer;

    public UpdateSongRequestDto(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }
}
