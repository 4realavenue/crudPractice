package com.example.crudprac01.service;

import com.example.crudprac01.controller.SongController;
import com.example.crudprac01.dto.request.CreateSongRequestDto;
import com.example.crudprac01.dto.response.CreateSongResponseDto;
import com.example.crudprac01.entity.Song;
import com.example.crudprac01.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SongService {

    private final SongRepository songRepository;
    private static final Logger log = LoggerFactory.getLogger(SongController.class);

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    /**
     *  노래 생성
     */
    @Transactional
    public CreateSongResponseDto createSong(CreateSongRequestDto request) {
        // 3. service 레이어 -> controller 확인
        log.info("서비스 - 테스트입니다.");

        // 4. 데이터 준비
        String title = request.getTitle();
        String Singer = request.getSinger();

        // 5. 객체 생성
        Song newSong = new Song(
                title,
                Singer
        );

        // 6. repository로 생성한 객체 DB에 저장
        Song saveSong = songRepository.save(newSong);

        // 7. 저장한 데이터 dto에 저장
        CreateSongResponseDto responseDto = new CreateSongResponseDto(
                saveSong.getId(),
                saveSong.getTitle(),
                saveSong.getSinger()
        );

        // 8. service 레이어에서 수행한 메서드 반환
        return responseDto;
    }
}
