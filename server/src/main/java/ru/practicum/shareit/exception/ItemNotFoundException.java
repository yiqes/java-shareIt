package ru.practicum.shareit.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * The type Item not found exception.
 */
@Slf4j
public class ItemNotFoundException extends IllegalArgumentException {
    /**
     * Instantiates a new Item not found exception.
     *
     * @param message the message
     */
    public ItemNotFoundException(String message) {
        super(message);
        log.error(message);
    }
}