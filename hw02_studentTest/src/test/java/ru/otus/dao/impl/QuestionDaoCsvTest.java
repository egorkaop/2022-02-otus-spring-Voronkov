package ru.otus.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Question;
import ru.otus.domain.QuestionTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionDaoCsvTest {
    private final static int EXPECTED_QUESTIONS_COUNT = 3;
    private QuestionDao questionDao;
    private List<QuestionTest> expectedQuestionList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoCsv("questions.csv");
        expectedQuestionList.add(new QuestionTest(1, "you are okey?", "yes no", "yes"));
        expectedQuestionList.add(new QuestionTest(2, "question?", "1 2", "2"));
        expectedQuestionList.add(new QuestionTest(3, "true or false?", "true false", "true"));
    }

    @DisplayName("Должен вернуть верное количество вопросов")
    @Test
    void shouldReturnCorrectCountOfQuestions() {
        int actualCount = questionDao.getListOfQuestions().size();
        assertThat(actualCount).isEqualTo(EXPECTED_QUESTIONS_COUNT);
    }

    @DisplayName("Должен вернуть заполненный список")
    @Test
    void shouldReturnNotNullList() {
        List<Question> actualQuestionList = questionDao.getListOfQuestions();
        assertThat(actualQuestionList).isNotNull();
    }

    @DisplayName("Должен вернуть правильный список")
    @Test
    void shouldReturnCorrectList() {
        List<Question> actualQuestionList = questionDao.getListOfQuestions();
        assertThat(expectedQuestionList.get(0).toString()).isEqualTo(actualQuestionList.get(0).toString());
        assertThat(expectedQuestionList.get(1).toString()).isEqualTo(actualQuestionList.get(1).toString());
        assertThat(expectedQuestionList.get(2).toString()).isEqualTo(actualQuestionList.get(2).toString());
    }

}
