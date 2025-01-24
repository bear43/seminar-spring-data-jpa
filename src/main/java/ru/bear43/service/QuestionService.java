package ru.bear43.service;

import ru.bear43.model.dto.Answer;
import ru.bear43.model.dto.Question;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuestionService {
    Long create(String text, List<Answer> answers);
    Optional<Question> find(Long questionId);
    void remove(List<Long> questionId);
    void addAnswers(Long questionId, List<Answer> answers);
    void removeAnswers(Long questionId, List<Long> answers);
}
