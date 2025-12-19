package com.example.crudprac01.dto.response;

import java.util.List;

public class DrinkGetAllResponseDto {
    private Integer count;
    private List<DrinkGetResponse> drinkList;

    public DrinkGetAllResponseDto(Integer count, List<DrinkGetResponse> drinkList) {
        this.count = count;
        this.drinkList = drinkList;
    }

    public Integer getCount() {
        return count;
    }

    public List<DrinkGetResponse> getDrinkList() {
        return drinkList;
    }


    // 음료수 리스트
    public static class DrinkGetResponse {
        private long id;
        private String name;
        private Integer price;

        public DrinkGetResponse(long id, String name, Integer price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        public long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }
    }
}
