package com.tms.util;

import com.tms.model.Hall;
import com.tms.model.dto.hall.HallCreateRequestDTO;
import com.tms.model.dto.hall.HallResponseDTO;
import com.tms.model.dto.hall.HallUpdateRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HallMapper {
    public Hall mapFromHallsCreateRequestDtoToHalls (HallCreateRequestDTO hallsRequest) {
        Hall halls = new Hall();
        halls.setName(hallsRequest.getName());
        halls.setRowsCount(hallsRequest.getRows_count());
        halls.setSeatsPerRows(hallsRequest.getSeatsPerRows());
        return halls;
    }

    public HallResponseDTO mapFromHallsToHallsResponseDto (Hall halls) {
        HallResponseDTO hallsResponse = new HallResponseDTO();
        hallsResponse.setId(halls.getId());
        hallsResponse.setName(halls.getName());
        hallsResponse.setRows_count(halls.getRowsCount());
        hallsResponse.setSeatsPerRows(halls.getSeatsPerRows());
        return hallsResponse;
    }

    public List<HallResponseDTO> mapFromListHallsToListHallsResponseDto(List<Hall> halls) {
        return halls.stream()
                .map(this::mapFromHallsToHallsResponseDto)
                .collect(Collectors.toList());
    }

    public Hall mapFromUpdateDtoToHalls (HallUpdateRequestDTO hallsRequest) {
        Hall halls = new Hall();
        halls.setName(hallsRequest.getName());
        halls.setRowsCount(hallsRequest.getRows_count());
        halls.setSeatsPerRows(hallsRequest.getSeatsPerRows());
        return halls;
    }
}
