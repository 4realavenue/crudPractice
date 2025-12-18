package com.example.crudprac01.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "drinks")
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "releaseDate")
    private LocalDate releaseDate;

    @Column(name = "isDeleted")
    private Boolean isDeleted;


    // 스프링이 사용하는 기본 생성자 입니다. 건들지 마쇼. 이건 경고요.
    protected Drink() {}

    // 생성자
    public Drink(String name, String description, Integer price, LocalDate releaseDate) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.releaseDate = releaseDate;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
}
