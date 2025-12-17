package com.example.crudprac01.controller;

import com.example.crudprac01.dto.ApiResponse;
import com.example.crudprac01.dto.request.CreateUserRequest;
import com.example.crudprac01.dto.response.CreateUserResponse;
import com.example.crudprac01.dto.response.GetOneUserResponse;
import com.example.crudprac01.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<GetOneUserResponse>> getOneUserApi(@PathVariable ("userId") Long userId) {

        GetOneUserResponse responseDto = userService.getOneUser(userId);

        ApiResponse<GetOneUserResponse> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<GetOneUserResponse>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
