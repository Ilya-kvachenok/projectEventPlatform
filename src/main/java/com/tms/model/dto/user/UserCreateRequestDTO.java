package com.tms.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequestDTO {
    @Email
    @NotBlank
    private String email;
}
