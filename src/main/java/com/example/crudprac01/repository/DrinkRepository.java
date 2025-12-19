package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Drink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findByIsDeletedFalse();
}
