package ru.practicum.shareit.item;

import java.util.List;

/**
 * The interface Item service.
 */
public interface ItemService {

    /**
     * Gets item by id.
     *
     * @param id     the id
     * @param userId the user id
     * @return the item by id
     */
    ItemDto getItemById(Long id, Long userId);

    /**
     * Find item by id item.
     *
     * @param id the id
     * @return the item
     */
    Item findItemById(Long id);

    /**
     * Create item dto.
     *
     * @param itemDto the item dto
     * @param ownerId the owner id
     * @return the item dto
     */
    ItemDto create(ItemDto itemDto, Long ownerId);

    /**
     * Gets items by owner.
     *
     * @param ownerId the owner id
     * @return the items by owner
     */
    List<ItemDto> getItemsByOwner(Long ownerId);

    /**
     * Delete.
     *
     * @param itemId  the item id
     * @param ownerId the owner id
     */
    void delete(Long itemId, Long ownerId);

    /**
     * Gets items by search query.
     *
     * @param text the text
     * @return the items by search query
     */
    List<ItemDto> getItemsBySearchQuery(String text);

    /**
     * Update item dto.
     *
     * @param itemDto the item dto
     * @param ownerId the owner id
     * @param itemId  the item id
     * @return the item dto
     */
    ItemDto update(ItemDto itemDto, Long ownerId, Long itemId);

    /**
     * Create comment comment dto.
     *
     * @param commentDto the comment dto
     * @param itemId     the item id
     * @param userId     the user id
     * @return the comment dto
     */
    CommentDto createComment(CommentDto commentDto, Long itemId, Long userId);

    /**
     * Gets comments by item id.
     *
     * @param itemId the item id
     * @return the comments by item id
     */
    List<CommentDto> getCommentsByItemId(Long itemId);

    /**
     * Gets by request id.
     *
     * @param requestId the request id
     * @return the by request id
     */
    List<ItemDto> getByRequestId(Long requestId);
}