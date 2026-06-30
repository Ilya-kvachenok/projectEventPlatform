package com.tms.model.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class BookingCreateRequestDTO {
    private Integer userId;
    private Integer eventId;
    private List<Integer> seatIds;
}
