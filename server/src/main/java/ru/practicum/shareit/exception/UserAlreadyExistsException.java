package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type User already exists exception.
 */
@Slf4j
public class UserAlreadyExistsException extends IllegalArgumentException {

    /**
     * Instantiates a new User already exists exception.
     *
     * @param message the message
     */
    public UserAlreadyExistsException(String message) {
        super(message);
        log.error(message);
    }
}
