package com.example.crudprac01.dto.response;

import java.util.List;

public class MemberGetListResponseDto {

    private Integer count;
    private List<MemberGetResponseDto> memberList;

    public MemberGetListResponseDto(Integer count, List<MemberGetResponseDto> memberList) {
        this.count = count;
        this.memberList = memberList;
    }

    public Integer getCount() {
        return count;
    }

    public List<MemberGetResponseDto> getMemberList() {
        return memberList;
    }


    // 내부 클래스
    public static class MemberGetResponseDto {

        private Long id;
        private String name;

        public MemberGetResponseDto(Long id, String name) {
            this.id = id;
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
