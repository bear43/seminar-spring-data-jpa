package ru.bear43.dao.jpa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import ru.bear43.dao.UserDao;
import ru.bear43.dao.jdbc.UserDaoJdbc;

@ContextConfiguration(classes = {UserDaoJdbc.class, UserDaoJpa.class})
class UserDaoDbTest extends DbTest {

    @Autowired
    private UserDao userDao;

    @Test
    @DisplayName("Успешное создание пользователя")
    public void testCreate() {
        Long userId = userDao.create("baron");

        Assertions.assertEquals(1L, userId);
        Assertions.assertTrue(userDao.find(userId).isPresent());
    }
}