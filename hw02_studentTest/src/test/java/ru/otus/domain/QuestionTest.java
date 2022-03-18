package ru.otus.domain;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class QuestionTest {
    String question;
    List<AnswerTest> answerList;

    @Override
    public String toString() {
        return question +
                answerList;
    }
}
