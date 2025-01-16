package ru.practicum.shareit.booking;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingState;

/**
 * The type Booking controller.
 */
@Controller
@RequestMapping(path = "/bookings")
@RequiredArgsConstructor
@Slf4j
@Validated
public class BookingController {
    private static final String USER_ID = "X-Sharer-User-Id";
    private final BookingClient bookingClient;
    private static final String BOOKING_ID = "{booking-id}";

    /**
     * Gets bookings.
     *
     * @param userId     the user id
     * @param stateParam the state param
     * @param from       the from
     * @param size       the size
     * @return the bookings
     */
    @GetMapping
    public ResponseEntity<Object> getBookings(@RequestHeader(USER_ID) Long userId,
                                              @RequestParam(name = "state", defaultValue = "all") String stateParam,
                                              @PositiveOrZero @RequestParam(name = "from", defaultValue = "0")
                                              Integer from,
                                              @RequestParam(required = false) Integer size) {
        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        log.info("Get booking with state {}, userId={}, from={}, size={}", stateParam, userId, from, size);
        return bookingClient.getBookings(userId, state, from, size);
    }

    /**
     * Gets bookings owner.
     *
     * @param stateParam the state param
     * @param userId     the user id
     * @param from       the from
     * @param size       the size
     * @return the bookings owner
     */
    @GetMapping("/owner")
    public ResponseEntity<Object> getBookingsOwner(@RequestParam(name = "state", defaultValue = "all")
                                                   String stateParam,
                                                   @RequestHeader(USER_ID) Long userId,
                                                   @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                   @RequestParam(required = false) Integer size) {
        BookingState state = BookingState.from(stateParam)
                .orElseThrow(() -> new IllegalArgumentException("Unknown state: " + stateParam));
        log.info("Получен GET-запрос к эндпоинту: '/bookings/owner' на получение " +
                "списка всех бронирований вещей пользователя с ID={} с параметром STATE={}", userId, state);
        return bookingClient.getBookingsOwner(userId, state, from, size);
    }

    /**
     * Create response entity.
     *
     * @param userId     the user id
     * @param requestDto the request dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(USER_ID) Long userId,
                                         @RequestBody @Valid BookItemRequestDto requestDto) {
        log.info("Creating booking {}, userId={}", requestDto, userId);
        return bookingClient.create(userId, requestDto);
    }

    /**
     * Gets booking.
     *
     * @param userId    the user id
     * @param bookingId the booking id
     * @return the booking
     */
    @GetMapping(BOOKING_ID)
    public ResponseEntity<Object> getBooking(@RequestHeader(USER_ID) Long userId,
                                             @PathVariable("booking-id") Long bookingId) {
        log.info("Get booking {}, userId={}", bookingId, userId);
        return bookingClient.getBooking(userId, bookingId);
    }

    /**
     * Update response entity.
     *
     * @param bookingId the booking id
     * @param userId    the user id
     * @param approved  the approved
     * @return the response entity
     */
    @ResponseBody
    @PatchMapping(BOOKING_ID)
    public ResponseEntity<Object> update(@PathVariable("booking-id") Long bookingId,
                                         @RequestHeader(USER_ID) Long userId, @RequestParam Boolean approved) {
        log.info("Получен PATCH-запрос к эндпоинту: '/bookings' на обновление статуса бронирования с ID={}",
                bookingId);
        return bookingClient.update(bookingId, userId, approved);
    }
}