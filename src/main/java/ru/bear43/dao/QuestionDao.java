package ru.bear43.dao;

import ru.bear43.model.dto.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionDao {
    Long create(Long formId, String text);
    Optional<Question> find(Long questionId);
    List<Question> findByFormId(Long formId);
    void remove(List<Long> questionIds);
    void removeByFormId(List<Long> formIds);
}
