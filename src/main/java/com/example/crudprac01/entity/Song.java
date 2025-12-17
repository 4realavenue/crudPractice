package com.example.crudprac01.entity;

import jakarta.persistence.*;

@Entity
@Table (name = "songs")
public class Song {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "title", length = 100)
    private String title;

    @Column (name = "singer", length = 100)
    private String singer;

    // Spring이 사용하는 생성자입니다
    protected Song() {}

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

    // 2. update 메서드 생성
    public void update(String title, String singer) {
        this.title = title;
        this.singer = singer;
    }
}
