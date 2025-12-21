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

// 컴포넌트 스캔 (이 클래스는 서비스다.)
@Service
public class GameService {

    // GameRepository 타입의 gameRepository 변수를 선언
    private final GameRepository gameRepository;

    // final로 선언 된 필드를 초기화 해줌
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    /**
     * 1. 게임 생성 (등록)
     */
    @Transactional
    // 1-1. 메서드 이름을 createGame으로 게임 생성하는 메서드임을 명시 해줌
    //      GameCreateRequestDto 타입의 request 변수를 매개변수로 입력 받겠다.
    public GameCreateResponseDto createGame(GameCreateRequestDto request) {

        // 1-2. Game 타입의 game 변수에, 요청시에 받은 데이터의 name, company, releaseDate를 넣어서 Game 객체를 생성함.
        Game game = new Game(
                request.getName(),
                request.getCompany(),
                request.getReleaseDate()
        );

        // 1-3. gameRepository에 save 메서드로 game 객체를 저장함. 그 후에 Game 타입의 savedGame 변수에 집어 넣음.
        Game savedGame = gameRepository.save(game);

        // 1-4. GameCreateResponseDto 타입의 responseDto 변수에
        //      repository를 통해 DB에 저장 했던 savedGame 데이터를 가져와서 dto로 감싸줌 (보여주고 싶은 데이터만 보여주고, DB의 데이터에 직접 접근 못하도록)
        GameCreateResponseDto responseDto = new GameCreateResponseDto(
                savedGame.getId(),
                savedGame.getName(),
                savedGame.getCompany(),
                savedGame.getReleaseDate()
        );

        // 1-5. responseDto 반환
        return responseDto;
    }

    /**
     * 2. 게임 단건 조회
     */
    // 읽기 전용
    @Transactional(readOnly = true)
    // 2-1. 메서드 이름을 getGameDetail로 게임을 자세히 조회 하는 메서드임을 명시해줌
    //      gameId 변수를 매개변수로 입력 받겠다.
    public GameGetDetailResponseDto getGameDetail(Long gameId) {

        // 2-2. 레포지토리에서 findById 메서드에 입력 받은 gameId를 통해서 자원을 식별해오고, 그 데이터를 findGame 변수에 집어 넣어줌
        //      findById로 Game이 Optional이 되어 null일 경우엔 예외를 던져서 해결
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        // 2-3. 자원 식별자로 찾으려는 게임의 isDeleted가 true일 땐(삭제 되었을 땐) 예외 던짐
        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("찾을 수 없는 게임입니다.");
        }

        // 2-4. GameGetDetailResponseDto 타입의 responseDto 변수에 찾아온 게임의 id, name, company, releaseDate를 넣어서 객체 생성
        GameGetDetailResponseDto responseDto = new GameGetDetailResponseDto(
                findGame.getId(),
                findGame.getName(),
                findGame.getCompany(),
                findGame.getReleaseDate()
        );

