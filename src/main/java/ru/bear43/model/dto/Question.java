package ru.bear43.model.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public record Question(Long id, String text, List<Answer> answers) {
    public Question with(List<Answer> answers) {
        return new Question(id, text, Collections.unmodifiableList(answers));
    }
}
