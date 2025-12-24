package com.example.crudprac01.dto.response;

import java.util.List;

public class UserGetAllResponseDto {
    private Integer count;
    private List<UserGetResponse> userLit;

    public UserGetAllResponseDto(Integer count, List<UserGetResponse> userLit) {
        this.count = count;
        this.userLit = userLit;
    }

    public Integer getCount() {
        return count;
    }

    public List<UserGetResponse> getUserLit() {
        return userLit;
    }

    public static class UserGetResponse {
        private long id;
        private String name;

        public UserGetResponse(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
