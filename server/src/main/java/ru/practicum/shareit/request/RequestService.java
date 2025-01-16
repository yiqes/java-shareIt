package ru.practicum.shareit.request;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The interface Request service.
 */
public interface RequestService {

    /**
     * Save request request dto.
     *
     * @param requestDto  the request dto
     * @param requestorId the requestor id
     * @param created     the created
     * @return the request dto
     */
    RequestDto saveRequest(RequestDto requestDto, Long requestorId, LocalDateTime created);

    /**
     * Gets request by id.
     *
     * @param requestId the request id
     * @param userId    the user id
     * @return the request by id
     */
    RequestDto getRequestById(Long requestId, Long userId);

    /**
     * Gets all requests.
     *
     * @param userId the user id
     * @param from   the from
     * @param size   the size
     * @return the all requests
     */
    List<RequestDto> getAllRequests(Long userId, Integer from, Integer size);

    /**
     * Gets own requests.
     *
     * @param requestorId the requestor id
     * @return the own requests
     */
    List<RequestDto> getOwnRequests(Long requestorId);
}
