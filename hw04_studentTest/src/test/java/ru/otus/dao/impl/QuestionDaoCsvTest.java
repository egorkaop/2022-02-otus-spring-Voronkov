package ru.otus.dao.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.config.CsvFileConfig;
import ru.otus.config.LocaleConfig;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.Answer;
import ru.otus.domain.Question;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class QuestionDaoCsvTest {
    @MockBean
    private static CsvFileConfig csvFileConfig;
    @MockBean
    private static LocaleConfig localeConfig;
    @Autowired
    private QuestionDao questionDao;

    @Configuration
    static class TestConfig {
        @Autowired
        CsvFileConfig csvFileConfig;
        @Autowired
        LocaleConfig localeConfig;

        @Bean
        QuestionDao questionDao() {
            return new QuestionDaoCsv(csvFileConfig, localeConfig);
        }
    }

    @Test
    void shouldReturnCorrectListOfQuestion() {
        when(csvFileConfig.getDefaultFileName()).thenReturn("questions");
        when(localeConfig.getLocale()).thenReturn("ru");
        List<Question> expectedQuestionList = new ArrayList<>();
        Question question = new Question();
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer());
        answerList.add(new Answer());
        answerList.get(0).setAnswer("да");
        answerList.get(0).setCorrect(true);
        answerList.get(1).setAnswer("нет");
        answerList.get(1).setCorrect(false);
        question.setQuestion("ты в порядке?");
        question.setAnswerList(answerList);
        expectedQuestionList.add(question);
        List<Question> actualQuestionList = questionDao.getListOfQuestions();
        assertThat(actualQuestionList).usingRecursiveComparison().isEqualTo(expectedQuestionList);

    }
}