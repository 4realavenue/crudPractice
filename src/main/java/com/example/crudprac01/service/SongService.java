package com.example.crudprac01.service;

import com.example.crudprac01.controller.SongController;
import com.example.crudprac01.dto.request.CreateSongRequestDto;
import com.example.crudprac01.dto.response.CreateSongResponseDto;
import com.example.crudprac01.dto.response.GetSongAllResponseDto;
import com.example.crudprac01.dto.response.GetSongDetailResponseDto;
import com.example.crudprac01.entity.Song;
import com.example.crudprac01.repository.SongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * 단건 조회
     * @param songId
     * @return
     */
    @Transactional
    public GetSongDetailResponseDto getSongDetail(Long songId) {
        // 2. 서비스 확인
        log.info("서비스 - 단건 조회");

        // 3. id로 원하는 조회 하고 싶은 노래 찾아와서 findSong 변수에 넣어주기
        // + findById로 optional 타입이 되었으니 .orElseThrow로 null의 경우 예외 시키기
        Song findSong = songRepository.findById(songId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 노래입니다."));

        GetSongDetailResponseDto responseDto = new GetSongDetailResponseDto(
                findSong.getId(),
                findSong.getTitle(),
                findSong.getSinger()
        );

        return responseDto;
    }

    @Transactional
    public List<GetSongAllResponseDto> getSongAll() {
        // 2. 서비스 확인
        log.info("서비스 - 전체 조회");

        // 3. repository를 통해 DB에 저장 된 모든 Song 객체 songList에 담아주기
        List<Song> songList = songRepository.findAll();

        // 4. 전체 조회 전용 ResponseDto 만들어주고 List로 감싸서 songDtos 라는 변수명 달아줌
        List<GetSongAllResponseDto> songDtos = new ArrayList<>();

        // 5. for문 사용해서 songList의 Song 객체 꺼내서 song 변수에 담아줌
        for (Song song : songList) {
            // + 전체 조회 전용 ResponseDto에 song 변수에 담긴 데이터 다시 담아줌 (공개 돼선 안될 데이터 보호 + 데이터에 직접 접근 x)
            GetSongAllResponseDto songDto = new GetSongAllResponseDto(song.getId(), song.getTitle(), song.getSinger());
            // songDtos List에 songDto 담아줌
            songDtos.add(songDto);
        }
        // 6. songDto들이 반복 돼서 담긴 songDtos 반환
        return songDtos;
    }
}
