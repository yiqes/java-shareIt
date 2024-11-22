package ru.practicum.shareit.booking;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingInputDto;
import ru.practicum.shareit.booking.dto.BookingShortDto;
import ru.practicum.shareit.item.Item;
import ru.practicum.shareit.item.ItemMapper;
import ru.practicum.shareit.item.ItemServiceImpl;
import ru.practicum.shareit.service.ValidationService;
import ru.practicum.shareit.user.User;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserServiceImpl;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Rollback
@DataJpaTest
@ActiveProfiles("test")
@Import({BookingMapper.class, BookingServiceImpl.class, UserServiceImpl.class, ValidationService.class,
        UserMapper.class, ItemServiceImpl.class, ItemMapper.class})
public class BookingServiceImplIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookingServiceImpl bookingService;

    private User owner;
    private User booker;

    private Item item;

    @BeforeEach
    void setUp() {
        // Создание пользователей
        owner = new User();
        owner.setEmail("owner@example.com");
        owner.setName("Owner");
        owner.setRegistrationDate(Instant.now());
        entityManager.persist(owner);

        booker = new User();
        booker.setEmail("booker@example.com");
        booker.setName("Booker");
        booker.setRegistrationDate(Instant.now());
        entityManager.persist(booker);

        item = new Item();
        item.setName("Item");
        item.setDescription("Description");
        item.setAvailable(true);
        item.setOwner(owner);
        entityManager.persist(item);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }

    @Test
    void testCreateBooking() {
        BookingInputDto bookingInputDto = new BookingInputDto(item.getId(), LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        BookingDto bookingDto = bookingService.create(bookingInputDto, booker.getId());

        assertNotNull(bookingDto.getId());
        assertEquals(bookingInputDto.getStart(), bookingDto.getStart());
        assertEquals(bookingInputDto.getEnd(), bookingDto.getEnd());
    }

    @Test
    void testUpdateBooking() {
        Booking booking = new Booking();
        booking.setStart(LocalDateTime.now().plusHours(1));
        booking.setEnd(LocalDateTime.now().plusHours(2));
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStatus(BookingStatus.WAITING);
        entityManager.persist(booking);

        BookingDto updatedBookingDto = bookingService.update(booking.getId(), owner.getId(), true);

        assertEquals(BookingStatus.APPROVED, updatedBookingDto.getStatus());
    }

    @Test
    void testGetBookingById() {
        Booking booking = new Booking();
        booking.setStart(LocalDateTime.now().plusHours(1));
        booking.setEnd(LocalDateTime.now().plusHours(2));
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStatus(BookingStatus.WAITING);

        entityManager.persist(booking);

        BookingDto bookingDto = bookingService.getBookingById(booking.getId(), booker.getId());

        assertNotNull(bookingDto);
        assertEquals(booking.getId(), bookingDto.getId());
    }

    @Test
    void testGetBookings() {
        Booking booking1 = new Booking();
        booking1.setStart(LocalDateTime.now().plusHours(1));
        booking1.setEnd(LocalDateTime.now().plusHours(2));
        booking1.setItem(item);
        booking1.setBooker(booker);
        booking1.setStatus(BookingStatus.WAITING);

        Booking booking2 = new Booking();
        booking2.setStart(LocalDateTime.now().plusHours(3));
        booking2.setEnd(LocalDateTime.now().plusHours(4));
        booking2.setItem(item);
        booking2.setBooker(booker);
        booking2.setStatus(BookingStatus.WAITING);

        entityManager.persist(booking1);
        entityManager.persist(booking2);

        List<BookingDto> bookings = bookingService.getBookings("ALL", booker.getId());

        assertEquals(2, bookings.size());
    }

    @Test
    void testGetBookingsOwner() {
        Booking booking1 = new Booking();
        booking1.setStart(LocalDateTime.now().plusHours(1));
        booking1.setEnd(LocalDateTime.now().plusHours(2));
        booking1.setItem(item);
        booking1.setBooker(booker);
        booking1.setStatus(BookingStatus.WAITING);

        Booking booking2 = new Booking();
        booking2.setStart(LocalDateTime.now().plusHours(3));
        booking2.setEnd(LocalDateTime.now().plusHours(4));
        booking2.setItem(item);
        booking2.setBooker(booker);
        booking2.setStatus(BookingStatus.WAITING);

        entityManager.persist(booking1);
        entityManager.persist(booking2);

        List<BookingDto> bookings = bookingService.getBookingsOwner("ALL", owner.getId());

        assertEquals(2, bookings.size());
    }

    @Test
    void testGetLastBooking() {
        Booking booking = new Booking();
        booking.setStart(LocalDateTime.now().minusHours(2));
        booking.setEnd(LocalDateTime.now().minusHours(2));
        booking.setItem(item);
        booking.setBooker(booker);
        booking.setStatus(BookingStatus.APPROVED);

        entityManager.persist(booking);

        BookingShortDto lastBooking = bookingService.getLastBooking(item.getId());

        assertNotNull(lastBooking);
        assertEquals(booking.getId(), lastBooking.getId());
    }

    @Test
    void testGetNextBooking() {
        Booking booking1 = new Booking();
        booking1.setStart(LocalDateTime.now().plusHours(1));
        booking1.setEnd(LocalDateTime.now().plusHours(2));
        booking1.setItem(item);
        booking1.setBooker(booker);
        booking1.setStatus(BookingStatus.APPROVED);
        entityManager.persist(booking1);

        BookingShortDto nextBooking = bookingService.getNextBooking(item.getId());

        assertNotNull(nextBooking);
        assertEquals(booking1.getId(), nextBooking.getId());
    }
}
