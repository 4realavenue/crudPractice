package com.example.crudprac01.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "songs")
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "title", length = 100)
    private String title;

    @Column (name = "singer", length = 100)
    private String singer;

    // Spring이 사용하는 생성자입니다
    protected Song(Long id, String title, String singer) {
        this.id = id;
        this.title = title;
        this.singer = singer;
    }

    public Song(String title, String singer) {
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
