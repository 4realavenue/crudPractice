package com.example.crudprac01.dto.response;

import java.time.LocalDate;
import java.util.List;

// 도메인 + 행동 + 응답명시 + DTO명시
public class GameGetAllResponseDto<T> {
    private Integer count;
    private List<GameGetListResponseDto> gameList;

    public GameGetAllResponseDto(Integer count, List<GameGetListResponseDto> gameList) {
        this.count = count;
        this.gameList = gameList;
    }

    // Spring이 사용하는 getter입니다.
    public Integer getCount() {
        return count;
    }

    public List<GameGetListResponseDto> getGameList() {
        return gameList;
    }

    // -- 유지 보수를 위한 내부 클래스 설계 입니다. --
    //    도메인 + 행동 + 응답명시 + DTO명시
    public static class GameGetListResponseDto {

        private Long id;
        private String name;
        private String company;
        private LocalDate releaseDate;

        public GameGetListResponseDto(Long id, String name, String company, LocalDate releaseDate) {
            this.id = id;
            this.name = name;
            this.company = company;
            this.releaseDate = releaseDate;
        }

        // Spring이 사용하는 getter입니다.
        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getCompany() {
            return company;
        }

        public LocalDate getReleaseDate() {
            return releaseDate;
        }
    }

}
