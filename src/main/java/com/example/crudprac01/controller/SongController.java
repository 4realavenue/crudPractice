package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.CreateSongRequestDto;
import com.example.crudprac01.dto.response.ApiResponse;
import com.example.crudprac01.dto.response.CreateSongResponseDto;
import com.example.crudprac01.dto.response.GetSongAllResponseDto;
import com.example.crudprac01.dto.response.GetSongDetailResponseDto;
import com.example.crudprac01.service.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 단건 조회
     *
     * @param songId
     * @return
     */
    @GetMapping("/{songId}")
    public ResponseEntity<ApiResponse<GetSongDetailResponseDto>> getSongDetailApi(@PathVariable("songId") Long songId) {
        // 1. 컨트롤러 확인
        log.info("컨트롤러 - 단건 조회");


        GetSongDetailResponseDto responseDto = songService.getSongDetail(songId);

        ApiResponse<GetSongDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GetSongDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;

    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GetSongAllResponseDto>>> getSongAllApi() {
        // 1. 컨트롤러 확인
        log.info("컨트롤러 - 전체 조회");

        // 7. songService 에서 getSongAll 메서드를 실행시키고 반환된 값을 response에 담아줌
        List<GetSongAllResponseDto> responseDtos = songService.getSongAll();

        ApiResponse<List<GetSongAllResponseDto>> apiResponse = new ApiResponse<>("success", 200, responseDtos);

        ResponseEntity<ApiResponse<List<GetSongAllResponseDto>>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
