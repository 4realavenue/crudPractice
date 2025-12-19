package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByIsDeletedFalse();
}
