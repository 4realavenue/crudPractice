package com.example.crudprac01.dto.response;

import java.util.List;

public class GetAllUserResponse {
    private Integer count;
    private List<GetUserListResponse> userList;

    public GetAllUserResponse(Integer count, List<GetUserListResponse> userList) {
        this.count = count;
        this.userList = userList;
    }

    public Integer getCount() {
        return count;
    }

    public List<GetUserListResponse> getUserList() {
        return userList;
    }

    public static class GetUserListResponse {
        private long id;
        private String name;
        private String email;

        public GetUserListResponse(long id, String name, String email) {
            this.id = id;
            this.name = name;
            this.email = email;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
