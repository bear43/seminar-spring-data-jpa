package ru.bear43.model.dto;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public record Form(Long id, String title, List<Question> questions) {
    public Form with(List<Question> questions) {
        return new Form(id, title, Collections.unmodifiableList(questions));
    }
}
