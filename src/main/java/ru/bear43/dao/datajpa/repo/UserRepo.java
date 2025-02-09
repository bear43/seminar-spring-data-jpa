package ru.bear43.dao.datajpa.repo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bear43.model.entity.UserEntity;

@Profile("spring-data-jpa")
@Repository
public interface UserRepo extends JpaRepository<UserEntity, Long> {

}
