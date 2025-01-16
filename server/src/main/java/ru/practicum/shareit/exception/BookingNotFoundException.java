package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Booking not found exception.
 */
@Slf4j
public class BookingNotFoundException extends IllegalArgumentException {
    /**
     * Instantiates a new Booking not found exception.
     *
     * @param message the message
     */
    public BookingNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}