package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.UserCreateRequestDto;
import com.example.crudprac01.dto.request.UserUpdateRequestDto;
import com.example.crudprac01.dto.response.UserCreateResponseDto;
import com.example.crudprac01.dto.response.UserGetAllResponseDto;
import com.example.crudprac01.dto.response.UserGetDetailResponseDto;
import com.example.crudprac01.dto.response.UserUpdateResponseDto;
import com.example.crudprac01.entity.User;
import com.example.crudprac01.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserCreateResponseDto createUser(UserCreateRequestDto requestDto) {
        User user = new User(requestDto.getName());

        User savedUser = userRepository.save(user);

        UserCreateResponseDto responseDto = new UserCreateResponseDto(savedUser.getId(), savedUser.getName());

        return responseDto;

    }

    @Transactional
    public UserGetDetailResponseDto getUserDetail(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("아잇 !"));

        UserGetDetailResponseDto responseDto = new UserGetDetailResponseDto(
                findUser.getId(),
                findUser.getName()
        );

        return responseDto;
    }

    @Transactional
    public UserGetAllResponseDto getUserAll() {
        List<User> findUserList = userRepository.findAll();

        Integer count = findUserList.size();

        List<UserGetAllResponseDto.UserGetResponse> responseList = new ArrayList<>();

        for (User user : findUserList) {
            UserGetAllResponseDto.UserGetResponse response = new UserGetAllResponseDto.UserGetResponse(
                    user.getId(),
                    user.getName()
            );

            responseList.add(response);
        }

        UserGetAllResponseDto responseDto = new UserGetAllResponseDto(count, responseList);

        return responseDto;
    }

    @Transactional
    public UserUpdateResponseDto updateUser(long userId, UserUpdateRequestDto requestDto) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("아잇 !"));

        findUser.updateUser(requestDto.getName());

        UserUpdateResponseDto responseDto = new UserUpdateResponseDto(
                findUser.getId(),
                findUser.getName()
        );

        return responseDto;
    }

    @Transactional
    public void deleteUser(long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("아잇 !"));

        userRepository.delete(findUser);
    }
}
