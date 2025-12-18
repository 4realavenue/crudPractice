package com.example.crudprac01.controller;

import com.example.crudprac01.dto.ApiResponse;
import com.example.crudprac01.dto.request.CreateUserRequest;
import com.example.crudprac01.dto.request.UpdateUserRequest;
import com.example.crudprac01.dto.response.CreateUserResponse;
import com.example.crudprac01.dto.response.GetAllUserResponse;
import com.example.crudprac01.dto.response.GetOneUserResponse;
import com.example.crudprac01.dto.response.UpdateUserResponse;
import com.example.crudprac01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 유저 생성
    @PostMapping
    public ResponseEntity<ApiResponse<CreateUserResponse>> createUserApi(@RequestBody CreateUserRequest request) {

        CreateUserResponse responseDto = userService.createUser(request);

        ApiResponse<CreateUserResponse> apiResponse = new ApiResponse("success", 201, responseDto);

        ResponseEntity<ApiResponse<CreateUserResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 유저 단건 조회
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<GetOneUserResponse>> getOneUserApi(@PathVariable ("userId") Long userId) {

        GetOneUserResponse responseDto = userService.getOneUser(userId);

        ApiResponse<GetOneUserResponse> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GetOneUserResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 유저 전체 조회
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAllUserResponse>>> getAllUserApi() {
        List<GetAllUserResponse> responseDto = userService.getAllUser();

        ApiResponse<List<GetAllUserResponse>> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<List<GetAllUserResponse>>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 유저 정보(이름) 일부 수정
    @PatchMapping("/{userId}")
    public ResponseEntity<ApiResponse<UpdateUserResponse>> updateUser(
            @PathVariable ("userId") long userId,
            @RequestBody UpdateUserRequest request
            ) {
        UpdateUserResponse responseDto = userService.updateUser(userId, request);

        ApiResponse<UpdateUserResponse> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<UpdateUserResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 유저 삭제
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable ("userId") Long userId) {
        userService.deleteUser(userId);

        ApiResponse apiResponse = new ApiResponse<>("delete success", 200, null);

        ResponseEntity<ApiResponse> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

}
