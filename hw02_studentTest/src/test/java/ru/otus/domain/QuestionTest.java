package ru.otus.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionTest {
    private int index;
    private String question;
    private String answers;
    private String correctAnswer;

    @Override
    public String toString() {
        return question + " " +
                answers;
    }
}
