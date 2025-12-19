package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.GameCreateRequestDto;
import com.example.crudprac01.dto.request.GameUpdateRequestDto;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.entity.Game;
import com.example.crudprac01.repository.GameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    private final GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * 1. 게임 생성 (등록)
     */
    @Transactional
    public GameCreateResponseDto createGame(GameCreateRequestDto request) {
        Game game = new Game(
                request.getName(),
                request.getCompany(),
                request.getReleaseDate()
        );

        Game savedGame = gameRepository.save(game);

        GameCreateResponseDto responseDto = new GameCreateResponseDto(
                savedGame.getId(),
                savedGame.getName(),
                savedGame.getCompany(),
                savedGame.getReleaseDate()
        );

        return responseDto;
    }

    /**
     * 2. 게임 단건 조회
     */
    @Transactional(readOnly = true)
    public GameGetDetailResponseDto getGameDetail(Long gameId) {
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("찾을 수 없는 게임입니다.");
        }

        GameGetDetailResponseDto responseDto = new GameGetDetailResponseDto(
                findGame.getId(),
                findGame.getName(),
                findGame.getCompany(),
                findGame.getReleaseDate()
        );

        return responseDto;
    }

    /**
     * 3. 게임 전체 조회
     */
    @Transactional(readOnly = true)
    public GameGetAllResponseDto getGameAll() {
        List<Game> findGameList = gameRepository.findByIsDeletedFalse();

        Integer countGame = findGameList.size();

        List<GameGetAllResponseDto.GameGetListResponseDto> responseDtoList = new ArrayList<>();

        for (Game game : findGameList) {
            GameGetAllResponseDto.GameGetListResponseDto responseGameDto = new GameGetAllResponseDto.GameGetListResponseDto(
                    game.getId(),
                    game.getName(),
                    game.getCompany(),
                    game.getReleaseDate()
            );

            responseDtoList.add(responseGameDto);
        }

        GameGetAllResponseDto responseDto = new GameGetAllResponseDto(countGame, responseDtoList);

        return responseDto;
    }

    /**
     * 4. 게임 정보 수정
     */
    @Transactional
    public GameUpdateResponseDto updateGame(Long gameId, GameUpdateRequestDto request) {
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("찾을 수 없는 게임입니다.");
        }

        findGame.updateGame(
                request.getName(),
                request.getCompany(),
                request.getReleaseDate(),
                LocalDateTime.now()
        );

        GameUpdateResponseDto responseDto = new GameUpdateResponseDto(
                findGame.getId(),
                findGame.getName(),
                findGame.getCompany(),
                findGame.getReleaseDate(),
                findGame.getUpdateAt()
        );

        return responseDto;
    }

    /**
     * 5. 등록 된 게임 삭제
     */
    @Transactional
    public GameDeleteResponseDto deleteGame(Long gameId) {
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("이미 제거 된 게임입니다.");
        }

        findGame.deleteGame();

        GameDeleteResponseDto responseDto = new GameDeleteResponseDto(
                findGame.getId(),
                findGame.getName()
        );

        return responseDto;


    }



}
