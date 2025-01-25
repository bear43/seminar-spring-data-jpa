package ru.bear43.dao.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.FormDao;
import ru.bear43.model.dto.Answer;
import ru.bear43.model.dto.Form;
import ru.bear43.model.dto.Question;
import ru.bear43.model.entity.FormEntity;

import java.util.List;
import java.util.Optional;

@Profile("jpa")
@Repository
public class FormDaoJpa implements FormDao {

    private static class Mapper {
        private static Form map(FormEntity formEntity) {
            List<Question> questions = formEntity.getQuestions()
                    .stream()
                    .map(question -> new Question(question.getId(), question.getText(),
                            question.getAnswers()
                                    .stream()
                                    .map(answerEntity -> new Answer(answerEntity.getId(), answerEntity.getText()))
                                    .toList()))
                    .toList();
            return new Form(formEntity.getId(), formEntity.getTitle(), questions);
        }
    }

    private final EntityManager entityManager;

    @Autowired
    public FormDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Long create(String title) {
        FormEntity formEntity = new FormEntity();
        formEntity.setTitle(title);
        entityManager.persist(formEntity);
        return formEntity.getId();
    }

    @Override
    public Optional<Form> find(Long formId) {
        return Optional.ofNullable(entityManager.find(FormEntity.class, formId))
                .map(Mapper::map);
    }

    @Transactional
    @Override
    public void remove(List<Long> formIds) {
        formIds.stream()
                .map(formId -> entityManager.getReference(FormEntity.class, formId))
                .forEach(entityManager::remove);
    }
}
