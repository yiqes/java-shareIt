package ru.practicum.shareit.user;

import org.springframework.stereotype.Component;

/**
 * The type User mapper.
 */
@Component
public class UserMapper {
    /**
     * To user dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRegistrationDate()
        );
    }

    /**
     * To user user.
     *
     * @param userDto the user dto
     * @return the user
     */
    public User toUser(UserDto userDto) {
        return new User(
                userDto.getId(),
                userDto.getEmail(),
                userDto.getName(),
                userDto.getRegistrationDate()
        );
    }
}