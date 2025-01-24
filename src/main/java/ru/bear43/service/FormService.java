package ru.bear43.service;

import ru.bear43.model.dto.Form;
import ru.bear43.model.dto.Question;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FormService {
    Long create(String title);
    Optional<Form> find(Long formId);
    void remove(Set<Long> formId);
    Long addQuestions(Long formId, List<Question> question);
    void removeQuestions(Long formId, List<Long> questionId);
}
