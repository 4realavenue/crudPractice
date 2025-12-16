package com.example.crudprac01.repository;

import com.example.crudprac01.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository <Song, Long> {
}
