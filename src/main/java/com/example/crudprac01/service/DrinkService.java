package com.example.crudprac01.service;

import com.example.crudprac01.repository.DrinkRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DrinkService {
    private final DrinkRepository drinkRepository;

    public DrinkService(DrinkRepository drinkRepository) {
        this.drinkRepository = drinkRepository;
    }

    @Transactional
    public void createDrink() {

    }
}
