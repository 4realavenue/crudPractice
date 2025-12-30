package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.MemberRegisterRequestDto;
import com.example.crudprac01.dto.request.MemberUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.entity.Member;
import com.example.crudprac01.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public MemberService(MemberRepository memberRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    // 1. 회원가입 로직 (멤버 생성)
    @Transactional
    public MemberRegisterResponseDto registerMember(MemberRegisterRequestDto request) {

        String email = request.getEmail();
        String password = request.getPassword();
        String name = request.getName();

        // 이메일 중복 여부
        Boolean validExistEmail = memberRepository.existsByEmail(email);

        if (validExistEmail == true) {
            throw new RuntimeException("중복된 이메일 입니다");
        }

        // 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(password);

        Member member = new Member(
                email,
                encodePassword,
                name
        );

        Member savedMember = memberRepository.save(member);

        String jwtToken = jwtService.createJwtToken(
                savedMember.getId(),
                savedMember.getEmail(),
                savedMember.getName()
        );

        MemberRegisterResponseDto responseDto = new MemberRegisterResponseDto(
                jwtToken
        );

        return responseDto;
    }

    // 2. 멤버 단건 조회
    @Transactional(readOnly = true)
    public MemberGetDetailResponseDto getDetailMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다."));

        MemberGetDetailResponseDto responseDto = new MemberGetDetailResponseDto(
                findMember.getId(),
                findMember.getName()
        );

        return responseDto;
    }

    // 3. 멤버 다건 조회
    @Transactional(readOnly = true)
    public MemberGetListResponseDto getListMember() {
        List<Member> memberList = memberRepository.findByIsDeletedFalse();

        Integer count = memberList.size();

        List<MemberGetListResponseDto.MemberGetResponseDto> memberDtoList = new ArrayList<>();

        for (Member member : memberList) {
            MemberGetListResponseDto.MemberGetResponseDto memberDto = new MemberGetListResponseDto.MemberGetResponseDto(
                    member.getId(),
                    member.getName()
            );

            memberDtoList.add(memberDto);
        }

        MemberGetListResponseDto responseDto = new MemberGetListResponseDto(count, memberDtoList);

        return responseDto;
    }

    // 4. 멤버 수정
    @Transactional
    public MemberUpdateResponseDto updateMember(Long memberId, MemberUpdateRequestDto request) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다"));

        findMember.updateMember(request.getName());

        MemberUpdateResponseDto responseDto = new MemberUpdateResponseDto(findMember.getId());

        return responseDto;
    }

    // 5. 멤버 삭제
    @Transactional
    public MemberDeleteResponseDto deleteMember(Long memberId) {
        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 멤버입니다"));

        if (findMember.getDeleted() == true) {
            throw new IllegalArgumentException("이미 삭제된 멤버입니다");
        }

        findMember.deleteMember();

        MemberDeleteResponseDto responseDto = new MemberDeleteResponseDto(findMember.getId());

        return responseDto;
    }

}
