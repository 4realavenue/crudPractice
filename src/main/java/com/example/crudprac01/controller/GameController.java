package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.GameCreateRequestDto;
import com.example.crudprac01.dto.request.GameUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// 컴포넌트 스캔 (Controller와 RequestBody가 합쳐진 RESTful Api용 컨트롤러)
@RestController
// /api/games url로 요청이 들어오면 이 컨트롤러가 받겠다.
@RequestMapping("/api/games")
public class GameController {

    // GameService 타입의 gameService 변수를 선언함
    private final GameService gameService;

    // final로 선언 된 필드를 생성자로 초기화 시켜줌
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    /**
     * 1. 게임 생성 (등록)
     */
    // 어떤 행동을 할지 정해주는 HTTP Method에 POST로 "생성" 행동 해달라는 요청을 받겠다. 추가 url은 없음.
    @PostMapping
    // 1-1. 반환 타입 일치 시켜주고, createGameApi로 네이밍 해서 Api임을 명시해줌.
    //      @RequestBody 어노테이션으로 Body를 입력받게 해줌 (입력 받는 형식은 GameCreateRequestDto), 그 변수 이름은 request로 설정
    public ResponseEntity<ApiResponse<GameCreateResponseDto>> createGameApi(
            @RequestBody GameCreateRequestDto request
    ) {
        // 1-2. 서비스의 createGame 메서드에 요청 받은 request를 매개변수를 넣고 받은 반환값을 GameCreateResponseDto 타입으로 받아서 responseDto 변수에 저장.
        GameCreateResponseDto responseDto = gameService.createGame(request);

        // 1-3. ApiResponse 타입으로 apiResponse 변수를 만들고 그 안에 새로운 ApiResponse 객체 생성.
        //      생성자에 맞춰서 message와 status와 data를 넣어줌 (data는 responseDto)
        ApiResponse<GameCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        // 1-4. ResponseEntity 타입으로 response 변수를 만들어주고 그 안에 Body를 apiResponse로 넣어주고, HttpStatus로 응답 상태코드 정해줌.
        ResponseEntity<ApiResponse<GameCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        // 1-5. response 반환
        return response;
    }

    /**
     * 2. 게임 단건 조회
     */
    // HTTP Method에 GET으로 "조회" 행동 해달라는 요청을 받겠다. gameId 라는 자원 식별자를 추가 url로 요청 받겠다.
    @GetMapping("/{gameId}")
    // 2-1. 반환 타입 일치 시켜주고, getGameDetailApi로 네이밍 해서 게임을 자세히 조회 하는 Api임을 명시해줌.
    //      @PathVariable로 자원 식별자를 받겠다. 자원 식별자 이름은 gameId로 하겠다고 명시 해주고, Long 타입으로 gameId 변수 생성
    public ResponseEntity<ApiResponse<GameGetDetailResponseDto>> getGameDetailApi(
            @PathVariable ("gameId") Long gameId
    ) {
        // 2-2. 서비스의 getGameDetail 메서드에 요청받은 gameId를 매개변수로 넣고, 받은 반환값을 GameGetDetailResponseDto 타입으로 받아서 responseDto 변수에 저장
        GameGetDetailResponseDto responseDto = gameService.getGameDetail(gameId);

        // 2-3. ApiResponse 타입으로 apiResponse 변수를 만들고 그 안에 새로운 ApiResponse 객체 생성.
        //      생성자에 맞춰서 message와 status와 data를 넣어줌 (data는 responseDto)
        ApiResponse<GameGetDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        // 2-4. ResponseEntity 타입으로 response 변수를 만들어주고 그 안에 Body를 apiResponse로 넣어주고, HttpStatus로 응답 상태코드 정해줌.
        ResponseEntity<ApiResponse<GameGetDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        // 2-5. response 반환
        return response;
    }

