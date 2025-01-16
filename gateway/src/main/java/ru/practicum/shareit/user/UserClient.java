package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.shareit.client.BaseClient;
import ru.practicum.shareit.user.dto.UserDto;

/**
 * The type User client.
 */
@Service
public class UserClient extends BaseClient {
    private static final String API = "/users";

    /**
     * Instantiates a new User client.
     *
     * @param serverUrl the server url
     * @param builder   the builder
     */
    @Autowired
    public UserClient(@Value("${shareit-server.url}") String serverUrl, RestTemplateBuilder builder) {
        super(
                builder
                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API))
                        .requestFactory(() -> new HttpComponentsClientHttpRequestFactory())
                        .build()
        );
    }

    /**
     * Create response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    public ResponseEntity<Object> create(UserDto userDto) {
        return post("", userDto);
    }

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    public ResponseEntity<Object> getUserById(Long userId) {
        return get("/" + userId);
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    public ResponseEntity<Object> getUsers() {
        return get("");
    }

    /**
     * Update response entity.
     *
     * @param userDto the user dto
     * @param userId  the user id
     * @return the response entity
     */
    public ResponseEntity<Object> update(UserDto userDto, long userId) {
        return patch("/" + userId, userDto);
    }

    /**
     * Delete response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    public ResponseEntity<Object> delete(Long userId) {
        return delete("/" + userId);
    }
}
