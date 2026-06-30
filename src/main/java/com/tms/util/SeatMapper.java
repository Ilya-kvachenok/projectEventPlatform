package com.tms.util;

import com.tms.model.Seat;
import com.tms.model.dto.seat.SeatResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SeatMapper {
    public SeatResponseDTO mapFromSeatToSeatResponseDto(Seat seat) {
        SeatResponseDTO seatResponseDTO = new SeatResponseDTO();
        seatResponseDTO.setRowNumber(seat.getRowNumber());
        seatResponseDTO.setSeatNumber(seat.getSeatNumber());
        return seatResponseDTO;
    }

    public List<SeatResponseDTO> mapFromListSeatToListResponseDto(List<Seat> seats) {
        return seats.stream()
                .map(this::mapFromSeatToSeatResponseDto)
                .collect(Collectors.toList());
    }
}
