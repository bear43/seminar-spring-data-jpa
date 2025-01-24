package ru.bear43.dao;

import ru.bear43.model.dto.User;

import java.util.Optional;

public interface UserDao {
    Long create(String nickname);
    Optional<User> find(Long userId);
}
