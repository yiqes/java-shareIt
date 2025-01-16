package ru.practicum.shareit.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;

import ru.practicum.shareit.booking.dto.BookItemRequestDto;
import ru.practicum.shareit.booking.dto.BookingState;
import ru.practicum.shareit.client.BaseClient;

/**
 * The type Booking client.
 */
@Service
public class BookingClient extends BaseClient {
    private static final String API_PREFIX = "/bookings";

    /**
     * Instantiates a new Booking client.
     *
     * @param serverUrl the server url
     * @param builder   the builder
     */
    @Autowired
    public BookingClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    /**
     * Gets bookings.
     *
     * @param userId the user id
     * @param state  the state
     * @param from   the from
     * @param size   the size
     * @return the bookings
     */
    public ResponseEntity<Object> getBookings(Long userId, BookingState state, Integer from, Integer size) {
        String path = "?state=" + state.name() + "&from=" + from;
        if (size != null) {
            path += "&size=" + size;
        }
        return get(path, userId, null);
    }

    /**
     * Gets bookings owner.
     *
     * @param userId the user id
     * @param state  the state
     * @param from   the form
     * @param size   the size
     * @return the bookings owner
     */
    public ResponseEntity<Object> getBookingsOwner(Long userId, BookingState state, Integer from, Integer size) {
        String path = "/owner?state=" + state.name() + "&from=" + from;
        if (size != null) {
            path += "&size=" + size;
        }
        return get(path, userId, null);
    }


    /**
     * Create response entity.
     *
     * @param userId     the user id
     * @param requestDto the request dto
     * @return the response entity
     */
    public ResponseEntity<Object> create(Long userId, BookItemRequestDto requestDto) {
        return post("", userId, requestDto);
    }

    /**
     * Gets booking.
     *
     * @param userId    the user id
     * @param bookingId the booking id
     * @return the booking
     */
    public ResponseEntity<Object> getBooking(Long userId, Long bookingId) {
        return get("/" + bookingId, userId);
    }

    /**
     * Update response entity.
     *
     * @param bookingId the booking id
     * @param userId    the user id
     * @param approved  the approved
     * @return the response entity
     */
    public ResponseEntity<Object> update(Long bookingId, Long userId, Boolean approved) {
        String path = "/" + bookingId + "?approved=" + approved;
        return patch(path, userId, null, null);
    }
}