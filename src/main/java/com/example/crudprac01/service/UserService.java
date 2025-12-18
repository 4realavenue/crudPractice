package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.CreateUserRequest;
import com.example.crudprac01.dto.request.UpdateUserRequest;
import com.example.crudprac01.dto.response.CreateUserResponse;
import com.example.crudprac01.dto.response.GetAllUserResponse;
import com.example.crudprac01.dto.response.GetOneUserResponse;
import com.example.crudprac01.dto.response.UpdateUserResponse;
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

    /**
     * 유저 생성
     */
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = new User(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        User saveUser = userRepository.save(user);

        CreateUserResponse responseDto = new CreateUserResponse(
                saveUser.getId(),
                saveUser.getName(),
                saveUser.getEmail(),
                saveUser.getPassword(),
                saveUser.getCreatedAt()
        );

        return responseDto;
    }

    /**
     * 유저 단건 조회
     */
    @Transactional(readOnly = true)
    public GetOneUserResponse getOneUser(long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다."));

        GetOneUserResponse responseDto = new GetOneUserResponse(
                findUser.getId(),
                findUser.getName(),
                findUser.getEmail()
        );

        return responseDto;
    }

    /**
     * 유저 전체 조회
     */
    @Transactional(readOnly = true)
    public List<GetAllUserResponse> getAllUser() {
        List<User> userList = userRepository.findAll();

        List<GetAllUserResponse> userDtoList = new ArrayList<>();

        for (User user : userList) {
            GetAllUserResponse userDto = new GetAllUserResponse(
                    user.getName(),
                    user.getEmail()
            );
            userDtoList.add(userDto);
        }

        return userDtoList;
    }

    /**
     * 유저 정보(이름) 일부 수정
     */
    @Transactional
    public UpdateUserResponse updateUser(long userId, UpdateUserRequest request) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));

        findUser.update(
                request.getName()
        );

        UpdateUserResponse responseDto = new UpdateUserResponse(
                findUser.getName(),
                findUser.getEmail()
        );

        return responseDto;
    }
}
