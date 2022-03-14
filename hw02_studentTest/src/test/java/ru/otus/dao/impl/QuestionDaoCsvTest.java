package ru.otus.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dao.QuestionDao;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionDaoCsvTest {
    private final static int EXPECTED_QUESTIONS_COUNT=3;
    private QuestionDao questionDao;

    @BeforeEach
    void setUp(){
        questionDao = new QuestionDaoCsv("questions.csv");
    }

    @DisplayName("Должен вернуть верное количество вопросов")
    @Test
    void shouldReturnCorrectCountOfQuestions(){
        int actualCount=questionDao.getListOfQuestions().size();
        assertThat(EXPECTED_QUESTIONS_COUNT).isEqualTo(actualCount);
    }

}
