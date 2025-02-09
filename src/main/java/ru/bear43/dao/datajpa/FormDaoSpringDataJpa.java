package ru.bear43.dao.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.FormDao;
import ru.bear43.dao.datajpa.repo.FormRepo;
import ru.bear43.model.dto.Form;
import ru.bear43.model.entity.FormEntity;
import ru.bear43.model.mapper.FormMapper;

import java.util.List;
import java.util.Optional;

@Profile("spring-data-jpa")
@Repository
public class FormDaoSpringDataJpa implements FormDao {

    private final FormRepo formRepo;

    @Autowired
    public FormDaoSpringDataJpa(FormRepo formRepo) {
        this.formRepo = formRepo;
    }

    @Transactional
    @Override
    public Long create(String title) {
        FormEntity formEntity = new FormEntity();
        formEntity.setTitle(title);
        return formRepo.saveAndFlush(formEntity).getId();
    }

    @Override
    public Optional<Form> find(Long formId) {
        return formRepo.findById(formId)
                .map(FormMapper::map);
    }

    @Transactional
    @Override
    public void remove(List<Long> formId) {
        formRepo.deleteAllById(formId);
    }
}
