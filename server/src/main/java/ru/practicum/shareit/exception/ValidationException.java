package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Validation exception.
 */
@Slf4j
public class ValidationException extends RuntimeException {
    /**
     * Instantiates a new Validation exception.
     *
     * @param message the message
     */
    public ValidationException(String message) {
        super(message);
        log.error(message);
    }
}