    /**
     * 3. 게임 전체 조회
     */
    // HTTP Method에 GET으로 "조회" 행동 해달라는 요청을 받겠다. 추가 url은 없음.
    @GetMapping
    // 3-1. 반환 타입 일치 시켜주고, getGameAllApi로 네이밍 해서 모든 게임들을 조회 하는 Api임을 명시해줌.
    public ResponseEntity<ApiResponse<GameGetAllResponseDto>> getGameAllApi() {

        // 3-2. 서비스의 getGameAll 메서드의 반환 값을 GameGetAllResponseDto 타입으로 받아서 responseDto 변수에 저장
        GameGetAllResponseDto responseDto = gameService.getGameAll();

        // 3-3. ApiResponse 타입으로 apiResponse 변수를 만들고 그 안에 새로운 ApiResponse 객체 생성.
        //      생성자에 맞춰서 message와 status와 data를 넣어줌 (data는 responseDto)
        ApiResponse<GameGetAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        // 3-4. ResponseEntity 타입으로 response 변수를 만들어주고 그 안에 Body를 apiResponse로 넣어주고, HttpStatus로 응답 상태코드 정해줌.
        ResponseEntity<ApiResponse<GameGetAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        // 3-5. response 반환
        return response;
    }

    /**
     * 4. 게임 정보 수정
     */
    // HTTP Method에 PUT으로 "통째로 수정" 행동 해달라는 요청을 받겠다. gameId 라는 자원 식별자를 추가 url로 요청 받겠다.
    @PutMapping("/{gameId}")
    // 4-1. 반환 타입 일치 시켜주고, updateGameApi로 네이밍 해서 게임의 정보를 수정하는 Api임을 명시해줌.
    //      @PathVariable로 자원 식별자를 받겠다. 자원 식별자 이름은 gameId로 하겠다고 명시 해주고, Long 타입으로 gameId 변수 생성
    //      @RequestBody 어노테이션으로 Body를 입력받게 해줌 (입력 받는 형식은 GameUpdateRequestDto), 그 변수 이름은 request로 설정
    public ResponseEntity<ApiResponse<GameUpdateResponseDto>> updateGameApi(
            @PathVariable ("gameId") Long gameId,
            @RequestBody GameUpdateRequestDto request
            ) {
        // 4-2. 서비스의 updateGame 메서드에 요청받은 gameId와 request 변수를 넣고, 받은 반환값을 GameUpdateResponseDto 타입으로 responseDto에 집어넣어줌
        GameUpdateResponseDto responseDto = gameService.updateGame(gameId, request);

        // 4-3. ApiResponse 타입으로 apiResponse 변수를 만들고 그 안에 새로운 ApiResponse 객체 생성.
        //      생성자에 맞춰서 message와 status와 data를 넣어줌 (data는 responseDto)
        ApiResponse<GameUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        // 4-4. ResponseEntity 타입으로 response 변수를 만들어주고 그 안에 Body를 apiResponse로 넣어주고, HttpStatus로 응답 상태코드 정해줌.
        ResponseEntity<ApiResponse<GameUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        // 4-5. response 반환
        return response;
    }

    /**
     * 5. 등록 된 게임 삭제
     */
    // HTTP Method에 Delete로 "삭제" 행동 해달라는 요청을 받겠다. gameId 라는 자원 식별자를 추가 url로 요청 받겠다.
    @DeleteMapping("/{gameId}")
    // 5-1. 반환 타입 일치 시켜주고, deleteGameApi로 네이밍 해서 게임을 삭제하는 Api임을 명시해줌.
    //      @PathVariable로 자원 식별자를 받겠다. 자원 식별자 이름은 gameId로 하겠다고 명시 해주고, Long 타입으로 gameId 변수 생성
    public ResponseEntity<ApiResponse<GameDeleteResponseDto>> deleteGameApi(
            @PathVariable ("gameId") Long gameId
    ) {
        // 5-2. 서비스의 deleteGame 메서드에 요청받은 gameId 변수를 넣고, 받은 반환값을 GameDeleteResponseDto 타입으로 responseDto 변수에 넣어줌
        GameDeleteResponseDto responseDto = gameService.deleteGame(gameId);

        // 5-3. ApiResponse 타입으로 apiResponse 변수를 만들고 그 안에 새로운 ApiResponse 객체 생성.
        //      생성자에 맞춰서 message와 status와 data를 넣어줌 (data는 responseDto)
        ApiResponse<GameDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        // 5-4. ResponseEntity 타입으로 response 변수를 만들어주고 그 안에 Body를 apiResponse로 넣어주고, HttpStatus로 응답 상태코드 정해줌.
        ResponseEntity<ApiResponse<GameDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        // 5-5. response 반환
        return response;
    }


}
