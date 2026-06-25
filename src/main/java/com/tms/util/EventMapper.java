package com.tms.util;

import com.tms.model.Event;
import com.tms.model.dto.event.EventCreateRequestDTO;
import com.tms.model.dto.event.EventResponseDTO;
import com.tms.model.dto.event.EventUpdateRequestDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EventMapper {
    public List<EventResponseDTO> mapFromListEventToListResponseDto(List<Event> events) {
        return events.stream()
                .map(this::mapFromEventToResponseDto)
                .collect(Collectors.toList());
    }

    public Event mapFromCreateRequestDtoToEvents (EventCreateRequestDTO eventRequest) {
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setCategory(eventRequest.getCategory());
        event.setDescription(eventRequest.getDescription());
        event.setLocation(eventRequest.getLocation());
        event.setEventDateTime(eventRequest.getEventDateTime());
        event.setPrice(eventRequest.getPrice());
        return event;
    }

    public EventResponseDTO mapFromEventToResponseDto (Event event) {
        EventResponseDTO responseDTO = new EventResponseDTO();
        responseDTO.setId(event.getId());
        responseDTO.setTitle(event.getTitle());
        responseDTO.setCategory(event.getCategory());
        responseDTO.setDescription(event.getDescription());
        responseDTO.setLocation(event.getLocation());
        responseDTO.setEventDateTime(event.getEventDateTime());
        responseDTO.setPrice(event.getPrice());
        return responseDTO;
    }

    public Event mapFromUpdateRequestDtoToEvents (EventUpdateRequestDTO eventRequest) {
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setCategory(eventRequest.getCategory());
        event.setDescription(eventRequest.getDescription());
        event.setLocation(eventRequest.getLocation());
        event.setEventDateTime(eventRequest.getEventDateTime());
        event.setPrice(eventRequest.getPrice());
        return event;
    }

}
