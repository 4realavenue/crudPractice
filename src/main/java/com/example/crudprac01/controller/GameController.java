package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.GameCreateRequestDto;
import com.example.crudprac01.dto.request.GameUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * 1. 게임 생성 (등록)
     */
    @PostMapping
    public ResponseEntity<ApiResponse<GameCreateResponseDto>> createGameApi(
            @RequestBody GameCreateRequestDto request
    ) {
        GameCreateResponseDto responseDto = gameService.createGame(request);

        ApiResponse<GameCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        ResponseEntity<ApiResponse<GameCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<ApiResponse<GameGetDetailResponseDto>> getGameDetailApi(
            @PathVariable ("gameId") Long gameId
    ) {
        GameGetDetailResponseDto responseDto = gameService.getGameDetail(gameId);

        ApiResponse<GameGetDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GameGetDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    /**
     * 3. 게임 전체 조회
     */
    @GetMapping
    public ResponseEntity<ApiResponse<GameGetAllResponseDto>> getGameAllApi() {

        GameGetAllResponseDto responseDto = gameService.getGameAll();

        ApiResponse<GameGetAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GameGetAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    /**
     * 4. 게임 정보 수정
     */
    @PutMapping("/{gameId}")
    public ResponseEntity<ApiResponse<GameUpdateResponseDto>> updateGameApi(
            @PathVariable ("gameId") Long gameId,
            @RequestBody GameUpdateRequestDto request
            ) {
        GameUpdateResponseDto responseDto = gameService.updateGame(gameId, request);

        ApiResponse<GameUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<GameUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    /**
     * 5. 등록 된 게임 삭제
     */
    @DeleteMapping("/{gameId}")
    public ResponseEntity<ApiResponse<GameDeleteResponseDto>> deleteGameApi(
            @PathVariable ("gameId") Long gameId
    ) {
        GameDeleteResponseDto responseDto = gameService.deleteGame(gameId);

        ApiResponse<GameDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<GameDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }


}
