package ru.practicum.shareit.booking;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Booking repository.
 */
public interface BookingRepository extends JpaRepository<Booking, Long> {
    /**
     * Find by booker id list.
     *
     * @param bookerId the booker id
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByBookerId(Long bookerId, Sort sort);

    /**
     * Find by booker id and start is before and end is after list.
     *
     * @param bookerId the booker id
     * @param start    the start
     * @param end      the end
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByBookerIdAndStartIsBeforeAndEndIsAfter(Long bookerId, LocalDateTime start,
                                                              LocalDateTime end, Sort sort);

    /**
     * Find by booker id and end is before list.
     *
     * @param bookerId the booker id
     * @param end      the end
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByBookerIdAndEndIsBefore(Long bookerId, LocalDateTime end, Sort sort);

    /**
     * Find by booker id and start is after list.
     *
     * @param bookerId the booker id
     * @param start    the start
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByBookerIdAndStartIsAfter(Long bookerId, LocalDateTime start, Sort sort);

    /**
     * Find by booker id and status list.
     *
     * @param bookerId the booker id
     * @param status   the status
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByBookerIdAndStatus(Long bookerId, BookingStatus status, Sort sort);

    /**
     * Find by item owner id list.
     *
     * @param ownerId the owner id
     * @param sort    the sort
     * @return the list
     */
    List<Booking> findByItemOwnerId(Long ownerId, Sort sort);

    /**
     * Find by item owner id and start is before and end is after list.
     *
     * @param ownerId the owner id
     * @param start   the start
     * @param end     the end
     * @param sort    the sort
     * @return the list
     */
    List<Booking> findByItemOwnerIdAndStartIsBeforeAndEndIsAfter(Long ownerId, LocalDateTime start,
                                                                 LocalDateTime end, Sort sort);

    /**
     * Find by item owner id and end is before list.
     *
     * @param bookerId the booker id
     * @param end      the end
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByItemOwnerIdAndEndIsBefore(Long bookerId, LocalDateTime end, Sort sort);

    /**
     * Find by item owner id and start is after list.
     *
     * @param bookerId the booker id
     * @param start    the start
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByItemOwnerIdAndStartIsAfter(Long bookerId, LocalDateTime start, Sort sort);

    /**
     * Find by item owner id and status list.
     *
     * @param bookerId the booker id
     * @param status   the status
     * @param sort     the sort
     * @return the list
     */
    List<Booking> findByItemOwnerIdAndStatus(Long bookerId, BookingStatus status, Sort sort);

    /**
     * Find first by item id and end before order by end desc booking.
     *
     * @param itemId the item id
     * @param end    the end
     * @return the booking
     */
    Booking findFirstByItemIdAndEndBeforeOrderByEndDesc(Long itemId, LocalDateTime end);

    /**
     * Find first by item id and start after order by start asc booking.
     *
     * @param itemId the item id
     * @param end    the end
     * @return the booking
     */
    Booking findFirstByItemIdAndStartAfterOrderByStartAsc(Long itemId, LocalDateTime end);

    /**
     * Find first by item id and booker id and end is before and status booking.
     *
     * @param itemId the item id
     * @param userId the user id
     * @param end    the end
     * @param status the status
     * @return the booking
     */
    Booking findFirstByItemIdAndBookerIdAndEndIsBeforeAndStatus(Long itemId, Long userId,
                                                                LocalDateTime end, BookingStatus status);
}