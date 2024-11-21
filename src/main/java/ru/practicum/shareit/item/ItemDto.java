package ru.practicum.shareit.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import ru.practicum.shareit.booking.dto.BookingShortDto;
import ru.practicum.shareit.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemDto {

    Long id;

    @NotBlank(message = "Name cannot be blank")
    String name;

    @NotBlank(message = "Description cannot be blank")
    String description;

    @NotNull(message = "Available cannot be null")
    Boolean available;

    @JsonIgnore
    User owner;
    Long requestId;
    BookingShortDto lastBooking;
    BookingShortDto nextBooking;
    List<CommentDto> comments;
}