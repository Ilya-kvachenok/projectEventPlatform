package com.tms.model.dto.event;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class EventResponseDTO {
    private Integer id;
    private String title;
    private String category;
    private String description;
    private String location;
    private LocalDateTime eventDateTime;
    private BigDecimal price;
}
