package ru.bear43.dao;

import ru.bear43.model.dto.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerDao {
    Long create(Long questionId, String text);
    Optional<Answer> find(Long answerId);
    void remove(Long answerId);
    List<Answer> findByQuestionId(Long questionId);
    void removeByQuestionIds(List<Long> questionIds);
    void removeByFormIds(List<Long> formIds);
}
