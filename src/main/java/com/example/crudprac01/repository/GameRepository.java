package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// JpaRepository를 상속 받아서 매핑 시켜줄 Entity와 그 Entity의 Id 타입을 넣어줌
public interface GameRepository extends JpaRepository<Game, Long> {

    // 찾는다, 어떤 조건으로? -> isDeleted의 값이 false인
    List<Game> findByIsDeletedFalse();
}
