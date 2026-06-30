package com.tms.model.dto.booking;

import com.tms.model.BookingStatus;
import com.tms.model.dto.seat.SeatResponseDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BookingResponseDTO {
    private Integer id;
    private BookingStatus status;
    private BigDecimal totalPrice;
    private LocalDateTime bookingDate;
    private String eventTitle;
    private LocalDateTime eventDateTime;
    private String userEmail;
    private List<SeatResponseDTO> seats;
}
