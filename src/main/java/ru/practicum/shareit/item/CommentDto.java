package ru.practicum.shareit.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private Long id;
    @NotEmpty @NotBlank
    private String text;
    @JsonIgnore
    private Item item;
    private String authorName;
    private LocalDateTime created;
}