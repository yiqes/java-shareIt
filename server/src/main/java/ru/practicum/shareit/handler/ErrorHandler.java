package ru.practicum.shareit.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.exception.*;

/**
 * The type Error handler.
 */
@RestControllerAdvice
public class ErrorHandler {

    /**
     * Handle user not found exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(final UserNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Handle item not found exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleItemNotFoundException(final ItemNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Handle booking not found exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleBookingNotFoundException(final BookingNotFoundException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Handle validation exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(final ValidationException e) {
        return new ErrorResponse(e.getMessage());
    }

    /**
     * Handle method argument not validation exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidationException(final MethodArgumentNotValidException e) {
        return new ErrorResponse("Аргумент не прошел валидацию!");
    }

    /**
     * Handle user already exist exception error response.
     *
     * @param e the e
     * @return the error response
     */
    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleUserAlreadyExistException(final UserAlreadyExistsException e) {
        return new ErrorResponse(e.getMessage());
    }
}