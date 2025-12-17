package com.example.crudprac01.service;

import com.example.crudprac01.dto.request.CreateUserRequest;
import com.example.crudprac01.dto.response.CreateUserResponse;
import com.example.crudprac01.entity.User;
import com.example.crudprac01.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
