package com.tms.service;

import com.tms.model.Seat;
import com.tms.repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class SeatService {
    private final SeatRepository seatRepository;

    public List<Seat> getSeatsByHallId(Integer hallId) {
        return seatRepository.findByHallId(hallId);
    }

}
