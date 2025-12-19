package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.DrinkCreateRequestDto;
import com.example.crudprac01.dto.request.DrinkUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.DrinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    // 1. 음료수 등록 기능
    @PostMapping
    public ResponseEntity<ApiResponse<DrinkCreateResponseDto>> createDrinkApi(@RequestBody DrinkCreateRequestDto requestDto) {

        DrinkCreateResponseDto responseDto = drinkService.createDrink(requestDto);

        ApiResponse<DrinkCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        ResponseEntity<ApiResponse<DrinkCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 2. 음료수 단건 조회 기능
    @GetMapping("/{drinkId}")
    public ResponseEntity<ApiResponse<DrinkGetDetailResponseDto>> getDrinkDetailApi(@PathVariable ("drinkId") long drinkId) {

        DrinkGetDetailResponseDto responseDto = drinkService.getDrinkDetail(drinkId);

        ApiResponse<DrinkGetDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<DrinkGetDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;

    }

    // 3. 음료수 전체 조회 기능
    @GetMapping
    public ResponseEntity<ApiResponse<DrinkGetAllResponseDto>> getDrinkAllApi() {

        DrinkGetAllResponseDto responseDto = drinkService.getDrinkAll();

        ApiResponse<DrinkGetAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<DrinkGetAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;

    }

    // 4. 음료수 정보 수정 기능
    @PutMapping("/{drinkId}")
    public ResponseEntity<ApiResponse<DrinkUpdateResponseDto>> updateDrinkApi(
            @PathVariable ("drinkId") long drinkId,
            @RequestBody DrinkUpdateRequestDto requestDto
            ) {

        DrinkUpdateResponseDto responseDto = drinkService.updateDrink(drinkId, requestDto);

        ApiResponse<DrinkUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<DrinkUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 5. 음료수 삭제 기능 (소프트 딜리트)
    @DeleteMapping("/{drinkId}")
    public ResponseEntity<ApiResponse<DrinkDeleteResponseDto>> deleteDrinkApi(@PathVariable ("drinkId") long drinkId) {

        DrinkDeleteResponseDto responseDto = drinkService.deleteDrink(drinkId);

        ApiResponse<DrinkDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<DrinkDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

}
