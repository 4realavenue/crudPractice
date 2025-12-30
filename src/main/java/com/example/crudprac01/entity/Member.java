package com.example.crudprac01.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;
    
    @Column(name = "name", length = 20, nullable = false)
    private String name;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
    
    // spring이 사용하는 생성자
    protected Member() {
    }

    public Member(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // --------[메서드]---------
    public void updateMember(String name) {
        this.name = name;
    }

    public void deleteMember() {
        this.isDeleted = true;
    }
}
