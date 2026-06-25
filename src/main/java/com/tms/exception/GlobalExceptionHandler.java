package com.tms.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<HttpStatus> usernameNotFoundException(UserNotFoundException ex) {
        log.error("UsernameNotFoundException: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpStatus> hallNotFoundException(HallNotFoundException ex) {
        log.error("HallNotFoundException: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpStatus> eventNotFoundException(EventNotFoundException ex) {
        log.error("EventNotFoundException: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<HttpStatus> SeatNotSelectedException(SeatNotSelectedException ex) {
        log.error("SeatNotSelectedException: " + ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
