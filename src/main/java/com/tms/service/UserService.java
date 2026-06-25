package com.tms.service;

import com.tms.exception.UserNotFoundException;
import com.tms.model.User;
import com.tms.model.dto.user.UserCreateRequestDTO;
import com.tms.model.dto.user.UserResponseDTO;
import com.tms.model.dto.user.UserUpdateRequestDto;
import com.tms.repository.UserRepository;
import com.tms.util.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> getAllUsers() {
        log.debug("IN UserService:getAllUsers");
        List<User> users = userRepository.findAll();
        log.debug("OUT UserService:getAllUsers");
        return userMapper.mapFromListUserToListResponseDto(users);
    }

    public UserResponseDTO getUserById(Integer id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        return userMapper.mapFromUserToResponseDto(user);
    }

    public UserResponseDTO createUser(UserCreateRequestDTO userRequest) {
        log.debug("IN UserService:createUser");
        User user = userRepository.save(userMapper.mapFromUserCreateRequestDTOToUser(userRequest));
        log.debug("OUT UserService:createUser");
        return userMapper.mapFromUserToResponseDto(user);
    }

    public UserResponseDTO updateUser(UserUpdateRequestDto userRequest) throws UserNotFoundException {
        log.debug("IN UserService:updateUser");
        Optional<User> userFromDatabase = userRepository.findById(userRequest.getId());
        if (userFromDatabase.isEmpty()) {
            throw new UserNotFoundException();
        }
        User result = userRepository.save(userMapper.mapFromUpdateRequestToUser(userRequest));
        log.info("Update user with id: {}", userRequest.getId());
        log.debug("OUT UserService:updateUser");
        return userMapper.mapFromUserToResponseDto(result);
    }

    public void deleteUserById(Integer id) throws UserNotFoundException {
        Optional<User> userFromDatabase = userRepository.findById(id);
        if (userFromDatabase.isEmpty()) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
        log.info("Delete user with id: {}", id);
        log.debug("OUT UserService:deleteUserByID");
    }


}
