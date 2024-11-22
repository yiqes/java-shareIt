package ru.practicum.shareit.item;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;


@Controller
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {
    private static final String USER_ID = "X-Sharer-User-Id";
    private final ItemClient itemClient;
    private final String ID = "{item-id}";


    @GetMapping
    public ResponseEntity<Object> getItemsByOwner(@RequestHeader(USER_ID) Long ownerId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @RequestParam(required = false) Integer size) {
        log.info("Получен GET-запрос к эндпоинту: '/items' на получение всех вещей владельца с ID={}", ownerId);
        return itemClient.getItemsByOwner(ownerId, from, size);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(USER_ID) Long userId,
                                         @RequestBody @Valid ItemDto itemDto) {
        log.info("Создание вещи {}, userId={}", itemDto, userId);
        return itemClient.create(userId, itemDto);
    }

    @GetMapping(ID)
    public ResponseEntity<Object> getItemById(@RequestHeader(USER_ID) Long userId,
                                              @PathVariable("item-id") Long itemId) {
        log.info("Запрос вещи {}, userId={}", itemId, userId);
        return itemClient.getItemById(userId, itemId);
    }

    @ResponseBody
    @PatchMapping(ID)
    public ResponseEntity<Object> update(@RequestBody ItemDto itemDto, @PathVariable("item-id") Long itemId,
                                         @RequestHeader(USER_ID) Long userId) {
        log.info("Получен PATCH-запрос к эндпоинту: '/items' на обновление вещи с ID={}", itemId);
        return itemClient.update(itemDto, itemId, userId);
    }

    @DeleteMapping(ID)
    public ResponseEntity<Object> delete(@PathVariable("item-id") Long itemId, @RequestHeader(USER_ID) Long ownerId) {
        log.info("Получен DELETE-запрос к эндпоинту: '/items' на удаление вещи с ID={}", itemId);
        return itemClient.delete(itemId, ownerId);
    }

    @GetMapping("/search")
    public ResponseEntity<Object> getItemsBySearchQuery(@RequestParam String text,
                                                        @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @RequestParam(required = false) Integer size) {
        log.info("Получен GET-запрос к эндпоинту: '/items/search' на поиск вещи с текстом={}", text);
        return itemClient.getItemsBySearchQuery(text, from, size);
    }

    @ResponseBody
    @PostMapping("/{item-id}/comment")
    public ResponseEntity<Object> createComment(@RequestBody @Valid CommentDto commentDto,
                                                @RequestHeader(USER_ID) Long userId,
                                                @PathVariable("item-id") Long itemId) {
        log.info("Получен POST-запрос к эндпоинту: '/items/comment' на" +
                " добавление отзыва пользователем с ID={}", userId);
        return itemClient.createComment(commentDto, itemId, userId);
    }
}