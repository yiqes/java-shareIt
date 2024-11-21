package ru.practicum.shareit.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JsonTest
public class RequestDtoJsonTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testSerialize() throws Exception {
        RequestDto requestDto = new RequestDto(1L, "Test Request", null, LocalDateTime.now(), null);
        String json = objectMapper.writeValueAsString(requestDto);

        assertEquals("{\"id\":1,\"description\":\"Test Request\",\"requestorId\":null,\"created\":\"" + requestDto.getCreated().toString() + "\",\"items\":null}", json);
    }

    @Test
    void testDeserialize() throws Exception {
        String json = "{\"id\":1,\"description\":\"Test Request\",\"requestorId\":null,\"created\":\"2023-10-01T00:00:00\",\"items\":null}";
        RequestDto requestDto = objectMapper.readValue(json, RequestDto.class);

        assertEquals(1L, requestDto.getId());
        assertEquals("Test Request", requestDto.getDescription());
        assertEquals(LocalDateTime.parse("2023-10-01T00:00:00"), requestDto.getCreated());
    }
}
