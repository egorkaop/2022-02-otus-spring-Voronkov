package ru.otus.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AnswerTest {
    String answer;
    boolean correct;

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
    @Override
    public String toString() {
        return answer;
    }
}
