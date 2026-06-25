package com.tms.model.dto.hall;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class HallCreateRequestDTO {
    @NotBlank
    private String name;

    @Min(value = 1)
    @Max(1000)
    private Integer rows_count;

    @Min(value = 1)
    @Max(1000)
    private Integer seatsPerRows;
}
