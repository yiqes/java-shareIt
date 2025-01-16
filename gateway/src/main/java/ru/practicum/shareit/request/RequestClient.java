package ru.practicum.shareit.request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.request.dto.RequestDto;

/**
 * The type Request client.
 */
@Service
public class RequestClient extends BaseClient {
    private static final String API_PREFIX = "/requests";

    /**
     * Instantiates a new Request client.
     *
     * @param serverUrl the server url
     * @param builder   the builder
     */
    @Autowired
    public RequestClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
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
     * @param requestDto  the request dto
     * @param requestorId the requestor id
     * @return the response entity
     */
    public ResponseEntity<Object> create(RequestDto requestDto, Long requestorId) {
        return post("", requestorId, requestDto);
    }

    /**
     * Gets item request by id.
     *
     * @param userId    the user id
     * @param requestId the request id
     * @return the item request by id
     */
    public ResponseEntity<Object> getItemRequestById(Long userId, Long requestId) {
        return get("/" + requestId, userId);
    }

    /**
     * Gets own item requests.
     *
     * @param userId the user id
     * @return the own item requests
     */
    public ResponseEntity<Object> getOwnItemRequests(Long userId) {
        return get("", userId);
    }

    /**
     * Gets all item requests.
     *
     * @param userId the user id
     * @param from   the from
     * @param size   the size
     * @return the all item requests
     */
    public ResponseEntity<Object> getAllItemRequests(Long userId, Integer from, Integer size) {
        String path = "/all" + "?from=" + from;
        if (size != null) {
            path += "&size=" + size;
        }
        return get(path, userId, null);
    }
}