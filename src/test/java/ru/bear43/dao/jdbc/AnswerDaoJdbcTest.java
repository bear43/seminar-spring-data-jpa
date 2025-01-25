package ru.bear43.dao.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.EmptySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import ru.bear43.dao.AnswerDao;
import ru.bear43.dao.JdbcTest;
import ru.bear43.model.dto.Answer;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes = AnswerDaoJdbc.class)
class AnswerDaoJdbcTest extends JdbcTest {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    private AnswerDao answerDao;

    @Test
    @DisplayName("Успешное создание ответа")
    public void testCreate() {
        insertFormAndQuestion();
        String text = "I am!";

        Long answerId = answerDao.create(1L, text);
        Answer answer = answerDao.find(answerId).get();

        Assertions.assertEquals(1L, answer.id());
        Assertions.assertEquals(text, answer.text());
    }

    private void insertFormAndQuestion() {
        jdbcTemplate.update("""
                    with cte as (insert into form(title) values ('Survey') returning id)
                    insert into question(form_id, "text")
                    select c.id, 'Are you?' from cte c
            """, EmptySqlParameterSource.INSTANCE);
    }
}