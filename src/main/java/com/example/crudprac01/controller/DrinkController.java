package com.example.crudprac01.controller;

import com.example.crudprac01.service.DrinkService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/drinks")
public class DrinkController {
    private final DrinkService drinkService;

    public DrinkController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    // 음료수 등록
    @PostMapping
    public void createDrinkApi() {

    }

}
