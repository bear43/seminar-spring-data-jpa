package ru.bear43.dao.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import ru.bear43.dao.FormDao;
import ru.bear43.dao.QuestionDao;
import ru.bear43.dao.util.Utils;
import ru.bear43.model.dto.Form;
import ru.bear43.model.dto.Question;

import java.util.List;

@ContextConfiguration(classes = FormDaoJdbc.class)
class FormDaoDbTest extends DbTest {

    @Autowired
    private FormDao formDao;

    @MockitoBean
    private QuestionDao questionDao;

    @Test
    @DisplayName("Успешное создание опроса")
    public void testCreate() {
        long generatedFormId = 1L;
        String title = "Социогастрономический опрос";
        Question plovQuestion = Utils.getQuestion("Укажите рецепт плова по вашему мнению",
                "Рис, мясо, чеснок...",
                "Не знаю, закажу из лавки",
                "Мама приготовит, я занят(-а), уходите",
                "25 картошек, 17 ...");
        Mockito.doReturn(List.of(plovQuestion))
                .when(questionDao)
                .findByFormId(generatedFormId);

        Long formId = formDao.create(title);
        Form form = formDao.find(formId).get();

        Assertions.assertEquals(generatedFormId, formId);
        Assertions.assertEquals(title, form.title());
        Assertions.assertTrue(formDao.find(formId).isPresent());
    }

    @Test
    @DisplayName("Успешное удаление опроса")
    public void testRemove() {
        long generatedFormId = 1L;
        String title = "Опрос самых успешных людей";
//        Question songQuestion = getQuestion("Какой трек, по-вашему, лучше всего подойдёт для выхода на защиту диплома",
//                "Любэ - выйду в поле с конём",
//                "INSTASAMKA - За деньги да",
//                "Шуфутинский - Путана",
//                "Клава Кока и утренняя звезда - Мне пох");
        Long formId = formDao.create(title);
        Assertions.assertEquals(generatedFormId, formId);

        formDao.remove(List.of(formId));
        Assertions.assertTrue(formDao.find(formId).isEmpty());
        Mockito.verify(questionDao)
                .removeByFormId(List.of(generatedFormId));
    }
}