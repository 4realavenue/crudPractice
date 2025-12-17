package com.example.crudprac01.repository;

import com.example.crudprac01.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
