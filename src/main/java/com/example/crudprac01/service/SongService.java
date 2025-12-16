package com.example.crudprac01.service;

import com.example.crudprac01.repository.SongRepository;
import org.springframework.stereotype.Service;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }
}
