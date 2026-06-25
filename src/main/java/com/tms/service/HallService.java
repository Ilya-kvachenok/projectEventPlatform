package com.tms.service;

import com.tms.exception.HallNotFoundException;
import com.tms.model.Hall;
import com.tms.model.Seat;
import com.tms.model.dto.hall.HallCreateRequestDTO;
import com.tms.model.dto.hall.HallResponseDTO;
import com.tms.model.dto.hall.HallUpdateRequestDTO;
import com.tms.repository.HallRepository;
import com.tms.repository.SeatRepository;
import com.tms.util.HallMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class HallService {
    private final HallRepository hallsRepository;
    private final HallMapper hallsMapper;
    private final SeatRepository seatRepository;

    public List<HallResponseDTO> getAllHalls () {
        log.debug("IN HallsService:getAllHalls");
        List<Hall> halls = hallsRepository.findAll();
        log.debug("OUT HallsService:getAllHalls");
        return hallsMapper.mapFromListHallsToListHallsResponseDto(halls);
    }

    public HallResponseDTO getHallById(Integer id) throws HallNotFoundException {
        log.debug("IN HallsService:getHallByID");
        Hall hall = hallsRepository.findById(id)
                .orElseThrow(HallNotFoundException::new);
        return hallsMapper.mapFromHallsToHallsResponseDto(hall);
    }

    public HallResponseDTO createHall(HallCreateRequestDTO hallsRequest) {
        log.debug("IN HallsService:createHall");
        Hall halls = hallsRepository.save(hallsMapper.mapFromHallsCreateRequestDtoToHalls(hallsRequest));
        generateSeatsForHall(halls);
        HallResponseDTO hallsResponse = hallsMapper.mapFromHallsToHallsResponseDto(halls);
        log.debug("OUT HallsService:createHall");
        return hallsResponse;
    }

    public HallResponseDTO updateHall(HallUpdateRequestDTO hallsRequest) throws HallNotFoundException {
        log.debug("IN HallsService:updateHall");
        Optional<Hall> hallFromDataBase = hallsRepository.findById(hallsRequest.getId());
        if(hallFromDataBase.isEmpty()) {
            throw new HallNotFoundException();
        }
        Hall result = hallsRepository.save(hallsMapper.mapFromUpdateDtoToHalls(hallsRequest));
        log.info("Hall with id: {} updated", hallsRequest.getId());
        log.debug("OUT HallsService:updateHall");
        return hallsMapper.mapFromHallsToHallsResponseDto(result);
    }

    public void deleteHallById(Integer id) throws HallNotFoundException {
        log.debug("IN HallsService:deleteHallById");
        Optional<Hall> hallFromDatabase = hallsRepository.findById(id);
        if (hallFromDatabase.isEmpty()) {
            throw new HallNotFoundException();
        }
        hallsRepository.deleteById(id);
        log.info("Delete hall with id: {}", id);
        log.debug("OUT HallsService:deleteHallByID");
    }

    public void generateSeatsForHall(Hall hall) {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= hall.getRowsCount(); row++) {
            for (int seatNum = 1; seatNum <= hall.getSeatsPerRows(); seatNum++) {
                Seat seat = new Seat();
                seat.setHall(hall);
                seat.setRowNumber(row);
                seat.setSeatNumber(seatNum);
                seats.add(seat);
            }
        }
        seatRepository.saveAll(seats);
    }

}
