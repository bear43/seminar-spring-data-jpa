package ru.bear43.dao;

import ru.bear43.model.dto.Form;

import java.util.List;
import java.util.Optional;

public interface FormDao {
    Long create(String title);
    Optional<Form> find(Long formId);
    void remove(List<Long> formId);
}
