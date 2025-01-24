package ru.bear43.service;

import ru.bear43.model.dto.User;

import java.util.Optional;

public interface UserService {
    Long create(String nickname);
    Optional<User> find(Long userId);
}
