package ru.bear43.service;

public interface UserAnswerService {
    void answer(Long userId, Long formId, Long questionId, Long answerId);
    void remove(Long userId, Long formId, Long questionId);
}
