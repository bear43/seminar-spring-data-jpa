package ru.bear43.dao.jpa;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.UserDao;
import ru.bear43.model.dto.User;
import ru.bear43.model.entity.UserEntity;

import java.util.Optional;

@Profile("jpa")
@Repository
public class UserDaoJpa implements UserDao {

    private static class Mapper {
        public static User map(UserEntity userEntity) {
            return new User(userEntity.getId(), userEntity.getNickname());
        }
    }

    private final EntityManager entityManager;

    @Autowired
    public UserDaoJpa(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public Long create(String nickname) {
        UserEntity entity = new UserEntity();
        entity.setNickname(nickname);
        entityManager.persist(entity);
        return entity.getId();
    }

    @Override
    public Optional<User> find(Long userId) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, userId))
                .map(Mapper::map);
    }
}
