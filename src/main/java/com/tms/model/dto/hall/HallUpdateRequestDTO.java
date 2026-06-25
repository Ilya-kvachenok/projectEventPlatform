package com.tms.model.dto.hall;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class HallUpdateRequestDTO {
    @Positive
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    @Positive
    private Integer rows_count;
    @NotBlank
    @Positive
    private Integer seatsPerRows;
}
