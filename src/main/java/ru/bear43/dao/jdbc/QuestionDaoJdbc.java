package ru.bear43.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.AnswerDao;
import ru.bear43.dao.QuestionDao;
import ru.bear43.model.dto.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDaoJdbc implements QuestionDao {

    private static class Mapper implements RowMapper<Question> {
        @Override
        public Question mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String text = rs.getString("text");
            return new Question(id, text, Collections.emptyList());
        }
    }

    private static final Mapper mapper = new Mapper();
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final AnswerDao answerDao;

    @Autowired
    public QuestionDaoJdbc(NamedParameterJdbcTemplate jdbcTemplate, AnswerDao answerDao) {
        this.jdbcTemplate = jdbcTemplate;
        this.answerDao = answerDao;
    }

    @Transactional
    @Override
    public Long create(Long formId, String text) {
        return jdbcTemplate.queryForObject("insert into question(form_id, \"text\") values (:formId, :text) returning id",
                new MapSqlParameterSource("formId", formId)
                        .addValue("text", text),
                SingleColumnRowMapper.newInstance(Long.class));
    }

    @Transactional
    @Override
    public Optional<Question> find(Long questionId) {
        return jdbcTemplate.query("select q.id, q.\"text\" from question q where q.id = :id",
                new MapSqlParameterSource("id", questionId), mapper)
                .stream()
                .map(question -> question.with(answerDao.findByQuestionId(question.id())))
                .findFirst();
    }

    @Transactional
    @Override
    public List<Question> findByFormId(Long formId) {
        return jdbcTemplate.query("select q.id, q\"text\" from question q where q.from_id = :formId",
                new MapSqlParameterSource("formId", formId), mapper)
                .stream()
                .map(question -> question.with(answerDao.findByQuestionId(question.id())))
                .toList();
    }

    @Transactional
    @Override
    public void remove(List<Long> questionIds) {
        if (questionIds.isEmpty()) {
            return;
        }
        jdbcTemplate.update("delete from question where id in (:ids)",
                new MapSqlParameterSource("ids", questionIds));
    }

    @Transactional
    @Override
    public void removeByFormId(List<Long> formIds) {
        if (formIds.isEmpty()) {
            return;
        }
        jdbcTemplate.update("delete from question where form_id in (:formIds)",
                new MapSqlParameterSource("fromIds", formIds));
    }
}
