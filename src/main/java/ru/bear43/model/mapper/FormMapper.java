package ru.bear43.model.mapper;

import ru.bear43.model.dto.Form;
import ru.bear43.model.dto.Question;
import ru.bear43.model.entity.FormEntity;

import java.util.List;

public class FormMapper {
    public static Form map(FormEntity formEntity) {
        List<Question> questions = QuestionMapper.map(formEntity.getQuestions());
        return new Form(formEntity.getId(), formEntity.getTitle(), questions);
    }
}
