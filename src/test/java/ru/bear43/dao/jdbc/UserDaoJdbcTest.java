package ru.bear43.dao.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.bear43.dao.JdbcTest;
import ru.bear43.dao.UserDao;

@ContextConfiguration(classes = UserDaoJdbc.class)
class UserDaoJdbcTest extends JdbcTest {

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