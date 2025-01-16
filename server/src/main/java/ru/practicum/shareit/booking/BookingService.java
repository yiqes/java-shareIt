package ru.practicum.shareit.booking;

import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInputDto;
import ru.practicum.shareit.booking.dto.BookingShortDto;

import java.util.List;

/**
 * The interface Booking service.
 */
public interface BookingService {
    /**
     * Create booking dto.
     *
     * @param bookingDto the booking dto
     * @param bookerId   the booker id
     * @return the booking dto
     */
    BookingDto create(BookingInputDto bookingDto, Long bookerId);

    /**
     * Update booking dto.
     *
     * @param bookingId the booking id
     * @param userId    the user id
     * @param approved  the approved
     * @return the booking dto
     */
    BookingDto update(Long bookingId, Long userId, Boolean approved);

    /**
     * Gets booking by id.
     *
     * @param bookingId the booking id
     * @param userId    the user id
     * @return the booking by id
     */
    BookingDto getBookingById(Long bookingId, Long userId);

    /**
     * Gets bookings.
     *
     * @param state  the state
     * @param userId the user id
     * @return the bookings
     */
    List<BookingDto> getBookings(String state, Long userId);

    /**
     * Gets bookings owner.
     *
     * @param state  the state
     * @param userId the user id
     * @return the bookings owner
     */
    List<BookingDto> getBookingsOwner(String state, Long userId);

    /**
     * Gets last booking.
     *
     * @param itemId the item id
     * @return the last booking
     */
    BookingShortDto getLastBooking(Long itemId);

    /**
     * Gets next booking.
     *
     * @param itemId the item id
     * @return the next booking
     */
    BookingShortDto getNextBooking(Long itemId);

    /**
     * Gets booking with user booked item.
     *
     * @param itemId the item id
     * @param userId the user id
     * @return the booking with user booked item
     */
    Booking getBookingWithUserBookedItem(Long itemId, Long userId);

}