package com.example.crudprac01.service;

import com.example.crudprac01.dto.MemberLoginRequestDto;
import com.example.crudprac01.entity.Member;
import com.example.crudprac01.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(MemberRepository memberRepository, PasswordEncoder passwordEncoder) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 로그인 처리
    public void login(MemberLoginRequestDto requestDto) {

        // 데이터 준비
        String email = requestDto.getEmail();
        String password = requestDto.getPassword();

        // 회원 조회
        Member foundMember = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 유저입니다"));

        String encodePassword = foundMember.getPassword();

        // 입력 받은 비밀번호와 기존 회원의 암호화 된 비밀번호 비교
        boolean match = passwordEncoder.matches(password, encodePassword);

        if (!match) {
            throw new RuntimeException("잘못된 비밀번호 입니다");
        }


    }
}
