package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.item.dto.CommentDto;
import ru.practicum.shareit.item.dto.ItemDto;

/**
 * The type Item client.
 */
@Service
public class ItemClient extends BaseClient {
    private static final String API_PREFIX = "/items";

    /**
     * Instantiates a new Item client.
     *
     * @param serverUrl the server url
     * @param builder   the builder
     */
    @Autowired
    public ItemClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    /**
     * Create response entity.
     *
     * @param userId  the user id
     * @param itemDto the item dto
     * @return the response entity
     */
    public ResponseEntity<Object> create(Long userId, ItemDto itemDto) {
        return post("", userId, itemDto);
    }

    /**
     * Gets item by id.
     *
     * @param userId the user id
     * @param itemId the item id
     * @return the item by id
     */
    public ResponseEntity<Object> getItemById(Long userId, Long itemId) {
        return get("/" + itemId, userId);
    }

    /**
     * Gets items by owner.
     *
     * @param userId the user id
     * @param from   the from
     * @param size   the size
     * @return the items by owner
     */
    public ResponseEntity<Object> getItemsByOwner(Long userId, Integer from, Integer size) {
        String path = "?from=" + from;
        if (size != null) {
            path += "&size=" + size;
        }
        return get(path, userId);
    }

    /**
     * Update response entity.
     *
     * @param itemDto the item dto
     * @param itemId  the item id
     * @param userId  the user id
     * @return the response entity
     */
    public ResponseEntity<Object> update(ItemDto itemDto, Long itemId, Long userId) {
        return patch("/" + itemId, userId, itemDto);
    }

    /**
     * Delete response entity.
     *
     * @param itemId the item id
     * @param userId the user id
     * @return the response entity
     */
    public ResponseEntity<Object> delete(Long itemId, Long userId) {
        return delete("/" + itemId, userId);
    }

    /**
     * Gets items by search query.
     *
     * @param text the text
     * @param from the from
     * @param size the size
     * @return the items by search query
     */
    public ResponseEntity<Object> getItemsBySearchQuery(String text, Integer from, Integer size) {
        String path = "/search?text=" + text + "&from=" + from;
        if (size != null) {
            path += "&size=" + size;
        }
        return get(path);
    }

    /**
     * Create comment response entity.
     *
     * @param commentDto the comment dto
     * @param itemId     the item id
     * @param userId     the user id
     * @return the response entity
     */
    public ResponseEntity<Object> createComment(CommentDto commentDto, Long itemId, Long userId) {
        return post("/" + itemId + "/comment", userId, commentDto);
    }
}