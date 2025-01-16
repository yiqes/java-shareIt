package ru.practicum.shareit.item;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Item controller.
 */
@Slf4j
@RestController
@RequestMapping("/items")
public class ItemController {
    private static final String OWNER = "X-Sharer-User-Id";
    private ItemService itemService;
    private static final String ITEM_ID = "{item-id}";

    /**
     * Instantiates a new Item controller.
     *
     * @param itemService the item service
     */
    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * Gets item by id.
     *
     * @param itemId  the item id
     * @param ownerId the owner id
     * @return the item by id
     */
    @GetMapping(ITEM_ID)
    public ItemDto getItemById(@PathVariable("item-id") Long itemId, @RequestHeader(OWNER) Long ownerId) {
        log.info("Получен GET-запрос к эндпоинту: '/items' на получение вещи с ID={}", itemId);
        return itemService.getItemById(itemId, ownerId);
    }

    /**
     * Create item dto.
     *
     * @param itemDto the item dto
     * @param ownerId the owner id
     * @return the item dto
     */
    @ResponseBody
    @PostMapping
    public ItemDto create(@RequestBody ItemDto itemDto, @RequestHeader(OWNER) Long ownerId) {
        log.info("Получен POST-запрос к эндпоинту: '/items' на добавление вещи владельцем с ID={}", ownerId);
        return itemService.create(itemDto, ownerId);
    }

    /**
     * Gets items by owner.
     *
     * @param ownerId the owner id
     * @return the items by owner
     */
    @GetMapping
    public List<ItemDto> getItemsByOwner(@RequestHeader(OWNER) Long ownerId) {
        log.info("Получен GET-запрос к эндпоинту: '/items' на получение всех вещей владельца с ID={}", ownerId);
        return itemService.getItemsByOwner(ownerId);
    }

    /**
     * Update item dto.
     *
     * @param itemDto the item dto
     * @param itemId  the item id
     * @param ownerId the owner id
     * @return the item dto
     */
    @ResponseBody
    @PatchMapping(ITEM_ID)
    public ItemDto update(@RequestBody ItemDto itemDto, @PathVariable("item-id") Long itemId,
                          @RequestHeader(OWNER) Long ownerId) {
        log.info("Получен PATCH-запрос к эндпоинту: '/items' на обновление вещи с ID={}", itemId);
        return itemService.update(itemDto, ownerId, itemId);
    }

    /**
     * Delete.
     *
     * @param itemId  the item id
     * @param ownerId the owner id
     */
    @DeleteMapping(ITEM_ID)
    public void delete(@PathVariable("item-id") Long itemId, @RequestHeader(OWNER) Long ownerId) {
        log.info("Получен DELETE-запрос к эндпоинту: '/items' на удаление вещи с ID={}", itemId);
        itemService.delete(itemId, ownerId);
    }

    /**
     * Gets items by search query.
     *
     * @param text the text
     * @return the items by search query
     */
    @GetMapping("/search")
    public List<ItemDto> getItemsBySearchQuery(@RequestParam String text) {
        log.info("Получен GET-запрос к эндпоинту: '/items/search' на поиск вещи с текстом={}", text);
        return itemService.getItemsBySearchQuery(text);
    }

    /**
     * Create comment comment dto.
     *
     * @param commentDto the comment dto
     * @param userId     the user id
     * @param itemId     the item id
     * @return the comment dto
     */
    @ResponseBody
    @PostMapping("/{item-id}/comment")
    public CommentDto createComment(@RequestBody CommentDto commentDto, @RequestHeader(OWNER) Long userId,
                                    @PathVariable("item-id") Long itemId) {
        log.info("Получен POST-запрос к эндпоинту: '/items/comment' на" +
                " добавление отзыва пользователем с ID={}", userId);
        return itemService.createComment(commentDto, itemId, userId);
    }
}