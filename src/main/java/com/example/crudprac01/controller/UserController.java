package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.UserCreateRequestDto;
import com.example.crudprac01.dto.request.UserUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserCreateResponseDto>> createUserApi(@RequestBody UserCreateRequestDto requestDto) {
        UserCreateResponseDto responseDto = userService.createUser(requestDto);

        ApiResponse<UserCreateResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        ResponseEntity<ApiResponse<UserCreateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserGetDetailResponseDto>> getUserDetailApi(@PathVariable ("userId") Long userId) {
        UserGetDetailResponseDto responseDto = userService.getUserDetail(userId);

        ApiResponse<UserGetDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<UserGetDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<UserGetAllResponseDto>> getUserAllApi() {
        UserGetAllResponseDto responseDto = userService.getUserAll();

        ApiResponse<UserGetAllResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<UserGetAllResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponse<UserUpdateResponseDto>> updateUserApi(
            @PathVariable ("userId") long userId,
            @RequestBody UserUpdateRequestDto request
    ) {
        UserUpdateResponseDto responseDto = userService.updateUser(userId ,request);

        ApiResponse<UserUpdateResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<UserUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;


    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable ("userId") long userId) {
        userService.deleteUser(userId);
    }

}
