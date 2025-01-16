package ru.practicum.shareit.user;

import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Save user user dto.
     *
     * @param userDto the user dto
     * @return the user dto
     */
    UserDto saveUser(UserDto userDto);

    /**
     * Gets user.
     *
     * @param id the id
     * @return the user
     */
    UserDto getUser(Long id);

    /**
     * Gets all users.
     *
     * @return the all users
     */
    List<UserDto> getAllUsers();

    /**
     * Delete user.
     *
     * @param id the id
     */
    void deleteUser(Long id);

    /**
     * Update user by id user dto.
     *
     * @param id      the id
     * @param userDto the user dto
     * @return the user dto
     */
    UserDto updateUserById(Long id, UserDto userDto);

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     */
    User findUserById(Long userId);

    /**
     * Exists by id boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean existsById(Long id);
}