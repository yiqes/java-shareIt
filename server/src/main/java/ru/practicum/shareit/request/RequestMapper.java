package ru.practicum.shareit.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.practicum.shareit.item.ItemService;
import ru.practicum.shareit.user.UserMapper;
import ru.practicum.shareit.user.UserService;

import java.time.LocalDateTime;

/**
 * The type Request mapper.
 */
@Component
public class RequestMapper {

    private UserMapper userMapper;
    private UserService userService;
    private ItemService itemService;

    /**
     * Instantiates a new Request mapper.
     *
     * @param userMapper  the user mapper
     * @param userService the user service
     * @param itemService the item service
     */
    @Autowired
    public RequestMapper(UserMapper userMapper, UserService userService, ItemService itemService) {
        this.userMapper = userMapper;
        this.userService = userService;
        this.itemService = itemService;
    }

    /**
     * To request dto request dto.
     *
     * @param request the request
     * @return the request dto
     */
    public RequestDto toRequestDto(Request request) {
        return new RequestDto(
                request.getId(),
                request.getDescription(),
                userMapper.toUserDto(request.getRequestor()),
                request.getCreated(),
                itemService.getByRequestId(request.getId())
        );
    }

    /**
     * To request request.
     *
     * @param requestDto  the request dto
     * @param requestorId the requestor id
     * @param created     the created
     * @return the request
     */
    public Request toRequest(RequestDto requestDto, Long requestorId, LocalDateTime created) {
        return new Request(
                null,
                requestDto.getDescription(),
                userService.findUserById(requestorId),
                created
        );
    }
}