package com.example.crudprac01.controller;

import com.example.crudprac01.dto.MemberLoginRequestDto;
import com.example.crudprac01.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public void loginApi(@RequestBody MemberLoginRequestDto requestDto) {
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        log.info("email : {} ", email);
        log.info("password : {} ", password);

    }
}
