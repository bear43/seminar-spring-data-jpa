package ru.bear43.dao.datajpa.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bear43.model.entity.UserAnswerEntity;
import ru.bear43.model.entity.UserAnswerEntityPK;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAnswerRepo extends JpaRepository<UserAnswerEntity, UserAnswerEntityPK> {
    List<UserAnswerEntity> findAllByUser_IdAndForm_Id(Long userId, Long formId);
}
