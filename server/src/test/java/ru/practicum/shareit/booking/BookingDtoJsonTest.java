package ru.practicum.shareit.booking;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import ru.practicum.shareit.booking.dto.BookingDto;
import ru.practicum.shareit.booking.dto.BookingShortDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
public class BookingDtoJsonTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerializeBookingDto() throws Exception {
        BookingDto bookingDto = new BookingDto(1L, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2), null, null, null);
        String json = objectMapper.writeValueAsString(bookingDto);

        assertEquals("{\"id\":1,\"start\":\"" + bookingDto.getStart().toString() + "\",\"end\":\"" + bookingDto.getEnd().toString() + "\",\"item\":null,\"booker\":null,\"status\":null}", json);
    }

    @Test
    void testDeserializeBookingDto() throws Exception {
        String json = "{\"id\":1,\"start\":\"2023-10-01T10:00:00\",\"end\":\"2023-10-01T12:00:00\",\"item\":null,\"booker\":null,\"status\":null}";
        BookingDto bookingDto = objectMapper.readValue(json, BookingDto.class);

        assertEquals(1L, bookingDto.getId());
        assertEquals(LocalDateTime.parse("2023-10-01T10:00:00"), bookingDto.getStart());
        assertEquals(LocalDateTime.parse("2023-10-01T12:00:00"), bookingDto.getEnd());
    }

    @Test
    void testSerializeBookingShortDto() throws Exception {
        BookingShortDto bookingShortDto = new BookingShortDto(1L, 1L, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(2));
        String json = objectMapper.writeValueAsString(bookingShortDto);

        assertEquals("{\"id\":1,\"bookerId\":1,\"startTime\":\"" + bookingShortDto.getStartTime().toString() + "\",\"endTime\":\"" + bookingShortDto.getEndTime().toString() + "\"}", json);
    }

    @Test
    void testDeserializeBookingShortDto() throws Exception {
        String json = "{\"id\":1,\"bookerId\":1,\"startTime\":\"2023-10-01T10:00:00\",\"endTime\":\"2023-10-01T12:00:00\"}";
        BookingShortDto bookingShortDto = objectMapper.readValue(json, BookingShortDto.class);

        assertEquals(1L, bookingShortDto.getId());
        assertEquals(1L, bookingShortDto.getBookerId());
        assertEquals(LocalDateTime.parse("2023-10-01T10:00:00"), bookingShortDto.getStartTime());
        assertEquals(LocalDateTime.parse("2023-10-01T12:00:00"), bookingShortDto.getEndTime());
    }
}
