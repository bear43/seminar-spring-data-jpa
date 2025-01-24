package ru.bear43.dao;

public interface UserAnswerDao {
    void answer(Long userId, Long formId, Long questionId, Long answerId);
    void remove(Long userId, Long formId, Long questionId);
}
