package ru.bear43.model.mapper;

import ru.bear43.model.dto.Answer;
import ru.bear43.model.dto.Question;
import ru.bear43.model.entity.QuestionEntity;

import java.util.List;

public class QuestionMapper {

    public static Question map(QuestionEntity questionEntity) {
        List<Answer> answers = AnswerMapper.map(questionEntity.getAnswers());
        return new Question(questionEntity.getId(), questionEntity.getText(), answers);
    }

    public static List<Question> map(List<QuestionEntity> questions) {
        return questions.stream()
                .map(QuestionMapper::map)
                .toList();
    }
}
