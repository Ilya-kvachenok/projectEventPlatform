package com.tms.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserUpdateRequestDto {
    @Positive
    private Integer id;
    @Email
    @NotBlank
    private String email;
}
