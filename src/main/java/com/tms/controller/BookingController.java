package com.tms.controller;

import com.tms.exception.BookingAlreadyCancelledException;
import com.tms.exception.BookingNotFoundException;
import com.tms.exception.EventAlreadyPassedException;
import com.tms.exception.EventNotFoundException;
import com.tms.exception.SeatNotFoundException;
import com.tms.exception.SeatNotSelectedException;
import com.tms.exception.UserNotFoundException;
import com.tms.model.dto.booking.BookingCreateRequestDTO;
import com.tms.model.dto.booking.BookingResponseDTO;
import com.tms.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/booking")
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponseDTO> createBooking (@RequestBody BookingCreateRequestDTO requestDTO) throws UserNotFoundException, EventNotFoundException, SeatNotSelectedException, SeatNotFoundException, EventAlreadyPassedException {
        BookingResponseDTO responseDTO = bookingService.createBooking(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDTO> getBookingById (@PathVariable Integer id) throws BookingNotFoundException {
        BookingResponseDTO responseDTO = bookingService.getBookingById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<BookingResponseDTO>> getBookingByUserId (@PathVariable Integer id) throws UserNotFoundException {
        List<BookingResponseDTO> responseDTO = bookingService.getAllBookingUserById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BookingResponseDTO> cancelBooking(@PathVariable Integer id) throws BookingNotFoundException, BookingAlreadyCancelledException, EventAlreadyPassedException {
        BookingResponseDTO responseDTO = bookingService.cancelBooking(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PatchMapping("/update-status")
    public ResponseEntity<HttpStatus> updateBookingStatuses() {
        bookingService.updateBookingStatuses();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
