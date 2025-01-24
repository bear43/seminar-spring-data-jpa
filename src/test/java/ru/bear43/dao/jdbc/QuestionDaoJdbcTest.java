package ru.bear43.dao.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.AnswerDao;
import ru.bear43.dao.JdbcTest;
import ru.bear43.dao.QuestionDao;
import ru.bear43.dao.util.Utils;
import ru.bear43.model.dto.Answer;
import ru.bear43.model.dto.Question;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = QuestionDaoJdbc.class)
class QuestionDaoJdbcTest extends JdbcTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private QuestionDao questionDao;

    @MockitoBean
    private AnswerDao answerDao;

    @Test
    @DisplayName("Успешное создание вопроса")
    public void testCreate() {
        long generatedFormId = 1L, generatedQuestionId = 1L;
        String text = "Слушали ли вы 8 симфонию Чайковского?";
        List<Answer> answers = Utils.getAnswers("Да, ну, конечно!", "Нет", "Думаешь, я тебя не переиграю? Я тебя не уничтожу!?");
        Mockito.doReturn(answers)
                .when(answerDao)
                .findByQuestionId(generatedQuestionId);

        insertForm();
        Long questionId = questionDao.create(generatedFormId, text);
        Question question = questionDao.find(questionId).get();

        Assertions.assertEquals(generatedQuestionId, question.id());
        Assertions.assertEquals(text, question.text());
        Assertions.assertEquals(answers, question.answers());
    }

    @Test
    @DisplayName("Поиск вопросов по опросу")
    public void testFindByForm() {

    }


    private void insertForm() {
        jdbcTemplate.update("insert into \"form\"(title) values ('Maestro survey')", EmptySqlParameterSource.INSTANCE);
    }
}