package ru.bear43.service;

import ru.bear43.model.dto.Answer;

import java.util.Optional;

public interface AnswerService {
    Long create(String text);
    Optional<Answer> find(Long answerId);
    void remove(Long answerId);
}
