package com.tms.model.dto.hall;

import lombok.Data;

@Data
public class HallResponseDTO {
    private Integer id;
    private String name;
    private Integer rows_count;
    private Integer seatsPerRows;
}
