package com.example.crudprac01.controller;

import com.example.crudprac01.dto.MemberLoginRequestDto;
import com.example.crudprac01.dto.response.ApiResponse;
import com.example.crudprac01.dto.response.MemberLoginResponseDto;
import com.example.crudprac01.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<MemberLoginResponseDto>> loginApi(@RequestBody MemberLoginRequestDto requestDto) {

        MemberLoginResponseDto responseDto = authService.login(requestDto);

        ApiResponse<MemberLoginResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<MemberLoginResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
