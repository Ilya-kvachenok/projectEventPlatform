package com.tms.util;

import com.tms.model.User;
import com.tms.model.dto.user.UserCreateRequestDTO;
import com.tms.model.dto.user.UserResponseDTO;
import com.tms.model.dto.user.UserUpdateRequestDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public List<UserResponseDTO> mapFromListUserToListResponseDto (List<User> users) {
        return users.stream()
                .map(this::mapFromUserToResponseDto)
                .collect(Collectors.toList());
    }

    public User mapFromUserCreateRequestDTOToUser(UserCreateRequestDTO userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        return user;
    }

    public UserResponseDTO mapFromUserToResponseDto (User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }

    public User mapFromUpdateRequestToUser (UserUpdateRequestDto userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        return user;
    }
}
