package ru.otus.dao.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.dao.QuestionDao;
import ru.otus.domain.AnswerTest;
import ru.otus.domain.Question;
import ru.otus.domain.QuestionTest;
import ru.otus.utils.impl.QuestionMapperImpl;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class QuestionDaoCsvTest {
    private final static int EXPECTED_QUESTIONS_COUNT = 3;
    private QuestionDao questionDao;
    private List<QuestionTest> expectedQuestionList = new ArrayList<>();


    @BeforeEach
    void setUp() {
        questionDao = new QuestionDaoCsv("questions.csv",new QuestionMapperImpl());
        List<AnswerTest> answerTests1 = new ArrayList<>();
        answerTests1.add(new AnswerTest("yes",true));
        answerTests1.add(new AnswerTest("no",false));
        List<AnswerTest> answerTests2 = new ArrayList<>();
        answerTests2.add(new AnswerTest("1",false));
        answerTests2.add(new AnswerTest("2",true));
        List<AnswerTest> answerTests3 = new ArrayList<>();
        answerTests3.add(new AnswerTest("true",true));
        answerTests3.add(new AnswerTest("false",false));
        expectedQuestionList.add(new QuestionTest("you are okey?", answerTests1));
        expectedQuestionList.add(new QuestionTest("question?", answerTests2));
        expectedQuestionList.add(new QuestionTest("true or false?", answerTests3));
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
