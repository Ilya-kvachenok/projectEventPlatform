package com.tms.util;

import com.tms.model.Booking;
import com.tms.model.dto.booking.BookingResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class BookingMapper {
    private final SeatMapper seatMapper;


    public BookingResponseDTO mapFromBookingToResponseDto(Booking booking) {
        BookingResponseDTO responseDTO = new BookingResponseDTO();
        responseDTO.setId(booking.getId());
        responseDTO.setStatus(booking.getStatus());
        responseDTO.setTotalPrice(booking.getTotalPrice());
        responseDTO.setBookingDate(booking.getBookingDate());
        responseDTO.setEventTitle(booking.getEvent().getTitle());
        responseDTO.setEventDateTime(booking.getEvent().getEventDateTime());
        responseDTO.setUserEmail(booking.getUser().getEmail());
        responseDTO.setSeats(
                booking.getBookingSeats()
                        .stream()
                        .map(bs -> seatMapper.mapFromSeatToSeatResponseDto(bs.getSeat()))
                        .toList()
        );
        return responseDTO;
    }

    public List<BookingResponseDTO> mapFromListBookingsToListBookingsResponseDto(List<Booking> bookings) {
        return bookings.stream()
                .map(this::mapFromBookingToResponseDto)
                .collect(Collectors.toList());
    }
}
