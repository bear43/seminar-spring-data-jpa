package ru.bear43.model.mapper;

import ru.bear43.model.dto.Answer;
import ru.bear43.model.entity.AnswerEntity;

import java.util.List;

public class AnswerMapper {

    public static Answer map(AnswerEntity answerEntity) {
        return new Answer(answerEntity.getId(), answerEntity.getText());
    }

    public static List<Answer> map(List<AnswerEntity> answers) {
        return answers.stream()
                .map(AnswerMapper::map)
                .toList();
    }
}
