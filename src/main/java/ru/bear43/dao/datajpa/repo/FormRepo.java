package ru.bear43.dao.datajpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bear43.model.entity.FormEntity;

@Repository
public interface FormRepo extends JpaRepository<FormEntity, Long> {
}
