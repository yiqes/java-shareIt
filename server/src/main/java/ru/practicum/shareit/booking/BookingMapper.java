package ru.practicum.shareit.booking;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInputDto;
import ru.practicum.shareit.booking.dto.BookingShortDto;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.ItemServiceImpl;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserServiceImpl;

/**
 * The type Booking mapper.
 */
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingMapper {
    UserServiceImpl userService;
    ItemServiceImpl itemService;
    UserMapper userMapper;
    ItemMapper itemMapper;

    /**
     * Instantiates a new Booking mapper.
     *
     * @param userService the user service
     * @param itemService the item service
     * @param userMapper  the user mapper
     * @param itemMapper  the item mapper
     */
    @Autowired
    public BookingMapper(UserServiceImpl userService, ItemServiceImpl itemService,
                         UserMapper userMapper, ItemMapper itemMapper) {
        this.userService = userService;
        this.itemService = itemService;
        this.userMapper = userMapper;
        this.itemMapper = itemMapper;
    }

    /**
     * To booking dto booking dto.
     *
     * @param booking the booking
     * @return the booking dto
     */
    public BookingDto toBookingDto(Booking booking) {
        if (booking != null) {
            return new BookingDto(
                    booking.getId(),
                    booking.getStart(),
                    booking.getEnd(),
                    itemMapper.toItemDto(booking.getItem()),
                    userMapper.toUserDto(booking.getBooker()),
                    booking.getStatus()
            );
        } else {
            return null;
        }
    }

    /**
     * To booking short dto booking short dto.
     *
     * @param booking the booking
     * @return the booking short dto
     */
    public BookingShortDto toBookingShortDto(Booking booking) {
        if (booking != null) {
            return new BookingShortDto(
                    booking.getId(),
                    booking.getBooker().getId(),
                    booking.getStart(),
                    booking.getEnd()
            );
        } else {
            return null;
        }
    }

    /**
     * To booking booking.
     *
     * @param bookingInputDto the booking input dto
     * @param bookerId        the booker id
     * @return the booking
     */
    public Booking toBooking(BookingInputDto bookingInputDto, Long bookerId) {
        return new Booking(
                null,
                bookingInputDto.getStart(),
                bookingInputDto.getEnd(),
                itemService.findItemById(bookingInputDto.getItemId()),
                userService.findUserById(bookerId),
                BookingStatus.WAITING
        );
    }
}