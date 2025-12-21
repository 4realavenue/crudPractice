package com.example.crudprac01.dto.response;

// 모든 응답의 공통 된 큰 틀 응답 형식. data는 제네릭 타입으로 받아서 매 사용 순간마다 그에 맞게 사용 되도록 설정.
public class ApiResponse<T> {

    private String message;
    private Integer status;
    private T data;

    public ApiResponse(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }
}
