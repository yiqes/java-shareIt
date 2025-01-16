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


/**
 * The type Item controller.
 */
@Controller
@RequestMapping(path = "/items")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ItemController {
    private static final String USER_ID = "X-Sharer-User-Id";
    private final ItemClient itemClient;
    private static final String ITEM_ID = "{item-id}";


    /**
     * Gets items by owner.
     *
     * @param ownerId the owner id
     * @param from    the from
     * @param size    the size
     * @return the items by owner
     */
    @GetMapping
    public ResponseEntity<Object> getItemsByOwner(@RequestHeader(USER_ID) Long ownerId,
                                                  @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                  @RequestParam(required = false) Integer size) {
        log.info("Получен GET-запрос к эндпоинту: '/items' на получение всех вещей владельца с ID={}", ownerId);
        return itemClient.getItemsByOwner(ownerId, from, size);
    }

    /**
     * Create response entity.
     *
     * @param userId  the user id
     * @param itemDto the item dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<Object> create(@RequestHeader(USER_ID) Long userId,
                                         @RequestBody @Valid ItemDto itemDto) {
        log.info("Создание вещи {}, userId={}", itemDto, userId);
        return itemClient.create(userId, itemDto);
    }

    /**
     * Gets item by id.
     *
     * @param userId the user id
     * @param itemId the item id
     * @return the item by id
     */
    @GetMapping(ITEM_ID)
    public ResponseEntity<Object> getItemById(@RequestHeader(USER_ID) Long userId,
                                              @PathVariable("item-id") Long itemId) {
        log.info("Запрос вещи {}, userId={}", itemId, userId);
        return itemClient.getItemById(userId, itemId);
    }

    /**
     * Update response entity.
     *
     * @param itemDto the item dto
     * @param itemId  the item id
     * @param userId  the user id
     * @return the response entity
     */
    @ResponseBody
    @PatchMapping(ITEM_ID)
    public ResponseEntity<Object> update(@RequestBody ItemDto itemDto, @PathVariable("item-id") Long itemId,
                                         @RequestHeader(USER_ID) Long userId) {
        log.info("Получен PATCH-запрос к эндпоинту: '/items' на обновление вещи с ID={}", itemId);
        return itemClient.update(itemDto, itemId, userId);
    }

    /**
     * Delete response entity.
     *
     * @param itemId  the item id
     * @param ownerId the owner id
     * @return the response entity
     */
    @DeleteMapping(ITEM_ID)
    public ResponseEntity<Object> delete(@PathVariable("item-id") Long itemId, @RequestHeader(USER_ID) Long ownerId) {
        log.info("Получен DELETE-запрос к эндпоинту: '/items' на удаление вещи с ID={}", itemId);
        return itemClient.delete(itemId, ownerId);
    }

    /**
     * Gets items by search query.
     *
     * @param text the text
     * @param from the from
     * @param size the size
     * @return the items by search query
     */
    @GetMapping("/search")
    public ResponseEntity<Object> getItemsBySearchQuery(@RequestParam String text,
                                                        @PositiveOrZero @RequestParam(defaultValue = "0") Integer from,
                                                        @RequestParam(required = false) Integer size) {
        log.info("Получен GET-запрос к эндпоинту: '/items/search' на поиск вещи с текстом={}", text);
        return itemClient.getItemsBySearchQuery(text, from, size);
    }

    /**
     * Create comment response entity.
     *
     * @param commentDto the comment dto
     * @param userId     the user id
     * @param itemId     the item id
     * @return the response entity
     */
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