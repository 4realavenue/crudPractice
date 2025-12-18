package com.example.crudprac01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column (name = "name", length = 30)
    private String name;

    @Column (name = "email", length = 100, unique = true)
    private String email;

    @Column (name = "password", length = 100)
    private String password;

    @Column (name = "created_at")
    private LocalDateTime createdAt;

    @Column (name = "modifiedAt")
    private LocalDateTime modifiedAt;

    private Boolean isDeleted = false;

    // Spring이 사용하는 생성자 입니다.
    protected User() {}

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void update(String name) {
        this.name = name;
    }
}
