package com.tms.model.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventUpdateRequestDTO {
    @Positive
    private Integer id;
    @NotBlank
    private String title;
    @NotBlank
    private String category;
    @NotBlank
    private String description;
    @NotBlank
    private String location;
    @NotBlank
    private LocalDateTime eventDateTime;
    @NotBlank
    private BigDecimal price;
}
