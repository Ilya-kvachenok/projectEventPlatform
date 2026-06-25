package com.tms.service;

import com.tms.exception.EventNotFoundException;
import com.tms.model.Event;
import com.tms.model.dto.event.EventCreateRequestDTO;
import com.tms.model.dto.event.EventResponseDTO;
import com.tms.model.dto.event.EventUpdateRequestDTO;
import com.tms.repository.EventRepository;
import com.tms.util.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventService {
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public List<EventResponseDTO> getAllEvents() {
        log.debug("IN EventService:getAllEvents");
        List<Event> events = eventRepository.findAll();
        log.debug("OUT EventService:getAllEvents");
        return eventMapper.mapFromListEventToListResponseDto(events);
    }

    public EventResponseDTO getEventById (Integer id) throws EventNotFoundException {
        log.debug("IN EventService:getEventById");
        Event event = eventRepository.findById(id)
                .orElseThrow(EventNotFoundException::new);
        return eventMapper.mapFromEventToResponseDto(event);
    }

    public EventResponseDTO createEvent(EventCreateRequestDTO eventRequest) {
        log.debug("IN EventService:createEvent");
        Event event = eventRepository.save(eventMapper.mapFromCreateRequestDtoToEvents(eventRequest));
        log.debug("OUT EventService:createEvent");
        return eventMapper.mapFromEventToResponseDto(event);
    }

    public EventResponseDTO updateEvent(EventUpdateRequestDTO eventRequest) throws EventNotFoundException {
        log.debug("IN EventService:updateEvent");
        Optional<Event> eventFromDatabase = eventRepository.findById(eventRequest.getId());
        if (eventFromDatabase.isEmpty()) {
            throw new EventNotFoundException();
        }
        Event result = eventRepository.save(eventMapper.mapFromUpdateRequestDtoToEvents(eventRequest));
        log.info("Update event with id: {}", eventRequest.getId());
        log.debug("OUT EventService:updateEvent");
        return eventMapper.mapFromEventToResponseDto(result);
    }

    public void deleteEventById (Integer id) throws EventNotFoundException {
        log.debug("IN EventService:deleteEventById");
        Optional<Event> eventFromDatabase = eventRepository.findById(id);
        if (eventFromDatabase.isEmpty()) {
            throw new EventNotFoundException();
        }
        eventRepository.deleteById(id);
        log.info("Delete event with id: {}", id);
        log.debug("OUT EventService:deleteEventById");
    }

}
