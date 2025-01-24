package ru.bear43.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.bear43.dao.AnswerDao;
import ru.bear43.model.dto.Answer;

import java.util.List;
import java.util.Optional;

@Profile("jdbc")
@Repository
public class AnswerDaoJdbc implements AnswerDao {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public AnswerDaoJdbc(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long create(String text) {
        return 0L;
    }

    @Override
    public Optional<Answer> find(Long answerId) {
        return Optional.empty();
    }

    @Override
    public void remove(Long answerId) {

    }

    @Override
    public List<Answer> findByQuestionId(Long questionId) {
        return List.of();
    }
}
