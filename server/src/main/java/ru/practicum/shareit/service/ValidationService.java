package ru.practicum.shareit.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.booking.Booking;
import ru.practicum.shareit.booking.BookingService;
import ru.practicum.shareit.booking.dto.BookingShortDto;
import ru.practicum.shareit.item.CommentDto;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserService;

import java.util.List;

/**
 * The type Validation service.
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationService {
    UserService userService;
    ItemService itemService;
    BookingService bookingService;

    /**
     * Instantiates a new Validation service.
     *
     * @param userService    the user service
     * @param itemService    the item service
     * @param bookingService the booking service
     */
    @Autowired
    public ValidationService(UserService userService, ItemService itemService, BookingService bookingService) {
        this.userService = userService;
        this.itemService = itemService;
        this.bookingService = bookingService;
    }

    /**
     * Is exist user boolean.
     *
     * @param userId the user id
     * @return the boolean
     */
    public boolean isExistUser(Long userId) {
        boolean exist = false;
        if (userService.getUser(userId) != null) {
            exist = true;
        }
        return exist;
    }

    /**
     * Is available item boolean.
     *
     * @param itemId the item id
     * @return the boolean
     */
    public boolean isAvailableItem(Long itemId) {
        return itemService.findItemById(itemId).getAvailable();
    }

    /**
     * Is item owner boolean.
     *
     * @param itemId the item id
     * @param userId the user id
     * @return the boolean
     */
    public boolean isItemOwner(Long itemId, Long userId) {

        return itemService.getItemsByOwner(userId).stream()
                .anyMatch(i -> i.getId().equals(itemId));
    }

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    public User findUserById(Long userId) {
        return userService.findUserById(userId);
    }


    /**
     * Gets last booking.
     *
     * @param itemId the item id
     * @return the last booking
     */
    public BookingShortDto getLastBooking(Long itemId) {
        return bookingService.getLastBooking(itemId);
    }

    /**
     * Gets next booking.
     *
     * @param itemId the item id
     * @return the next booking
     */
    public BookingShortDto getNextBooking(Long itemId) {
        return bookingService.getNextBooking(itemId);
    }

    /**
     * Gets booking with user booked item.
     *
     * @param itemId the item id
     * @param userId the user id
     * @return the booking with user booked item
     */
    public Booking getBookingWithUserBookedItem(Long itemId, Long userId) {
        return bookingService.getBookingWithUserBookedItem(itemId, userId);
    }

    /**
     * Gets comments by item id.
     *
     * @param itemId the item id
     * @return the comments by item id
     */
    public List<CommentDto> getCommentsByItemId(Long itemId) {
        return itemService.getCommentsByItemId(itemId);
    }
}
