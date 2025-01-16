package ru.practicum.shareit.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.exception.UserNotFoundException;

import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final String USER_ID = "{user-id}";

    /**
     * Create user response entity.
     *
     * @param userDto the user dto
     * @return the response entity
     */
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.saveUser(userDto);
        return ResponseEntity.ok(createdUser);
    }

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @GetMapping(USER_ID)
    public ResponseEntity<UserDto> getUser(@PathVariable("user-id") Long userId) {
        UserDto userDto = userService.getUser(userId);
        return userDto != null ? ResponseEntity.ok(userDto) : ResponseEntity.notFound().build();
    }

    /**
     * Gets all users.
     *
     * @return the all users
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.noContent().build();
    }

    /**
     * Update user response entity.
     *
     * @param userId  the user id
     * @param userDto the user dto
     * @return the response entity
     */
    @PatchMapping(USER_ID)
    public ResponseEntity<UserDto> updateUser(@PathVariable("user-id") Long userId, @RequestBody UserDto userDto) {
        try {
            UserDto updatedUser = userService.updateUserById(userId, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @DeleteMapping(USER_ID)
    public ResponseEntity<Void> deleteUser(@PathVariable("user-id") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}