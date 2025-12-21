package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.CreateUserRequest;
import com.example.crudprac01.dto.request.UpdateUserRequest;
import com.example.crudprac01.dto.response.*;
import com.example.crudprac01.entity.User;
import com.example.crudprac01.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public GetAllUserResponse getAllUser() {
        List<User> findUserList = userRepository.findByIsDeletedFalse();

        Integer countUser = findUserList.size();

        List<GetAllUserResponse.GetUserListResponse> userListDto = new ArrayList<>();

        for (User user : findUserList) {
            GetAllUserResponse.GetUserListResponse userDto = new GetAllUserResponse.GetUserListResponse(
                    user.getId(),
                    user.getName(),
                    user.getEmail()
            );

            userListDto.add(userDto);
        }

        GetAllUserResponse responseDto = new GetAllUserResponse(countUser, userListDto);

        return responseDto;
    }

    /**
     * 유저 정보(이름) 일부 수정
     */
    @Transactional
    public UpdateUserResponse updateUser(long userId, UpdateUserRequest request) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다"));

        findUser.update(
                request.getName(),
                LocalDateTime.now()
        );

        UpdateUserResponse responseDto = new UpdateUserResponse(
                findUser.getName(),
                findUser.getEmail(),
                findUser.getModifiedAt()
        );

        return responseDto;
    }

    /**
     * 유저 삭제 (softDelete)
     */
    @Transactional
    public DeleteUserResponseDto deleteUser(Long userId) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저 입니다"));

        if (findUser.getIsDeleted() == true) {
            throw new IllegalArgumentException("이미 삭제 된 유저 입니다.");
        }

        findUser.delete(true);

        DeleteUserResponseDto responseDto = new DeleteUserResponseDto(findUser.getId());

        return responseDto;
    }
}
