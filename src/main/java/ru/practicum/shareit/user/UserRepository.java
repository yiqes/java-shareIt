package ru.practicum.shareit.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /*List<User> findAll();

    User save(User user);

    User findById(Long id);

    User updateUserById(User user, Long id);

    void deleteUser(Long id);*/
}