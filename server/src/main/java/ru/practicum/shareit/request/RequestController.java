package ru.practicum.shareit.request;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * The type Request controller.
 */
@RestController
@RequestMapping("/requests")
public class RequestController {
    private final RequestService requestService;
    private static final String header = "X-Sharer-User-Id";

    /**
     * Instantiates a new Request controller.
     *
     * @param requestService the request service
     */
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    /**
     * Create request request dto.
     *
     * @param requestDto  the request dto
     * @param requestorId the requestor id
     * @return the request dto
     */
    @ResponseBody
    @PostMapping
    public RequestDto createRequest(@RequestBody RequestDto requestDto,
                                                    @RequestHeader(header) Long requestorId) {
        return requestService.saveRequest(requestDto, requestorId, LocalDateTime.now());
    }

    /**
     * Gets request.
     *
     * @param requestId the request id
     * @param userId    the user id
     * @return the request
     */
    @GetMapping("/{request-id}")
    public RequestDto getRequest(@PathVariable("request-id") Long requestId,
                                                 @RequestHeader(header) Long userId) {
        return requestService.getRequestById(requestId, userId);
    }

    /**
     * Gets all requests.
     *
     * @param userId the user id
     * @param from   the from
     * @param size   the size
     * @return the all requests
     */
    @GetMapping("/all")
    public List<RequestDto> getAllRequests(@RequestHeader(header) Long userId,
                                           @RequestParam(defaultValue = "0") Integer from,
                                           @RequestParam(required = false) Integer size) {
        return requestService.getAllRequests(userId, from, size);
    }

    /**
     * Gets own requests.
     *
     * @param userId the user id
     * @return the own requests
     */
    @GetMapping
    public List<RequestDto> getOwnRequests(@RequestHeader(header) Long userId) {
        return requestService.getOwnRequests(userId);
    }
}
