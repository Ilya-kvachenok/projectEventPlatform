package com.tms.controller;

import com.tms.model.Seat;
import com.tms.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/halls/seats")
public class SeatController {
    private final SeatService seatService;

    @GetMapping("/{hallId}")
    public ResponseEntity<List<Seat>> getSeatsByHallId(@PathVariable Integer hallId) {
        List<Seat> seats = seatService.getSeatsByHallId(hallId);
        return new ResponseEntity<>(seats, HttpStatus.OK);
    }
}
