package com.tms.controller;

import com.tms.exception.HallNotFoundException;
import com.tms.model.dto.hall.HallCreateRequestDTO;
import com.tms.model.dto.hall.HallResponseDTO;
import com.tms.model.dto.hall.HallUpdateRequestDTO;
import com.tms.service.HallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/halls")
public class HallController {
    private final HallService hallsService;

    @GetMapping
    public ResponseEntity<List<HallResponseDTO>> getAllHalls() {
        return new ResponseEntity<>(hallsService.getAllHalls(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallResponseDTO> getHallById(@PathVariable Integer id) throws HallNotFoundException {
        HallResponseDTO responseDTO = hallsService.getHallById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HallResponseDTO> createHall(@RequestBody @Valid HallCreateRequestDTO hallsRequest) {
        HallResponseDTO hallsResponseDTO = hallsService.createHall(hallsRequest);
        return new ResponseEntity<>(hallsResponseDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<HallResponseDTO> updateHall(@RequestBody @Valid HallUpdateRequestDTO hallRequest) throws HallNotFoundException {
        HallResponseDTO hallsResponseDTO = hallsService.updateHall(hallRequest);
        return new ResponseEntity<>(hallsResponseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteHallById(@PathVariable Integer id) throws HallNotFoundException {
        hallsService.deleteHallById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
