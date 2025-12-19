package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.DrinkCreateRequestDto;
import com.example.crudprac01.dto.request.DrinkUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.entity.Drink;
import com.example.crudprac01.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    // 1. 음료수 등록 기능
    @Transactional
    public DrinkCreateResponseDto createDrink(DrinkCreateRequestDto requestDto) {
        Drink newDrink = new Drink(
                requestDto.getName(),
                requestDto.getDescription(),
                requestDto.getPrice(),
                requestDto.getReleaseDate()
        );

        Drink saveDrink = drinkRepository.save(newDrink);

        DrinkCreateResponseDto responseDto = new DrinkCreateResponseDto(
                saveDrink.getId(),
                saveDrink.getName(),
                saveDrink.getPrice(),
                saveDrink.getDescription(),
                saveDrink.getReleaseDate()
        );

        return responseDto;
    }

    // 2. 음료수 단건 조회 기능
    public DrinkGetDetailResponseDto getDrinkDetail(long drinkId) {
        Drink findDrink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 음료수 입니다."));

        DrinkGetDetailResponseDto responseDto = new DrinkGetDetailResponseDto(
                findDrink.getId(),
                findDrink.getName(),
                findDrink.getPrice(),
                findDrink.getDescription(),
                findDrink.getReleaseDate()
        );

        return responseDto;
    }

    // 3. 음료수 전체 조회 기능
    public DrinkGetAllResponseDto getDrinkAll() {
        List<Drink> drinkList = drinkRepository.findByIsDeletedFalse();

        //count 준비
        Integer count = drinkList.size();

        // drinkList 준비
        List<DrinkGetAllResponseDto.DrinkGetResponse> responseDtoList = new ArrayList<>();

        for (Drink drink : drinkList) {
            DrinkGetAllResponseDto.DrinkGetResponse drinkDto = new DrinkGetAllResponseDto.DrinkGetResponse(
                    drink.getId(),
                    drink.getName(),
                    drink.getPrice()
            );

            responseDtoList.add(drinkDto);
        }

        DrinkGetAllResponseDto responseDto = new DrinkGetAllResponseDto(count, responseDtoList);

        return responseDto;
    }

    // 4. 음료수 정보 수정 기능
    public DrinkUpdateResponseDto updateDrink(long drinkId, DrinkUpdateRequestDto request) {
        Drink findDrink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 음료수 입니다."));

        findDrink.update(
                request.getName(),
                request.getPrice()
        );

        DrinkUpdateResponseDto responseDto = new DrinkUpdateResponseDto(
                findDrink.getId(),
                findDrink.getName(),
                findDrink.getPrice(),
                findDrink.getDescription(),
                findDrink.getReleaseDate()
        );

        return responseDto;
    }

    // 5. 음료수 삭제 기능 (소프트 딜리트)
    public DrinkDeleteResponseDto deleteDrink(long drinkId) {
        Drink findDrink = drinkRepository.findById(drinkId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 음료수 입니다."));

        if (findDrink.getIsDeleted() == true) {
            throw new IllegalArgumentException("이미 등록 제거 된 음료수 입니다.");
        }

        findDrink.delete();

        DrinkDeleteResponseDto responseDto = new DrinkDeleteResponseDto(
                findDrink.getId(),
                findDrink.getName()
        );

        return responseDto;
    }


}
