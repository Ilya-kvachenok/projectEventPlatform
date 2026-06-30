package com.tms.service;

import com.tms.exception.BookingAlreadyCancelledException;
import com.tms.exception.BookingNotFoundException;
import com.tms.exception.EventAlreadyPassedException;
import com.tms.exception.EventNotFoundException;
import com.tms.exception.SeatNotFoundException;
import com.tms.exception.SeatNotSelectedException;
import com.tms.exception.UserNotFoundException;
import com.tms.model.Booking;
import com.tms.model.BookingSeat;
import com.tms.model.BookingStatus;
import com.tms.model.Event;
import com.tms.model.Seat;
import com.tms.model.User;
import com.tms.model.dto.booking.BookingCreateRequestDTO;
import com.tms.model.dto.booking.BookingResponseDTO;
import com.tms.repository.BookingRepository;
import com.tms.repository.EventRepository;
import com.tms.repository.SeatRepository;
import com.tms.repository.UserRepository;
import com.tms.util.BookingMapper;
import com.tms.util.SeatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class BookingService {
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final BookingMapper bookingMapper;
    private final SeatMapper saatMapper;

    @Transactional
    public BookingResponseDTO createBooking(BookingCreateRequestDTO requestDTO) throws EventNotFoundException, UserNotFoundException, SeatNotSelectedException, SeatNotFoundException, EventAlreadyPassedException {
        log.debug("IN BookingService:createBooking");
        Event event = eventRepository.findById(requestDTO.getEventId())
                .orElseThrow(EventNotFoundException::new);
        User user = userRepository.findById(requestDTO.getUserId())
                .orElseThrow(UserNotFoundException::new);
        if (requestDTO.getSeatIds() == null || requestDTO.getSeatIds().isEmpty()) {
            throw new SeatNotSelectedException();
        }
        List<Seat> seats = seatRepository.findAllById(requestDTO.getSeatIds());
        if (seats.size() != requestDTO.getSeatIds().size()) {
            throw new SeatNotFoundException();
        }
        Booking booking = new Booking();
        if(booking.getEvent().getEventDateTime().isBefore(LocalDateTime.now())) {
            throw new EventAlreadyPassedException();
        }
        booking.setEvent(event);
        booking.setUser(user);
        booking.setStatus(BookingStatus.CREATED);
        booking.setBookingDate(LocalDateTime.now());
        booking.setCreated(LocalDateTime.now());
        booking.setUpdated(LocalDateTime.now());
        BigDecimal totalPrice = event.getPrice()
                .multiply(BigDecimal.valueOf(seats.size()));
        booking.setTotalPrice(totalPrice);
        for (Seat seat:seats) {
            BookingSeat bookingSeat = new BookingSeat();
            bookingSeat.setBooking(booking);
            bookingSeat.setSeat(seat);
            bookingSeat.setPrice(event.getPrice());
            booking.getBookingSeats().add(bookingSeat);
        }
        booking = bookingRepository.save(booking);
        log.debug("OUT BookingService:createBooking");
        return bookingMapper.mapFromBookingToResponseDto(booking);
    }

    public BookingResponseDTO getBookingById(Integer id) throws BookingNotFoundException {
        log.debug("IN BookingService:getBookingById");
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(BookingNotFoundException::new);
        log.debug("OUT BookingService:getBookingById");
        return bookingMapper.mapFromBookingToResponseDto(booking);
    }

    public List<BookingResponseDTO> getAllBookingUserById(Integer id) throws UserNotFoundException {
        log.debug("IN BookingService:getAllBookingUserById");
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
        List<Booking> bookings = bookingRepository.findAllByUserId(user.getId());
        log.debug("OUT BookingService:getAllBookingUserById");
        return bookingMapper.mapFromListBookingsToListBookingsResponseDto(bookings);
    }

    @Transactional
    public BookingResponseDTO cancelBooking(Integer id) throws BookingNotFoundException, BookingAlreadyCancelledException, EventAlreadyPassedException {
        log.debug("IN BookingService:cancelBooking");
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(BookingNotFoundException::new);
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            throw new BookingAlreadyCancelledException();
        }
        if(booking.getEvent().getEventDateTime().isBefore(LocalDateTime.now())) {
            throw new EventAlreadyPassedException();
        }
        booking.setStatus(BookingStatus.CANCELLED);
        booking.setUpdated(LocalDateTime.now());
        booking = bookingRepository.save(booking);
        log.debug("OUT BookingService:cancelBooking");
        return bookingMapper.mapFromBookingToResponseDto(booking);
    }

    @Transactional
    public void updateBookingStatuses() {
        List<Booking> bookings = bookingRepository.findAll();
        for (Booking booking:bookings) {
            if (booking.getStatus() == BookingStatus.CREATED && booking.getEvent().getEventDateTime().isBefore(LocalDateTime.now())) {
                booking.setStatus(BookingStatus.VISITED);
            }
        }
    }
}