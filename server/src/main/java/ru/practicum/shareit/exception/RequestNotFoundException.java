package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Request not found exception.
 */
@Slf4j
public class RequestNotFoundException extends IllegalArgumentException {
    /**
     * Instantiates a new Request not found exception.
     *
     * @param message the message
     */
    public RequestNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}
