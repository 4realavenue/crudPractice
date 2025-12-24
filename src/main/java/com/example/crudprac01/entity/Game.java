package com.example.crudprac01.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Entity 어노테이션으로 이 클래스를 Entity로 설정
@Entity
// Mapping 할 때 games라는 이름의 테이블과 매핑 되도록 설정
@Table (name = "games")
public class Game {

    // Entity의 필수 요소. 해당 필드값에 Id를 붙여줌
    @Id
    // 자동으로 생성되도록 설정
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 id 임을 명시
    @Column(name = "id")
    private Long id;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 name 임을 명시
    // null 허용 안함, 중복 불가능
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 company 임을 명시
    // null 허용 안함
    @Column(name = "company", nullable = false)
    private String company;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 release_date 임을 명시
    // null 허용 안함
    @Column(name = "release_date", nullable = false)
    private LocalDate releaseDate;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 created_at 임을 명시
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 updated_at 임을 명시
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 매핑 된 games 테이블에 컬럼에 표시 될 이름은 is_deleted 임을 명시
    // 기본 값은 false로 설정
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    // 영속화 되기 전에
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // 수정 되기 전에
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    // Spring이 사용하는 생성자 입니다.
    protected Game() {}

    public Game(String name, String company, LocalDate releaseDate) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public LocalDateTime getUpdateAt() {
        return updatedAt;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    // -----------------[method]--------------------
    
    // 매개변수로 name, company, releaseDate, updatedAt 을 받고 updateGame 메서드를 실행 시키면 이 Entity의 해당 값들이 인자값으로 받은 데이터로 변경 되도록 함
    public void updateGame(String name, String company, LocalDate releaseDate, LocalDateTime updatedAt) {
        this.name = name;
        this.company = company;
        this.releaseDate = releaseDate;
        this.updatedAt = updatedAt;
    }
    
    // deleteGame 메서드를 실행 시키면 이 Entity의 isDeleted가 true가 (soft delete) 되도록 함 
    public void deleteGame() {
        this.isDeleted = true;
    }
}