        // 2-5. responseDto 반환
        return responseDto;
    }

    /**
     * 3. 게임 전체 조회
     */
    // 읽기 전용
    @Transactional(readOnly = true)
    // 3-1. 메서드 이름을 getGameAll로 모든 게임을 조회 하는 메서드임을 명시해줌
    public GameGetAllResponseDto getGameAll() {
        // 3-2. Game을 담을 수 있는 List에 findGameList 라고 변수명 선언해주고 레포지토리에서 isDeleted가 false인 객체들을 찾아와서 그 안에 넣어줌
        List<Game> findGameList = gameRepository.findByIsDeletedFalse();

        // 3-3. countGame 이라는 변수에 findGameList의 사이즈(크기)를 넣어줌 (찾아온 게임의 수)
        Integer countGame = findGameList.size();

        // 3-4. GameGetAllResponseDto의 내부 클래스인 GameGetListResponseDto의 List로 responseDtoList 라는 이름의 ArrayList 변수를 만듬.
        List<GameGetAllResponseDto.GameGetListResponseDto> responseDtoList = new ArrayList<>();

        // 3-5. Game 타입의 game 변수를 선언해주고 findGameList를 반복 하도록 반복문 사용
        for (Game game : findGameList) {
            // 3-5(1). GameGetAllResponseDto의 내부 클래스인 GameGetListResponseDto 타입의 responseGameDto 객체를 생성함
            //         이 때 생성 된 객체는 game의 id, name, company, releaseDate 데이터를 가짐
            GameGetAllResponseDto.GameGetListResponseDto responseGameDto = new GameGetAllResponseDto.GameGetListResponseDto(
                    game.getId(),
                    game.getName(),
                    game.getCompany(),
                    game.getReleaseDate()
            );

            // 3-5(2). responseDtoList에 responseGameDto를 넣어줌 (이를 반복)
            responseDtoList.add(responseGameDto);
        }

        // GameGetAllResponseDto 타입의 responseDto 객체를 새로 생성하고, 그 객체는 countGame과 responseDtoList 데이터를 가짐
        GameGetAllResponseDto responseDto = new GameGetAllResponseDto(countGame, responseDtoList);

        // 3-7. responseDto 반환
        return responseDto;
    }

    /**
     * 4. 게임 정보 수정
     */
    @Transactional
    // 4-1. 메서드 이름을 updateGame으로 선택한 게임의 정보를 수정 하는 메서드임을 명시해줌
    //      gameId 변수, GameUpdateRequestDto 타입의 request를 매개변수로 입력 받겠다.
    public GameUpdateResponseDto updateGame(Long gameId, GameUpdateRequestDto request) {
        // 4-2. 레포지토리에서 findById 메서드에 입력 받은 gameId를 통해서 자원을 식별해오고, 그 데이터를 findGame 변수에 집어 넣어줌
        //      findById로 Game이 Optional이 되어 null일 경우엔 예외를 던져서 해결
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        // 4-3. 자원 식별자로 찾으려는 게임의 isDeleted가 true일 땐(삭제 되었을 땐) 예외 던짐
        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("찾을 수 없는 게임입니다.");
        }

        // 4-4. request로 요청 받은 데이터 name, company, releaseDate와 메서드가 실행되는 현재 시간을 인자값으로 입력 받고 findGame이 updateGame 메서드를 실행한다
        findGame.updateGame(
                request.getName(),
                request.getCompany(),
                request.getReleaseDate(),
                LocalDateTime.now()
        );

        // 4-5. GameUpdateResponseDto 타입의 responseDto 객체를 새로 생성한다
        //      이 때 객체가 가지는 데이터는 updateGame 메서드가 실행 된 findGame의 id, name, company, releaseDate, updateAt 이다.
        GameUpdateResponseDto responseDto = new GameUpdateResponseDto(
                findGame.getId(),
                findGame.getName(),
                findGame.getCompany(),
                findGame.getReleaseDate(),
                findGame.getUpdateAt()
        );

        // 4-6. responseDto 반환
        return responseDto;
    }

    /**
     * 5. 등록 된 게임 삭제
     */
    @Transactional
    // 5-1. 메서드 이름을 deleteGame으로 선택한 게임의 정보를 삭제 하는 메서드임을 명시해줌
    //      gameId 변수를 매개변수로 입력 받겠다.
    public GameDeleteResponseDto deleteGame(Long gameId) {
        // 5-2. 레포지토리에서 findById 메서드에 입력 받은 gameId를 통해서 자원을 식별해오고, 그 데이터를 findGame 변수에 집어 넣어줌
        //      findById로 Game이 Optional이 되어 null일 경우엔 예외를 던져서 해결
        Game findGame = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 게임입니다."));

        // 4-3. 자원 식별자로 찾으려는 게임의 isDeleted가 true일 땐(삭제 되었을 땐) 예외 던짐
        if (findGame.getIsDeleted() == true) {
            throw new IllegalArgumentException("이미 제거 된 게임입니다.");
        }

        // 4-4. findGame이 deleteGame 메서드를 실행한다 (this.isDeleted = true)
        findGame.deleteGame();

        // 4-5. GameDeleteResponseDto 타입의 responseDto 객체를 생성하고 이 때 이 객체는 findGame의 id, name을 가진다
        GameDeleteResponseDto responseDto = new GameDeleteResponseDto(
                findGame.getId(),
                findGame.getName()
        );

        // 4-6. responseDto를 반환한다
        return responseDto;


    }



}
