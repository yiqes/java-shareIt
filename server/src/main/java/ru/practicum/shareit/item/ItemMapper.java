package ru.practicum.shareit.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.service.ValidationService;

/**
 * The type Item mapper.
 */
@Component
public class ItemMapper {

    private ValidationService validationService;

    /**
     * Instantiates a new Item mapper.
     *
     * @param validationService the validation service
     */
    @Autowired
    @Lazy
    public ItemMapper(ValidationService validationService) {
        this.validationService = validationService;
    }

    /**
     * To item dto item dto.
     *
     * @param item the item
     * @return the item dto
     */
    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getOwner(),
                item.getRequestId() != null ? item.getRequestId() : null,
                null,
                null,
                validationService.getCommentsByItemId(item.getId()));
    }

    /**
     * To item ext dto item dto.
     *
     * @param item the item
     * @return the item dto
     */
    public ItemDto toItemExtDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getAvailable(),
                item.getOwner(),
                item.getRequestId() != null ? item.getRequestId() : null,
                validationService.getLastBooking(item.getId()),
                validationService.getNextBooking(item.getId()),
                validationService.getCommentsByItemId(item.getId()));
    }

    /**
     * To item item.
     *
     * @param itemDto the item dto
     * @param ownerId the owner id
     * @return the item
     */
    public Item toItem(ItemDto itemDto, Long ownerId) {
        return new Item(
                itemDto.getId(),
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getAvailable(),
                validationService.findUserById(ownerId),
                itemDto.getRequestId() != null ? itemDto.getRequestId() : null
        );
    }

    /**
     * To comment dto comment dto.
     *
     * @param comment the comment
     * @return the comment dto
     */
    public CommentDto toCommentDto(Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getText(),
                comment.getItem(),
                comment.getAuthor().getName(),
                comment.getCreated());
    }
}