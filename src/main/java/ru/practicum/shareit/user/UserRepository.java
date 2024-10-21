package ru.practicum.shareit.user;

import java.util.List;

interface UserRepository {
    List<User> findAll();

    User save(User user);

    User findById(Long id);

    User updateUserById(User user, Long id);

    void deleteUser(Long id);
}