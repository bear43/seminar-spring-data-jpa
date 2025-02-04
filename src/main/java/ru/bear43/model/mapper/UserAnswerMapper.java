package ru.bear43.model.mapper;

import ru.bear43.model.dto.*;
import ru.bear43.model.entity.QuestionEntity;
import ru.bear43.model.entity.UserAnswerEntity;
import ru.bear43.model.entity.UserEntity;

public class UserAnswerMapper {

    public static UserAnswer map(UserAnswerEntity userAnswerEntity) {
        UserEntity userEntity = userAnswerEntity.getUser();
        User user = new User(userEntity.getId(), userEntity.getNickname());
        Form form = FormMapper.map(userAnswerEntity.getForm());
        Question question = QuestionMapper.map(userAnswerEntity.getQuestion());
        Answer answer = AnswerMapper.map(userAnswerEntity.getAnswer());
        return new UserAnswer(user, form, question, answer);
    }

}
