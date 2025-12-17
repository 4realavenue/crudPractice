package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.CreateSongRequestDto;
import com.example.crudprac01.dto.response.ApiResponse;
import com.example.crudprac01.dto.response.CreateSongResponseDto;
import com.example.crudprac01.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;
    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    public SongController(SongService songService) {
        this.songService = songService;
    }

    /**
     * 노래 생성
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CreateSongResponseDto>> createSongApi(@RequestBody CreateSongRequestDto request) {
        // 1. controller 확인
        log.info("컨트롤러 - 테스트 입니다.");

        // 9. Service 레이어에서 메서드 실행 시키고 responseDto에 저장하기
        CreateSongResponseDto responseDto = songService.createSong(request);

        // 10. ApiResponse로 responseDto 감싸기
        ApiResponse<CreateSongResponseDto> apiResponse = new ApiResponse("created", 201, responseDto);

        // 11. ResponseEntity로 ApiResponse 감싸기
        ResponseEntity<ApiResponse<CreateSongResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;

    }

}
