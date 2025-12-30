package com.example.crudprac01.controller;

import com.example.crudprac01.dto.request.MemberRegisterRequestDto;
import com.example.crudprac01.dto.request.MemberUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    // 1. 회원가입 API (멤버 생성)
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<MemberRegisterResponseDto>> registerMemberApi(
            @RequestBody MemberRegisterRequestDto request
            ) {

        MemberRegisterResponseDto responseDto = memberService.registerMember(request);

        ApiResponse<MemberRegisterResponseDto> apiResponse = new ApiResponse<>("created", 201, responseDto);

        ResponseEntity<ApiResponse<MemberRegisterResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);

        return response;
    }

    // 2. 멤버 단건 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<ApiResponse<MemberGetDetailResponseDto>> getMemberDetailApi(
            @PathVariable ("memberId") Long memberId
    ) {

        MemberGetDetailResponseDto responseDto = memberService.getDetailMember(memberId);

        ApiResponse<MemberGetDetailResponseDto> apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<MemberGetDetailResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 3. 멤버 다건 조회
    @GetMapping
    public ResponseEntity<ApiResponse<MemberGetListResponseDto>> getMemberAllApi() {

        MemberGetListResponseDto responseDto = memberService.getListMember();

        ApiResponse apiResponse = new ApiResponse<>("success", 200, responseDto);

        ResponseEntity<ApiResponse<MemberGetListResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 4. 멤버 수정
    @PatchMapping("/memberId")
    public ResponseEntity<ApiResponse<MemberUpdateResponseDto>> updateMemberApi(
            @PathVariable ("memberId") Long memberId,
            @RequestBody MemberUpdateRequestDto request
            ) {

        MemberUpdateResponseDto responseDto = memberService.updateMember(memberId, request);

        ApiResponse<MemberUpdateResponseDto> apiResponse = new ApiResponse<>("updated", 200, responseDto);

        ResponseEntity<ApiResponse<MemberUpdateResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }

    // 5. 멤버 삭제
    @DeleteMapping("/memberId")
    public ResponseEntity<ApiResponse<MemberDeleteResponseDto>> deleteMemberApi(
            @PathVariable ("memberId") Long memberId
    ) {
        MemberDeleteResponseDto responseDto = memberService.deleteMember(memberId);

        ApiResponse<MemberDeleteResponseDto> apiResponse = new ApiResponse<>("deleted", 200, responseDto);

        ResponseEntity<ApiResponse<MemberDeleteResponseDto>> response = new ResponseEntity<>(apiResponse, HttpStatus.OK);

        return response;
    }
}
