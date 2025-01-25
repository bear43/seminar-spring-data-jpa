package ru.bear43.dao;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.bear43.dao.jdbc.UserDaoJdbc;

@DataJpaTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@EnableJpaRepositories(basePackages = "ru.bear43.dao.datajpa.repo")
@ActiveProfiles("jdbc")
@Sql("classpath:scripts/init.sql")
public class JdbcTest {
    @Container
    @ServiceConnection
    private static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:16");
}
