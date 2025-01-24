package ru.bear43.dao.util;

import org.jetbrains.annotations.NotNull;
import ru.bear43.model.dto.Answer;
import ru.bear43.model.dto.Question;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static @NotNull Question getQuestion(String question, String... answers) {
        List<Answer> answerList = getAnswers(answers);
        return new Question(1L, question, answerList);
    }

    public static List<Answer> getAnswers(String... answers) {
        ArrayList<Answer> answerList = new ArrayList<>(answers.length);
        for (int i = 0; i < answers.length; i++) {
            answerList.add(new Answer(i + 1L, answers[i]));
        }
        return answerList;
    }
}
