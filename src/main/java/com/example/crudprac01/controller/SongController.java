package com.example.crudprac01.controller;

import com.example.crudprac01.service.SongService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }
}
